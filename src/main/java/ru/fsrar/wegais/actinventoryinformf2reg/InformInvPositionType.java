//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.actinventoryinformf2reg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InformInvPositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InformInvPositionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Identity" type="{http://fsrar.ru/WEGAIS/Common}IdentityType"/>
 *         &lt;element name="InformF1RegId" type="{http://fsrar.ru/WEGAIS/Common}NoEmptyString50"/>
 *         &lt;element name="InformF2">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="InformF2Item" type="{http://fsrar.ru/WEGAIS/ActInventoryInformF2Reg}InformInvF2RegItem" maxOccurs="unbounded"/>
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
@XmlType(name = "InformInvPositionType", propOrder = {

})
public class InformInvPositionType {

    @XmlElement(name = "Identity", required = true)
    protected String identity;
    @XmlElement(name = "InformF1RegId", required = true)
    protected String informF1RegId;
    @XmlElement(name = "InformF2", required = true)
    protected InformInvPositionType.InformF2 informF2;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentity(String value) {
        this.identity = value;
    }

    /**
     * Gets the value of the informF1RegId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformF1RegId() {
        return informF1RegId;
    }

    /**
     * Sets the value of the informF1RegId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformF1RegId(String value) {
        this.informF1RegId = value;
    }

    /**
     * Gets the value of the informF2 property.
     * 
     * @return
     *     possible object is
     *     {@link InformInvPositionType.InformF2 }
     *     
     */
    public InformInvPositionType.InformF2 getInformF2() {
        return informF2;
    }

    /**
     * Sets the value of the informF2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformInvPositionType.InformF2 }
     *     
     */
    public void setInformF2(InformInvPositionType.InformF2 value) {
        this.informF2 = value;
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
     *         &lt;element name="InformF2Item" type="{http://fsrar.ru/WEGAIS/ActInventoryInformF2Reg}InformInvF2RegItem" maxOccurs="unbounded"/>
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
        "informF2Item"
    })
    public static class InformF2 {

        @XmlElement(name = "InformF2Item", required = true)
        protected List<InformInvF2RegItem> informF2Item;

        /**
         * Gets the value of the informF2Item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the informF2Item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInformF2Item().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InformInvF2RegItem }
         * 
         * 
         */
        public List<InformInvF2RegItem> getInformF2Item() {
            if (informF2Item == null) {
                informF2Item = new ArrayList<InformInvF2RegItem>();
            }
            return this.informF2Item;
        }

    }

}