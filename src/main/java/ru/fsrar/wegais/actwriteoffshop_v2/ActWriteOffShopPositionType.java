//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.actwriteoffshop_v2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.fsrar.wegais.commonenum.MarkCodeInfoType;
import ru.fsrar.wegais.productref_v2.ProductInfoV2;


/**
 * Позиция
 * 
 * <p>Java class for ActWriteOffShopPositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActWriteOffShopPositionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="Identity" type="{http://fsrar.ru/WEGAIS/Common}IdentityType"/>
 *         &lt;element name="Product" type="{http://fsrar.ru/WEGAIS/ProductRef_v2}ProductInfo_v2"/>
 *         &lt;element name="Quantity" type="{http://fsrar.ru/WEGAIS/Common}PositiveDecimalType"/>
 *         &lt;element name="MarkCodeInfo" type="{http://fsrar.ru/WEGAIS/CommonEnum}MarkCodeInfoType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActWriteOffShopPositionType", propOrder = {

})
public class ActWriteOffShopPositionType {

    @XmlElement(name = "Identity", required = true)
    protected String identity;
    @XmlElement(name = "Product", required = true)
    protected ProductInfoV2 product;
    @XmlElement(name = "Quantity", required = true)
    protected BigDecimal quantity;
    @XmlElement(name = "MarkCodeInfo")
    protected MarkCodeInfoType markCodeInfo;

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
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link ProductInfoV2 }
     *     
     */
    public ProductInfoV2 getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductInfoV2 }
     *     
     */
    public void setProduct(ProductInfoV2 value) {
        this.product = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the markCodeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MarkCodeInfoType }
     *     
     */
    public MarkCodeInfoType getMarkCodeInfo() {
        return markCodeInfo;
    }

    /**
     * Sets the value of the markCodeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarkCodeInfoType }
     *     
     */
    public void setMarkCodeInfo(MarkCodeInfoType value) {
        this.markCodeInfo = value;
    }

}
