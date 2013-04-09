package com.citytechinc.cq.component.editconfig.xml;

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

import org.codehaus.plexus.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.global.Constants;

public class EditConfigXmlWriter {

	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();

	private EditConfigXmlWriter() {
	}

	public static final void writeEditConfig(EditConfig editConfig, OutputStream outputStream)
		throws TransformerException, ParserConfigurationException {

		Document contentDocument = makeDocument(editConfig);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource domSource = new DOMSource(contentDocument);

		StreamResult resultStream = new StreamResult(outputStream);

		transformer.transform(domSource, resultStream);

	}

	private static final Document makeDocument(EditConfig editConfig) throws ParserConfigurationException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = document.createElementNS(Constants.JCR_NS_URI, "jcr:root");

		jcrRootElement.setAttributeNS(Constants.JCR_NS_URI, Constants.PRIMARY_TYPE_ATTRIBUTE,
			editConfig.getPrimaryType());

		jcrRootElement.setAttributeNS(Constants.CQ_NS_URI, "cq:dialogMode", editConfig.getDialogMode());
		jcrRootElement.setAttributeNS(Constants.CQ_NS_URI, "cq:layout", editConfig.getLayout());
		jcrRootElement.setAttributeNS(Constants.CQ_NS_URI, "cq:actions", getActionsStringForEditConfig(editConfig));
		if (editConfig.getListeners() != null) {
			Element cqListeners = document.createElementNS(Constants.CQ_NS_URI, "cq:listeners");
			cqListeners.setAttributeNS(Constants.JCR_NS_URI, "jcr:primaryType", "cq:EditListenersConfig");
			for (String key : editConfig.getListeners().keySet()) {
				cqListeners.setAttribute(key, editConfig.getListeners().get(key));
			}
			jcrRootElement.appendChild(cqListeners);
		}
		document.appendChild(jcrRootElement);

		return document;
	}

	private static final String getActionsStringForEditConfig(EditConfig editConfig) {
		StringBuilder retStringBuilder = new StringBuilder();

		retStringBuilder.append('[');

		retStringBuilder.append(StringUtils.join(editConfig.getActions().toArray(), ","));

		retStringBuilder.append(']');

		return retStringBuilder.toString();
	}

}
