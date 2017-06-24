package io.secundus.controller

import io.secundus.util.currentDate
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.mock.http.MockHttpInputMessage
import org.springframework.mock.http.MockHttpOutputMessage
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset

/**
 * ConverterProcessingController test
 *
 * @author Secundus
 * @since 23.04.2017 18:15
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner::class)
class ConverterProcessingControllerTests {

    private val contentType = MediaType(MediaType.APPLICATION_FORM_URLENCODED.type,
            MediaType.APPLICATION_FORM_URLENCODED.subtype,
            Charset.forName("utf8"))

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mappingJackson2HttpMessageConverter: HttpMessageConverter<Any>

    lateinit var mockMvc: MockMvc
    lateinit var form: WayBillForm

    @Autowired
    @Qualifier("httpMessageConverter")
    fun setConverters(converters: Array<HttpMessageConverter<Any>>) {
        mappingJackson2HttpMessageConverter = converters
                .filter { it -> it is MappingJackson2HttpMessageConverter }
                .first()
        assertNotNull("the JSON message converter must not be null", mappingJackson2HttpMessageConverter)
    }

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        form = WayBillForm()
        with(form) {
            number = "test number"
            date = String.currentDate()
            shippingDate = String.currentDate()
            base = "test base"
            note = "test note"
            shipperFsrarId = "123456789012"
            shipperInn = "123123"
            shipperKpp = "100100"
            shipperName = "Поставщик"
            shipperAddress = mutableMapOf(Pair("city", "Санкт-Петербург"), Pair("street", "Яхтенная"), Pair("house", "22"), Pair("building", "1"))
            consigneeFsrarId = "111100001111"
            consigneeInn = "555000"
            consigneeKpp = "111000"
            consigneeName = "Получатель"
            consigneeAddress = mutableMapOf(Pair("city", "Санкт-Петербург"), Pair("street", "Яхтенная"), Pair("house", "22"), Pair("building", "1"))
            xmlString = "test"
        }
    }

    @Test
    fun convertTest() {
        val result = mockMvc.perform(post("/convert")
                .sessionAttr("wayBillForm", form)
                .contentType(contentType))
                .andExpect(status().isOk)
                .andReturn()
                .response.contentAsString
        assert(result.isNotEmpty())
        assert(result.substring(0, result.indexOfFirst { it -> it == ' ' }) == "<?xml")
    }

    private fun json(obj: Any): String {
        val mockHttpOutputMessage = MockHttpOutputMessage()
        mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage)
        return mockHttpOutputMessage.bodyAsString
    }

    private fun fromJsonToGuestModel(contents: ByteArray): WayBillForm {
        val mockInputMessage = MockHttpInputMessage(contents)
        return mappingJackson2HttpMessageConverter.read(WayBillForm::class.java, mockInputMessage) as WayBillForm
    }
}