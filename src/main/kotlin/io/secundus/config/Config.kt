package io.secundus.config

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import javax.servlet.MultipartConfigElement

/**
 * Application configurations
 *
 * @author Secundus
 * @since 29.03.2017 18:44
 */
@Configuration
class Configuration {

    @Bean
    fun multipartConfigElement(): MultipartConfigElement {
        val factory = MultipartConfigFactory()
        factory.setMaxFileSize("100MB")
        factory.setMaxRequestSize("100MB")
        return factory.createMultipartConfig()
    }

    @Primary
    @Bean
    fun xmlMapper(): XmlMapper {
        val mapper = XmlMapper()
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
        return mapper
    }

    @Bean
    fun jaxbMapper(): XmlMapper {
        val module = JaxbAnnotationModule()
        val mapper = XmlMapper()
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)
        mapper.registerModule(module)
        return mapper
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    @Bean
    fun httpMessageConverter(): HttpMessageConverter<Any> {
        return MappingJackson2HttpMessageConverter(objectMapper())
    }

    @Bean
    fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())

    @Bean
    fun namespaces(): Map<String, String> = mapOf(
            Pair("c", "http://fsrar.ru/WEGAIS/Common"),
            Pair("ns", "http://fsrar.ru/WEGAIS/WB_DOC_SINGLE_01"),
            Pair("oref", "http://fsrar.ru/WEGAIS/ClientRef"),
            Pair("pref", "http://fsrar.ru/WEGAIS/ProductRef"),
            Pair("wb", "http://fsrar.ru/WEGAIS/TTNSingle"),
            Pair("xsi", "http://www.w3.org/2001/XMLSchema-instance"),
            Pair("rst", "http://fsrar.ru/WEGAIS/ReplyRests")
    )
}

/**
 * UTM properties
 *
 * @author Secundus
 * @since 29.03.2017 22:56
 */
@Configuration
@ConfigurationProperties(prefix = "utm")
class UtmProperties {

    /* Server properties */

    @Value("\${server.address}")
    lateinit var address: String

    @Value("\${server.port}")
    lateinit var port: String

    /* UTM properties */

    lateinit var path: String
    lateinit var outFileName: String
    lateinit var inFileName: String
    lateinit var inResponseFileName: String

    var opt = Opt()

    class Opt {
        var out = Out()
        var `in` = In()

        class Out {
            lateinit var path: String
            var documents: MutableList<String> = mutableListOf()
        }

        class In {
            lateinit var path: String
            var documents: MutableList<String> = mutableListOf()
        }
    }
}

const val PATTERN = "dd.MM.yyyy"