//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.19 at 01:00:49 AM MSK 
//


package ru.fsrar.wegais.requestaddfproducer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.fsrar.wegais.requestaddfproducer package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddTSTypeTSNUM_QNAME = new QName("http://fsrar.ru/WEGAIS/RequestAddFProducer", "TSNUM");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.fsrar.wegais.requestaddfproducer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestAddFProducer }
     * 
     */
    public RequestAddFProducer createRequestAddFProducer() {
        return new RequestAddFProducer();
    }

    /**
     * Create an instance of {@link OrgInfoForeignAddType }
     * 
     */
    public OrgInfoForeignAddType createOrgInfoForeignAddType() {
        return new OrgInfoForeignAddType();
    }

    /**
     * Create an instance of {@link AddFOType }
     * 
     */
    public AddFOType createAddFOType() {
        return new AddFOType();
    }

    /**
     * Create an instance of {@link AddTSType }
     * 
     */
    public AddTSType createAddTSType() {
        return new AddTSType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fsrar.ru/WEGAIS/RequestAddFProducer", name = "TSNUM", scope = AddTSType.class)
    public JAXBElement<String> createAddTSTypeTSNUM(String value) {
        return new JAXBElement<String>(_AddTSTypeTSNUM_QNAME, String.class, AddTSType.class, value);
    }

}
