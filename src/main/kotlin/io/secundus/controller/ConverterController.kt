package io.secundus.controller

import io.secundus.service.ConverterService
import io.secundus.util.currentDate
import io.secundus.util.toLocalDate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Converter page controller
 *
 * @author Secundus
 * @since 01.04.2017 16:04
 */
@Controller
@RequestMapping("/converter")
class ConverterController {
    @GetMapping
    fun getConverterPage() = "converter"
}

/**
 * Converter processing REST controller
 *
 * @author Secundus
 * @since 03.04.2017 19:40
 */
@RestController
@RequestMapping("/convert")
@SessionAttributes("wayBillForm")
class ConverterProcessingController @Autowired constructor(val service: ConverterService) {

    val logger = LoggerFactory.getLogger(this.javaClass.name) as ch.qos.logback.classic.Logger

    @PostMapping
    fun convert(@Valid @ModelAttribute("wayBillForm") form: WayBillForm, bindingResult: BindingResult): String {

        logger.info("POST /convert $form")

        if (bindingResult.hasErrors()) {
            return bindingResult.allErrors.toString()
        }

        val wrapper = createWayBillWrapper(form)
        val result = service.replyRestsToWayBill(wrapper)

        return result
    }

    private fun createWayBillWrapper(wayBillForm: WayBillForm): WayBillWrapper {
        return WayBillWrapper(
                wayBillForm.number,
                wayBillForm.date.toLocalDate(),
                wayBillForm.shippingDate.toLocalDate(),
                wayBillForm.base, wayBillForm.note,
                wayBillForm.shipperFsrarId, wayBillForm.shipperInn, wayBillForm.shipperKpp,
                wayBillForm.shipperName, wayBillForm.shipperAddress,
                wayBillForm.consigneeFsrarId, wayBillForm.consigneeInn, wayBillForm.consigneeKpp,
                wayBillForm.consigneeName, wayBillForm.consigneeAddress,
                wayBillForm.xmlString)
    }
}

class WayBillForm {
    // Документ
    @NotNull @Size(min = 1, max = 15)
    var number: String = ""
    @NotNull @Pattern(regexp = "[0-9]{2}[.][0-9]{2}[.][0-9]{4}")
    var date: String = String.currentDate()
    @NotNull @Pattern(regexp = "[0-9]{2}[.][0-9]{2}[.][0-9]{4}")
    var shippingDate: String = String.currentDate()
    var base: String? = null
    var note: String? = null

    // Отправитель
    @NotNull @Size(min = 12, max = 12)
    var shipperFsrarId: String = ""
    @NotNull @Size(min = 1, max = 12)
    var shipperInn: String = ""
    @NotNull @Size(min = 1, max = 12)
    var shipperKpp: String = ""
    var shipperName: String? = null
    var shipperAddress: MutableMap<String, String> = mutableMapOf()

    // Получатель
    @NotNull @Size(min = 12, max = 12)
    var consigneeFsrarId: String = ""
    @NotNull @Size(min = 1, max = 12)
    var consigneeInn: String = ""
    @NotNull @Size(min = 1, max = 12)
    var consigneeKpp: String = ""
    var consigneeName: String? = null
    var consigneeAddress: MutableMap<String, String> = mutableMapOf()

    // WayBill XML
    @NotNull
    var xmlString: String = ""

    override fun toString(): String {
        return "WayBillForm(number='$number', date='$date', shippingDate='$shippingDate', base=$base, note=$note," +
                " shipperFsrarId='$shipperFsrarId', shipperInn='$shipperInn', shipperKpp='$shipperKpp', " +
                " shipperName='$shipperName', shipperAddress='$shipperAddress'," +
                " consigneeFsrarId='$consigneeFsrarId', consigneeInn='$consigneeInn', consigneeKpp='$consigneeKpp'," +
                " consigneeName='$consigneeName', consigneeAddress='$consigneeAddress'," +
                " \nxmlString='${xmlString.substring(0, if (xmlString.length > 200) 200 else xmlString.length)}...')"
    }
}

class WayBillWrapper(
        // Документ
        val number: String,
        val date: LocalDate,
        val shippingDate: LocalDate,
        val base: String? = null,
        val note: String? = null,
        // Отправитель
        val shipperFsrarId: String,
        val shipperInn: String,
        val shipperKpp: String,
        val shipperName: String? = null,
        val shipperAddress: Map<String, String>,
        // Получатель
        val consigneeFsrarId: String,
        val consigneeInn: String,
        val consigneeKpp: String,
        val consigneeName: String? = null,
        val consigneeAddress: Map<String, String>,
        // WayBill XML
        val xmlString: String
)