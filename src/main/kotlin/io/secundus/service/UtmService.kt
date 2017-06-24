package io.secundus.service

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.secundus.config.UtmProperties
import io.secundus.model.DocumentType
import io.secundus.model.LocalFile
import io.secundus.model.UtmElement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

/**
 * UTM Service layer
 *
 * @author Secundus
 * @since 31.03.2017 0:16
 */
interface UtmService {
    fun processingXml(path: String, element: UtmElement): String
    fun getFiles(): Iterable<LocalFile>
}

/**
 * UTM XML Service Jackson implementation
 *
 * @author Secundus
 * @since 31.03.2017 0:19
 */
@Service
class UtmXmlService @Autowired constructor(
        utm: UtmProperties,
        val mapper: XmlMapper) : UtmService {

    val HOSTNAME = "${utm.address}:${utm.port}"

    val PATH = utm.path
    val OUT_FILE = utm.outFileName
    val IN_FILE = utm.inFileName
    val IN_RESPONSE_FILE = utm.inResponseFileName

    val OUT_PATH = utm.opt.out.path
    val IN_PATH = utm.opt.`in`.path

    val OUT_DOCUMENTS = utm.opt.out.documents
    val IN_DOCUMENTS = utm.opt.`in`.documents

    /**
     * Обработка XML файла по пути из HTTP запроса.
     * Если файл не найден, подставляем сгенерированный Jackson-ом XML по классу, унаследованному от Element.
     *
     * @param path путь к локальным XML файлам
     * @param element модель XML файла для генерации, если по параметру {@code path} не будет найден файл
     * @return XML файл в виде строки
     */
    override fun processingXml(path: String, element: UtmElement): String {
        var content: String

        try {
            // Ответ из файла
            val data = Files.readAllBytes(Paths.get(path))
            content = String(data, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            // Стандартный ответ
            content = mapper.writeValueAsString(element)
        }
        return content.replace("\$hostname", HOSTNAME, true)
    }

    /**
     * Возвращает список всех локальных XML файлов из директорий OUT_PATH и IN_PATH,
     * которые могут быть вызваны в UTM.
     *
     * @return список локальных XML файлов
     */
    override fun getFiles(): Iterable<LocalFile> {
        val list = mutableListOf<LocalFile>()

        if (OUT_PATH == IN_PATH) return emptyList()

        val outPath = Paths.get(OUT_PATH)
        val inPath = Paths.get(IN_PATH)
        try {
            // Документы /opt/out
            val outStream = Files.newDirectoryStream(outPath, "*.{xml}")
            outStream.forEach { it ->
                val file = newFileOrNull(it.fileName.toString(), "/opt/out", DocumentType.OUT)
                if (file != null)
                    list.add(file)
            }

            // документы /opt/in
            val inStream = Files.newDirectoryStream(inPath, "*.{xml}")
            inStream.forEach { it ->
                val file = newFileOrNull(it.fileName.toString(), "/opt/in", DocumentType.IN)
                if (file != null)
                    list.add(file)
            }
        } catch (e: IOException) {
        }
        return list
    }

    /**
     * Создает новый объект {@link io.secundus.xml.LocalFile} из имени XML-файла или возвращает null
     *
     * @param name имя XML-файла
     * @param path путь к ресурсу (/opt/out или /opt/in)
     * @param type тип ресурса OUT или IN
     * @return новый файл или null
     * @see LocalFile
     */
    private fun newFileOrNull(name: String, path: String, type: DocumentType): LocalFile? {
        val doc: String
        val id = findId(name)

        when (type) {
            DocumentType.OUT -> {
                if (!validateName(name, OUT_DOCUMENTS))
                    return null
                doc = findDoc(name, OUT_DOCUMENTS)
            }
            DocumentType.IN -> {
                if (!validateName(name, IN_DOCUMENTS))
                    return null
                doc = findDoc(name, IN_DOCUMENTS)
            }
        }
        return LocalFile(name, "$path/$doc/$id", type)
    }

    /**
     * Проверка имени XML-файла на соответствие условиям:
     * 1. Имя файла не указано в определенных свойствах (application.yml или classpath):
     *    a) {@code utm.outFileName}
     *    b) {@code utm.inFileName}
     *    c) {@code utm.inResponseFileName}
     * 2. Имя файла соответствует регулярному выражению с учетом допустимых названий документов.
     *    a) начинается с названия документа из application.yml или classpath ${it}
     *    b) за ним следует символ подчеркивания "_" и одна или более цифр \\d+
     *    c) заканчивается символами .xml
     *
     * @param name имя XML-файла
     * @return true - соответствует условиям, false - не соответствует условиям
     */
    private fun validateName(name: String, documents: MutableList<String>): Boolean {

        if (name == OUT_FILE || name == IN_FILE || name == IN_RESPONSE_FILE) return false

        var isError = true
        documents
                .filter { name.contains(other = it, ignoreCase = true) }
                .forEach {
                    if (name.matches("\\b${it}_(\\d+).xml\\b".toRegex(RegexOption.IGNORE_CASE)))
                        isError = false
                }
        if (isError) return false
        return true
    }

    /**
     * Поиск названия документа в имени XML-файла
     *
     * @param name имя XML-файла
     * @return название документа
     */
    private fun findDoc(name: String, documents: MutableList<String>): String {
        documents
                .filter { name.contains(other = it, ignoreCase = true) }
                .forEach {
                    if (it.equals(
                            other = name.substring(0, name.indexOfLast { c: Char -> c == '_' }),
                            ignoreCase = true))
                        return it
                }
        return ""
    }

    /**
     * Поиск номера документа в имени XML-файла
     *
     * @param name имя XML-файла
     * @return номер документа
     */
    private fun findId(name: String) =
            name.substring(
                    name.indexOfLast { c: Char -> c == '_' } + 1,
                    name.indexOfLast { c: Char -> c == '.' })
}