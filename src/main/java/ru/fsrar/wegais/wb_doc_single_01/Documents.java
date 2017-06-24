//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.wb_doc_single_01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Owner" type="{http://fsrar.ru/WEGAIS/WB_DOC_SINGLE_01}SenderInfo"/>
 *         &lt;element name="Document" type="{http://fsrar.ru/WEGAIS/WB_DOC_SINGLE_01}DocBody"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" type="{http://fsrar.ru/WEGAIS/Common}NoEmptyString" default="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "owner",
    "document"
})
@XmlRootElement(name = "Documents")
public class Documents {

    @XmlElement(name = "Owner", required = true)
    protected SenderInfo owner;
    @XmlElement(name = "Document", required = true)
    protected DocBody document;
    @XmlAttribute(name = "Version")
    protected String version;

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link SenderInfo }
     *     
     */
    public SenderInfo getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link SenderInfo }
     *     
     */
    public void setOwner(SenderInfo value) {
        this.owner = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link DocBody }
     *     
     */
    public DocBody getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocBody }
     *     
     */
    public void setDocument(DocBody value) {
        this.document = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.0";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
