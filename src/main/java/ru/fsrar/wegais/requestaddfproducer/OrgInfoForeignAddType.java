//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.requestaddfproducer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Иностранные организации
 * 
 * <p>Java class for OrgInfoForeignAddType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrgInfoForeignAddType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="FO" type="{http://fsrar.ru/WEGAIS/RequestAddFProducer}AddFOType"/>
 *         &lt;element name="TS" type="{http://fsrar.ru/WEGAIS/RequestAddFProducer}AddTSType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrgInfoForeignAddType", propOrder = {
    "fo",
    "ts"
})
public class OrgInfoForeignAddType {

    @XmlElement(name = "FO")
    protected AddFOType fo;
    @XmlElement(name = "TS")
    protected AddTSType ts;

    /**
     * Gets the value of the fo property.
     * 
     * @return
     *     possible object is
     *     {@link AddFOType }
     *     
     */
    public AddFOType getFO() {
        return fo;
    }

    /**
     * Sets the value of the fo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddFOType }
     *     
     */
    public void setFO(AddFOType value) {
        this.fo = value;
    }

    /**
     * Gets the value of the ts property.
     * 
     * @return
     *     possible object is
     *     {@link AddTSType }
     *     
     */
    public AddTSType getTS() {
        return ts;
    }

    /**
     * Sets the value of the ts property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTSType }
     *     
     */
    public void setTS(AddTSType value) {
        this.ts = value;
    }

}
