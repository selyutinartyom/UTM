package io.secundus.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import io.secundus.util.getRandomHexString
import java.util.*

/**
 * UTM XML element model interface
 *
 * @author Selutin_AV
 * @since 29.03.2017 18:12
 */
interface UtmElement

/**
 * /opt/out RootElement
 *
 * @author Secundus
 * @since 04.04.2016 22:22
 */
@JacksonXmlRootElement(localName = "A")
class OutElement : UtmElement {
    @JacksonXmlProperty(localName = "url")
    @JacksonXmlElementWrapper(useWrapping = false)
    var documents: Array<OutUrl> = emptyArray()

    val ver: String = "1"
}

class OutUrl {
    @JacksonXmlProperty(isAttribute = true)
    var replyId: String = ""

    @JacksonXmlText
    var value: String = ""
}

/**
 * /opt/in RootElement
 *
 * @author Selutin_AV
 * @since 01.11.2016 18:22
 */
@JacksonXmlRootElement(localName = "A")
class InElement : UtmElement {
    @JacksonXmlProperty(localName = "url")
    @JacksonXmlElementWrapper(localName = "url", useWrapping = false)
    var documents: Array<InUrl> = emptyArray()

    val ver: String = "1"
}

class InUrl {
    @JacksonXmlProperty(localName = "replyId", isAttribute = true)
    var replyId: String = ""

    @JacksonXmlText
    var value: String = ""
}

/**
 * /opt/in response from POST method
 *
 * @author Selutin_AV
 * @since 29.03.2017 17:45
 */
@JacksonXmlRootElement(localName = "A")
class InResponse : UtmElement {
    val url: String = UUID.randomUUID().toString()
    val sign: String = String.getRandomHexString(128)
    val ver: String = "1"
}