package io.secundus.controller

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.secundus.model.InElement
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset

/**
 * UtmController test
 *
 * @author Selutin_AV
 * @since 25.04.2017 15:54
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
class UtmControllerTests {

    private val contentType = MediaType(MediaType.APPLICATION_XML.type,
            MediaType.APPLICATION_XML.subtype,
            Charset.forName("utf8"))

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    lateinit var xmlMapper: XmlMapper

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    /* Входящие (/opt/out) */

    @Test
    fun getOptOutTest() {
        val result = mockMvc.perform(get("/opt/out")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(result != "")
        assert(result.substring(0, result.indexOfFirst { it -> it == ' ' }) == "<?xml")
    }

    @Test
    fun getOutDocumentTest() {
        val wayBill123 = mockMvc.perform(get("/opt/out/WayBill/123")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(wayBill123 != "")
        assert(wayBill123.substring(0, wayBill123.indexOfFirst { it -> it == ' ' }) == "<?xml")

        val ticket4 = mockMvc.perform(get("/opt/out/Ticket/4")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(ticket4 != "")
        assert(ticket4.substring(0, ticket4.indexOfFirst { it -> it == ' ' }) == "<?xml")

        val formBRegInfo124 = mockMvc.perform(get("/opt/out/FormBRegInfo/124")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(formBRegInfo124 != "")
        assert(formBRegInfo124.substring(0, formBRegInfo124.indexOfFirst { it -> it == ' ' }) == "<ns:Documents")
    }

    @Test
    fun deleteOutDocumentTest() {
        mockMvc.perform(delete("/opt/out/WayBill/123")
                .contentType(contentType))
                .andExpect(status().isOk)
    }

    /* Исходящие (/opt/in) */

    @Test
    fun getOptInTest() {
        val result = mockMvc.perform(get("/opt/in")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(result != "")
        assert(result.substring(0, result.indexOfFirst { it -> it == ' ' }) == "<?xml")
    }

    @Test
    fun getInDocumentTest() {
        val wayBillAct123 = mockMvc.perform(get("/opt/in/WayBillAct/123")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(wayBillAct123 != "")
        assert(wayBillAct123.substring(0, wayBillAct123.indexOfFirst { it -> it == ' ' }) == "<?xml")

        val queryFormA124 = mockMvc.perform(get("/opt/in/QueryFormA/124")
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(queryFormA124 != "")
        assert(queryFormA124.substring(0, queryFormA124.indexOfFirst { it -> it == ' ' }) == "<?xml")
    }

    @Test
    fun saveDocumentTest() {
        val xmlString = xmlMapper.writeValueAsString(InElement())
        val xmlFile = MockMultipartFile("xml_file", "xml_file.xml", MediaType.APPLICATION_XML_VALUE,
                xmlString.toByteArray())

        // fileUpload creates multipart request that use POST method and "multipart/form-data" content type
        val inResponse = mockMvc.perform(
                MockMvcRequestBuilders
                        .fileUpload("/opt/in/WayBillAct")
                        .file(xmlFile))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(inResponse != "")
        assert(inResponse.substring(0, inResponse.indexOfFirst { it -> it == ' ' }) == "<?xml")
    }

    @Test
    fun deleteInDocumentTest() {
        mockMvc.perform(delete("/opt/in/WayBillAct/123")
                .contentType(contentType))
                .andExpect(status().isOk)
    }
}