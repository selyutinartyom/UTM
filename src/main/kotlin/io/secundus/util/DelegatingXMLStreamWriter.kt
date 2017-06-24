/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.secundus.util

import javax.xml.namespace.NamespaceContext
import javax.xml.stream.XMLStreamException
import javax.xml.stream.XMLStreamWriter

/**
 * DelegatingXMLStreamWriter from Apache CXF
 *
 * @author Secundus
 * @since 06.04.2017 22:41
 * @see [Remove namespace prefix while JAXB marshalling](http://stackoverflow.com/a/21378770)
 * @see [cxf/DelegatingXMLStreamWriter.java at master · apache/cxf](https://github.com/apache/cxf/blob/master/core/src/main/java/org/apache/cxf/staxutils/DelegatingXMLStreamWriter.java)
 */
open class DelegatingXMLStreamWriter(
        protected val delegate: XMLStreamWriter) : XMLStreamWriter {

    @Throws(XMLStreamException::class)
    override fun close() {
        delegate.close()
    }

    @Throws(XMLStreamException::class)
    override fun flush() {
        delegate.flush()
    }

    override fun getNamespaceContext(): NamespaceContext {
        return delegate.namespaceContext
    }


    @Throws(XMLStreamException::class)
    override fun getPrefix(uri: String): String {
        return delegate.getPrefix(uri)
    }

    @Throws(IllegalArgumentException::class)
    override fun getProperty(name: String): Any {
        return delegate.getProperty(name)
    }

    @Throws(XMLStreamException::class)
    override fun setDefaultNamespace(uri: String) {
        delegate.setDefaultNamespace(uri)
    }

    @Throws(XMLStreamException::class)
    override fun setNamespaceContext(ctx: NamespaceContext) {
        delegate.namespaceContext = ctx
    }

    @Throws(XMLStreamException::class)
    override fun setPrefix(pfx: String, uri: String) {
        delegate.setPrefix(pfx, uri)
    }

    @Throws(XMLStreamException::class)
    override fun writeAttribute(prefix: String, uri: String,
                                local: String, value: String) {
        delegate.writeAttribute(prefix, uri, local, value)
    }

    @Throws(XMLStreamException::class)
    override fun writeAttribute(uri: String, local: String, value: String) {
        delegate.writeAttribute(uri, local, value)
    }

    @Throws(XMLStreamException::class)
    override fun writeAttribute(local: String, value: String) {
        delegate.writeAttribute(local, value)
    }

    @Throws(XMLStreamException::class)
    override fun writeCData(cdata: String) {
        delegate.writeCData(cdata)
    }

    @Throws(XMLStreamException::class)
    override fun writeCharacters(arg0: CharArray, arg1: Int, arg2: Int) {
        delegate.writeCharacters(arg0, arg1, arg2)
    }

    @Throws(XMLStreamException::class)
    override fun writeCharacters(text: String) {
        delegate.writeCharacters(text)
    }

    @Throws(XMLStreamException::class)
    override fun writeComment(text: String) {
        delegate.writeComment(text)
    }

    @Throws(XMLStreamException::class)
    override fun writeDefaultNamespace(uri: String) {
        delegate.writeDefaultNamespace(uri)
    }

    @Throws(XMLStreamException::class)
    override fun writeDTD(dtd: String) {
        delegate.writeDTD(dtd)
    }

    @Throws(XMLStreamException::class)
    override fun writeEmptyElement(prefix: String, local: String, uri: String) {
        delegate.writeEmptyElement(prefix, local, uri)
    }

    @Throws(XMLStreamException::class)
    override fun writeEmptyElement(uri: String, local: String) {
        delegate.writeEmptyElement(uri, local)
    }

    @Throws(XMLStreamException::class)
    override fun writeEmptyElement(localName: String) {
        delegate.writeEmptyElement(localName)
    }

    @Throws(XMLStreamException::class)
    override fun writeEndDocument() {
        delegate.writeEndDocument()
    }

    @Throws(XMLStreamException::class)
    override fun writeEndElement() {
        delegate.writeEndElement()
    }

    @Throws(XMLStreamException::class)
    override fun writeEntityRef(ent: String) {
        delegate.writeEntityRef(ent)
    }

    @Throws(XMLStreamException::class)
    override fun writeNamespace(prefix: String, uri: String) {
        delegate.writeNamespace(prefix, uri)
    }

    @Throws(XMLStreamException::class)
    override fun writeProcessingInstruction(target: String, data: String) {
        delegate.writeProcessingInstruction(target, data)
    }

    @Throws(XMLStreamException::class)
    override fun writeProcessingInstruction(target: String) {
        delegate.writeProcessingInstruction(target)
    }

    @Throws(XMLStreamException::class)
    override fun writeStartDocument() {
        delegate.writeStartDocument()
    }

    @Throws(XMLStreamException::class)
    override fun writeStartDocument(encoding: String, ver: String) {
        delegate.writeStartDocument(encoding, ver)
    }

    @Throws(XMLStreamException::class)
    override fun writeStartDocument(ver: String) {
        delegate.writeStartDocument(ver)
    }

    @Throws(XMLStreamException::class)
    override fun writeStartElement(prefix: String, local: String, uri: String) {
        delegate.writeStartElement(prefix, local, uri)
    }

    @Throws(XMLStreamException::class)
    override fun writeStartElement(uri: String, local: String) {
        delegate.writeStartElement(uri, local)
    }

    @Throws(XMLStreamException::class)
    override fun writeStartElement(local: String) {
        delegate.writeStartElement(local)
    }
}
