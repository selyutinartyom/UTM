//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.replyhistoryshop;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Ответ на запрос о пермещении между регистрами 1 и 2
 * 
 * <p>Java class for ReplyHistoryTransferShop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReplyHistoryTransferShop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ReplyDate" type="{http://fsrar.ru/WEGAIS/Common}DateWTime"/>
 *         &lt;element name="monthReport" type="{http://fsrar.ru/WEGAIS/Common}MonthType"/>
 *         &lt;element name="yearReport" type="{http://fsrar.ru/WEGAIS/Common}YearType"/>
 *         &lt;element name="AlcCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://fsrar.ru/WEGAIS/Common}NoEmptyString">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="History">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocData" type="{http://fsrar.ru/WEGAIS/ReplyHistoryShop}DocDataType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReplyHistoryTransferShop", propOrder = {

})
public class ReplyHistoryTransferShop {

    @XmlElement(name = "ReplyDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar replyDate;
    @XmlElement(required = true)
    protected String monthReport;
    @XmlElement(required = true)
    protected String yearReport;
    @XmlElement(name = "AlcCode", required = true)
    protected String alcCode;
    @XmlElement(name = "History", required = true)
    protected ReplyHistoryTransferShop.History history;

    /**
     * Gets the value of the replyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReplyDate() {
        return replyDate;
    }

    /**
     * Sets the value of the replyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReplyDate(XMLGregorianCalendar value) {
        this.replyDate = value;
    }

    /**
     * Gets the value of the monthReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthReport() {
        return monthReport;
    }

    /**
     * Sets the value of the monthReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthReport(String value) {
        this.monthReport = value;
    }

    /**
     * Gets the value of the yearReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearReport() {
        return yearReport;
    }

    /**
     * Sets the value of the yearReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearReport(String value) {
        this.yearReport = value;
    }

    /**
     * Gets the value of the alcCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlcCode() {
        return alcCode;
    }

    /**
     * Sets the value of the alcCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlcCode(String value) {
        this.alcCode = value;
    }

    /**
     * Gets the value of the history property.
     * 
     * @return
     *     possible object is
     *     {@link ReplyHistoryTransferShop.History }
     *     
     */
    public ReplyHistoryTransferShop.History getHistory() {
        return history;
    }

    /**
     * Sets the value of the history property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplyHistoryTransferShop.History }
     *     
     */
    public void setHistory(ReplyHistoryTransferShop.History value) {
        this.history = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="DocData" type="{http://fsrar.ru/WEGAIS/ReplyHistoryShop}DocDataType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "docData"
    })
    public static class History {

        @XmlElement(name = "DocData")
        protected List<DocDataType> docData;

        /**
         * Gets the value of the docData property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the docData property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDocData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DocDataType }
         * 
         * 
         */
        public List<DocDataType> getDocData() {
            if (docData == null) {
                docData = new ArrayList<DocDataType>();
            }
            return this.docData;
        }

    }

}
