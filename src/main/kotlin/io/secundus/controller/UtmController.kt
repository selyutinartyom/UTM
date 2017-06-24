package io.secundus.controller

import ch.qos.logback.classic.Logger
import io.secundus.config.UtmProperties
import io.secundus.model.InElement
import io.secundus.model.InResponse
import io.secundus.model.OutElement
import io.secundus.service.UtmService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.ServletContext

/**
 * UTM REST controller
 *
 * @author Secundus
 * @since 04.04.2016 0:49
 */
@RestController
@RequestMapping("/opt")
class UtmController @Autowired constructor(
        utm: UtmProperties,
        val service: UtmService,
        val context: ServletContext) {

    val logger = LoggerFactory.getLogger(this.javaClass.name) as Logger

    val PATH = utm.path
    val OUT_FILE = utm.outFileName
    val IN_FILE = utm.inFileName
    val IN_RESPONSE_FILE = utm.inResponseFileName

    val OUT_PATH = utm.opt.out.path
    val IN_PATH = utm.opt.`in`.path

    /* Входящие (/opt/out) */

    @GetMapping(value = "/out", produces = arrayOf(MediaType.APPLICATION_XML_VALUE))
    @ResponseBody
    fun getOptOut(): String {
        logger.info("GET /opt/out")
        return service.processingXml("$PATH$OUT_FILE", OutElement())
    }

    @GetMapping(value = "/out/{doc}/{id}", produces = arrayOf(MediaType.APPLICATION_XML_VALUE))
    @ResponseBody
    fun getOutDocument(@PathVariable("doc") doc: String,
                       @PathVariable("id") id: Long): String {
        logger.info("GET /opt/out/$doc/$id")
        return service.processingXml("$OUT_PATH${doc}_$id.xml", OutElement())
    }

    @DeleteMapping(value = "/out/{doc}/{id}")
    fun deleteOutDocument(@PathVariable doc: String,
                          @PathVariable id: Long) = logger.info("DELETE /opt/out/$doc/$id")

    /* Исходящие (/opt/in) */

    @GetMapping(value = "/in", produces = arrayOf(MediaType.APPLICATION_XML_VALUE))
    @ResponseBody
    fun getOptIn(): String {
        logger.info("GET /opt/in")
        return service.processingXml("$PATH$IN_FILE", InElement())
    }

    @GetMapping(value = "/in/{doc}/{id}", produces = arrayOf(MediaType.APPLICATION_XML_VALUE))
    @ResponseBody
    fun getInDocument(@PathVariable("doc") doc: String,
                      @PathVariable("id") id: Long): String {
        logger.info("GET /opt/in/$doc/$id")
        return service.processingXml("$IN_PATH${doc}_$id.xml", InElement())
    }

    @PostMapping(value = "/in/{doc}")
    fun saveDocument(@PathVariable doc: String,
                     @RequestParam("xml_file") file: MultipartFile): String {
        logger.info("POST /opt/in/$doc")
        return service.processingXml("$PATH$IN_RESPONSE_FILE", InResponse())
    }

    @DeleteMapping(value = "/in/{doc}/{id}")
    fun deleteInDocument(@PathVariable doc: String,
                         @PathVariable id: Long) = logger.info("DELETE /opt/in/$doc/$id")
}