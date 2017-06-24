package io.secundus.service

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper
import io.secundus.controller.WayBillWrapper
import io.secundus.model.xml.*
import io.secundus.util.NoNamesWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.fsrar.wegais.clientref.OrgAddressType
import ru.fsrar.wegais.ttnsingle.WbType
import ru.fsrar.wegais.ttnsingle.WbUnitType
import ru.fsrar.wegais.wb_doc_single_01.Documents
import java.io.StringReader
import java.io.StringWriter
import java.math.BigDecimal
import java.time.ZoneId
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller

/**
 * Converter service layer
 *
 * @author Secundus
 * @since 03.04.2017 21:39
 */
interface ConverterService {
    fun replyRestsToWayBill(wrapper: WayBillWrapper): String
}

/**
 * Converter XML service JAXB implementation
 *
 * @author Secundus
 * @since 03.04.2017 21:47
 * @see [Generate Java classes from .XSD files…?](http://stackoverflow.com/questions/686453/generate-java-classes-from-xsd-files)
 * @see [How to provide JACKSON with the namespace-mapper in the code?](http://stackoverflow.com/questions/13214306/how-to-provide-jackson-with-the-namespace-mapper-in-the-code)
 */
@Service
class ConverterXmlService @Autowired constructor(val namespaces: Map<String, String>) : ConverterService {

    override fun replyRestsToWayBill(wrapper: WayBillWrapper): String {

        val replyRests = createDocumentsByXML(wrapper.xmlString)

        // Main converting logic
        val wayBill = convertReplyRestsToWayBill(replyRests, wrapper)

        val wayBillNames = namespaces.filter {
            it.key in arrayOf("c", "ns", "oref", "pref", "wb", "xsi")
        }
        return createXMLByDocuments(wayBill, wayBillNames)
    }

    /**
     * Create Documents object from XML string
     *
     * @param xml string
     * @return Documents object
     */
    private fun createDocumentsByXML(xml: String): Documents {
        val factory = ObjectFactoryV1()
        var replyRests = factory.createDocuments()

        val reader = StringReader(xml)
        val ctx: JAXBContext = JAXBContext.newInstance(replyRests.javaClass)
        try {
            val u: Unmarshaller = ctx.createUnmarshaller()
            replyRests = u.unmarshal(reader) as Documents
        } catch (e: Exception) {
        }
        return replyRests
    }

    /**
     * Create XML string from Documents object
     *
     * @param doc Document object
     * @return XML string
     */
    private fun createXMLByDocuments(doc: Documents, namespaces: Map<String, String>): String {
        val writer = StringWriter()
        val ctx: JAXBContext = JAXBContext.newInstance(doc.javaClass)
        try {
            val m: Marshaller = ctx.createMarshaller()
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            m.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", UtmNamespaceMapper(namespaces))
            m.marshal(doc, NoNamesWriter.filter(writer, namespaces))
        } catch (e: Exception) {
        }
        return writer.toString()
    }

