package io.secundus.service

import io.secundus.model.OutElement
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * UtmService test
 *
 * @author Secundus
 * @since 23.04.2017 15:47
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class UtmServiceTests {

    @Autowired
    lateinit var service: UtmService

    @Test
    fun processingXmlTest() {
        val xml = service.processingXml("./out.xml", OutElement())
        assert(xml != "")
        assert(xml.substring(0, xml.indexOfFirst { it -> it == ' ' }) == "<?xml")

        val createdXml = service.processingXml("./not_found_test.xml", OutElement())
        assert(createdXml == "<?xml version='1.0' encoding='UTF-8'?><A><ver>1</ver></A>")
    }

    @Test
    fun getFilesTest() {
        val files = service.getFiles()
        assert(files.toList().isNotEmpty())

        // TODO тестировать логику чтения файлов по типам IN и OUT
    }
}