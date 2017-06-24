package io.secundus.util

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter
import java.io.Writer
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamException
import javax.xml.stream.XMLStreamWriter

/**
 * Filtering XMLStreamWriter which applies an empty namespace context
 *
 * @author Secundus
 * @since 06.04.2017 22:51
 * @see [Remove namespace prefix while JAXB marshalling](http://stackoverflow.com/a/21378770)
 */
class NoNamesWriter(writer: XMLStreamWriter, val ignoreNames: Map<String, String>) : DelegatingXMLStreamWriter(writer) {

    /**
     * Запись namespace в XML
     * Если записываемый namespace не содержится в ignoreNames, то удаляем его из XML.
     * В противном случае вызываем конструктор суперкласса и передаем ему namespace для записи.
     *
     * @param prefix префикс пространства имен
     * @param uri ссылка пространства имен
     */
    override fun writeNamespace(prefix: String, uri: String) {
        if (prefix in ignoreNames.keys && uri in ignoreNames.values)
            super.writeNamespace(prefix, uri)
    }

    companion object {
        /**
         * Фильтрация всех namespace в XML документе
         *
         * @param writer входной объект для записи потока символов
         * @return результат преобразования объекта в XMLStreamWriter
         */
        @Throws(XMLStreamException::class)
        fun filter(writer: Writer): XMLStreamWriter {
            return NoNamesWriter(
                    IndentingXMLStreamWriter(
                            XMLOutputFactory
                                    .newInstance()
                                    .createXMLStreamWriter(writer)), emptyMap())
        }

        /**
         * Фильтрация всех namespace в XML документе, исключая игнорируемые
         *
         * @param writer входной объект для записи потока символов
         * @param ignoreNames игнорируемые namespace, которые не будут удаляться из XML документа
         * @return результат преобразования объекта в XMLStreamWriter
         */
        @Throws(XMLStreamException::class)
        fun filter(writer: Writer, ignoreNames: Map<String, String>): XMLStreamWriter {
            return NoNamesWriter(
                    IndentingXMLStreamWriter(
                            XMLOutputFactory
                                    .newInstance()
                                    .createXMLStreamWriter(writer)), ignoreNames)
        }
    }
}