    /**
     * Convert ReplyRests (Documents object) to WayBill
     *
     * @param replyRests input Documents object
     * @return output Documents object (WayBill)
     */
    private fun convertReplyRestsToWayBill(replyRests: Documents, wrapper: WayBillWrapper): Documents {

        val factory = ObjectFactoryV1()
        val wayBillDocuments = factory.createDocuments()

        val owner = factory.createSenderInfo()
        val body = factory.createDocBody()

        val wayBill = factory.createWayBillType()
        val header = factory.createWayBillTypeHeader()
        val content = factory.createWayBillTypeContent()

        wayBillDocuments.let {
            it.owner = owner
            it.document = body
        }

        owner.fsrarid = wrapper.shipperFsrarId
        body.wayBill = wayBill

        wayBill.let {
            it.identity = wrapper.number
            it.header = header
            it.content = content
        }

        // Header
        header.let {
            val transport = factory.createTransportType()
            val shipper = factory.createOrgInfo()
            val consignee = factory.createOrgInfo()
            val supplier = factory.createOrgInfo()

            it.number = wrapper.number
            it.date = XMLGregorianCalendarImpl(
                    GregorianCalendar.from(wrapper.date.atStartOfDay(ZoneId.systemDefault())))
            it.shippingDate = XMLGregorianCalendarImpl(
                    GregorianCalendar.from(wrapper.shippingDate.atStartOfDay(ZoneId.systemDefault())))
            it.type = WbType.WB_INVOICE_FROM_ME
            it.unitType = WbUnitType.PACKED
            if (wrapper.base?.isNotEmpty() ?: false)
                it.base = wrapper.base
            if (wrapper.note?.isNotEmpty() ?: false)
                it.note = wrapper.note
            it.shipper = shipper
            it.consignee = consignee
            it.supplier = supplier
            it.transport = transport

            // Transport
            with(transport) {
                //trantype = ""
                //trancompany = ""
                //trancar = "some trancar"
                //trantrailer = ""
                trancustomer = wrapper.shipperName
                //trandriver = "some trandriver"
                tranloadpoint = wrapper.consigneeAddress.toFullAddress()
                tranunloadpoint = wrapper.shipperAddress.toFullAddress()
                //tranredirect = ""
                //tranforwarder = ""
            }

            // Shipper
            with(shipper) {
                val shipperAddress = factory.createOrgAddressType()

                clientRegId = wrapper.shipperFsrarId
                inn = factory.createOrgInfoExINN(wrapper.shipperInn)
                kpp = wrapper.shipperKpp
                if (wrapper.shipperName?.isNotEmpty() ?: false) {
                    fullName = wrapper.shipperName
                    shortName = wrapper.shipperName
                }
                //identity = ""
                //unp = factory.createOrgInfoExUNP("")
                //rnn = factory.createOrgInfoExRNN("")
                address = shipperAddress.setAddress(wrapper.shipperAddress)
            }

            // Consignee
            with(consignee) {
                val consigneeAddress = factory.createOrgAddressType()

                clientRegId = wrapper.consigneeFsrarId
                inn = factory.createOrgInfoExINN(wrapper.consigneeInn)
                kpp = wrapper.consigneeKpp
                if (wrapper.consigneeName?.isNotEmpty() ?: false) {
                    fullName = wrapper.consigneeName
                    shortName = wrapper.consigneeName
                }
                //identity = ""
                //unp = factory.createOrgInfoExUNP("some unp")
                //rnn = factory.createOrgInfoExRNN("some rnn")
                address = consigneeAddress.setAddress(wrapper.consigneeAddress)
            }

            // Supplier
            with(supplier) {
                val supplierAddress = factory.createOrgAddressType()

                clientRegId = wrapper.shipperFsrarId
                inn = factory.createOrgInfoExINN(wrapper.shipperInn)
                kpp = wrapper.shipperKpp
                if (wrapper.shipperName?.isNotEmpty() ?: false) {
                    fullName = wrapper.shipperName
                    shortName = wrapper.shipperName
                }
                //identity = ""
                //unp = factory.createOrgInfoExUNP("some unp")
                //rnn = factory.createOrgInfoExRNN("some rnn")
                address = supplierAddress.setAddress(wrapper.shipperAddress)
            }
        }

        // Content
        content.let {

            replyRests.document?.replyRests?.products?.stockPosition?.forEachIndexed { index, stockPositionType ->

                val position = factory.createPositionType()

                position.identity = (index + 1).toString()
                position.quantity = stockPositionType.quantity
                position.price = BigDecimal.ZERO // Перемещение остатков с нулевой ценой

                // InformA.regId
                val informA = factory.createInformAType()
                informA.regId = stockPositionType.informARegId
                position.informA = informA

                // InformB.informBItem.bRegId
                val informB = factory.createInformBType()
                val informBItem = factory.createInformBTypeItem()
                informB.informBItem = informBItem
                informBItem.bRegId = stockPositionType.informBRegId
                position.informB = informB

                // Product
                position.product = stockPositionType.product

                // Add to mutable list
                it.position.add(position)
            }
        }

        return wayBillDocuments
    }
}

/**
 * Implement NamespacePrefixMapper to control the prefixes that will be assigned to namespaces
 *
 * @author Secundus
 * @since 06.04.2017 17:31
 * @see [JAXB and Namespace Prefixes](https://dzone.com/articles/jaxb-and-namespace-prefixes)
 */
class UtmNamespaceMapper(val map: Map<String, String>) : NamespacePrefixMapper() {

    override fun getPreferredPrefix(p0: String?, p1: String?, p2: Boolean): String? {
        return map.entries
                .firstOrNull { it.value == p0 }
                ?.key ?: p1
    }

    override fun getPreDeclaredNamespaceUris(): Array<String> {
        return map.values.toTypedArray()
    }
}

/**
 * Set address from map extension function
 */
fun OrgAddressType.setAddress(address: Map<String, String>): OrgAddressType {
    with(this) {
        if (address["country"]?.isNotEmpty() ?: false)
            country = address["country"]
        if (address["index"]?.isNotEmpty() ?: false)
            index = address["index"]
        if (address["regionCode"]?.isNotEmpty() ?: false)
            regionCode = address["regionCode"]
        if (address["area"]?.isNotEmpty() ?: false)
            area = address["area"]
        if (address["city"]?.isNotEmpty() ?: false)
            city = address["city"]
        if (address["place"]?.isNotEmpty() ?: false)
            place = address["place"]
        if (address["street"]?.isNotEmpty() ?: false)
            street = address["street"]
        if (address["house"]?.isNotEmpty() ?: false)
            house = address["house"]
        if (address["building"]?.isNotEmpty() ?: false)
            building = address["building"]
        if (address["liter"]?.isNotEmpty() ?: false)
            liter = address["liter"]
        if (address["description"]?.isNotEmpty() ?: false)
            description = address["description"]
    }
    return this
}

/**
 * Get full address from map extension function
 */
fun Map<String, String>.toFullAddress() =
        "${this["index"]}," +
                "${this["area"]}," +
                "${this["city"]}," +
                "${this["place"]}," +
                "${this["street"]}," +
                "${this["house"]}," +
                "${this["building"]}," +
                "${this["liter"]}," +
                "${this["description"]}"