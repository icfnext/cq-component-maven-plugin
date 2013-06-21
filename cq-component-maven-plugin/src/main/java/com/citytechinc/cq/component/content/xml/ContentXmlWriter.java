package com.citytechinc.cq.component.content.xml;

import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.util.Constants;

public class ContentXmlWriter {

	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();

	private ContentXmlWriter() {
	}

	public static final void writeContent(Content content, OutputStream outputStream) throws TransformerException,
		ParserConfigurationException {

		Document contentDocument = makeDocument(content);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource domSource = new DOMSource(contentDocument);

		StreamResult resultStream = new StreamResult(outputStream);

		transformer.transform(domSource, resultStream);
	}

	private static final Document makeDocument(Content content) throws ParserConfigurationException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = document.createElementNS(Constants.JCR_NS_URI, "jcr:root");

		jcrRootElement.setAttributeNS(Constants.JCR_NS_URI, Constants.PRIMARY_TYPE_ATTRIBUTE, content.getPrimaryType());

		if (content.isContainer()) {
			jcrRootElement.setAttributeNS(Constants.CQ_NS_URI, "cq:isContainer", "{Boolean}true");
		} else {
			jcrRootElement.setAttributeNS(Constants.CQ_NS_URI, "cq:isContainer", "{Boolean}false");
		}

		if (content.getResourceSuperType() != null) {
			jcrRootElement.setAttributeNS(Constants.SLING_NS_URI, "sling:resourceSuperType",
				content.getResourceSuperType());
		}

		for (String key : content.getAdditionalProperties().keySet()) {
			jcrRootElement.setAttribute(key, content.getAdditionalProperties().get(key));
		}

		jcrRootElement.setAttributeNS(Constants.JCR_NS_URI, "jcr:title", content.getTitle());

		jcrRootElement.setAttribute("componentGroup", content.getGroup());

		document.appendChild(jcrRootElement);

		return document;
	}

}
