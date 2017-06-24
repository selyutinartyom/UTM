package io.secundus.service

import io.secundus.controller.WayBillWrapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

/**
 * ConverterService test
 *
 * @author Secundus
 * @since 23.04.2017 16:44
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ConverterServiceTests {

    @Autowired
    lateinit var service: ConverterService

    val wrapper: WayBillWrapper = WayBillWrapper(
            number = "test number",
            date = LocalDate.now(),
            shippingDate = LocalDate.now(),
            base = "test base",
            note = "test note",
            shipperFsrarId = "123456789012",
            shipperInn = "123123",
            shipperKpp = "100100",
            shipperName = "Поставщик",
            shipperAddress = mapOf(Pair("city", "Санкт-Петербург"), Pair("street", "Яхтенная"), Pair("house", "22"), Pair("building", "1")),
            consigneeFsrarId = "111100001111",
            consigneeInn = "555000",
            consigneeKpp = "111000",
            consigneeName = "Получатель",
            consigneeAddress = mapOf(Pair("city", "Санкт-Петербург"), Pair("street", "Яхтенная"), Pair("house", "22"), Pair("building", "1")),
            xmlString = "test"
    )

    @Test
    fun replyRestsToWayBillTest() {
        val result = service.replyRestsToWayBill(wrapper)
        assert(result != "")
        assert(result.contains(wrapper.number))
        assert(result.contains(wrapper.date.toString()))
        assert(result.contains(wrapper.shippingDate.toString()))
        assert(result.contains(wrapper.base!!))
        assert(result.contains(wrapper.note!!))
        assert(result.contains(wrapper.shipperFsrarId))
        assert(result.contains(wrapper.shipperInn))
        assert(result.contains(wrapper.shipperKpp))
        assert(result.contains(wrapper.shipperName!!))
        assert(result.contains(wrapper.shipperAddress["city"].toString()))
        assert(result.contains(wrapper.shipperAddress["street"].toString()))
        assert(result.contains(wrapper.shipperAddress["house"].toString()))
        assert(result.contains(wrapper.shipperAddress["building"].toString()))
        assert(result.contains(wrapper.consigneeFsrarId))
        assert(result.contains(wrapper.consigneeInn))
        assert(result.contains(wrapper.consigneeKpp))
        assert(result.contains(wrapper.consigneeName!!))
        assert(result.contains(wrapper.consigneeAddress["city"].toString()))
        assert(result.contains(wrapper.consigneeAddress["street"].toString()))
        assert(result.contains(wrapper.consigneeAddress["house"].toString()))
        assert(result.contains(wrapper.consigneeAddress["building"].toString()))

        // TODO тестировать структуру исходящего xml файла WayBill
    }
}