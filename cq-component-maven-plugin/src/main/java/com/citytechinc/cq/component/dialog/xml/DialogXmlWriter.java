package com.citytechinc.cq.component.dialog.xml;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.Dialog;
import com.citytechinc.cq.component.global.Constants;

public class DialogXmlWriter {

	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private static final List<String> DO_NOT_CALL = Arrays.asList(new String[] { "getPrimaryType", "getNameSpace",
		"getContainedElements", "getFieldName", "getClass", "getAdditionalProperties" });

	private DialogXmlWriter() {
	}

	public static final void writeDialog(Dialog dialog, OutputStream outputStream) throws ParserConfigurationException,
		TransformerException, IllegalArgumentException, SecurityException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		Document dialogDocument = makeDocument(dialog);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource domSource = new DOMSource(dialogDocument);

		StreamResult resultStream = new StreamResult(outputStream);

		transformer.transform(domSource, resultStream);
	}

	@SuppressWarnings({ "all" })
	private static final Element createElement(DialogElement dialogElement, Document document)
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException,
		NoSuchMethodException {
		Class dialogClass = dialogElement.getClass();
		Method namespaceMethod = dialogClass.getMethod("getNameSpace", null);
		String namespace = (String) namespaceMethod.invoke(dialogElement, null);
		Method fieldNameMethod = dialogClass.getMethod("getFieldName", null);
		String fieldName = sanatize((String) fieldNameMethod.invoke(dialogElement, null));
		Method containedElementsMethod = dialogClass.getMethod("getContainedElements", null);
		List<DialogElement> containedElementsReturn = (List<DialogElement>) containedElementsMethod.invoke(
			dialogElement, null);
		Method primaryTypeMethod = dialogClass.getMethod("getPrimaryType", null);
		String primaryType = (String) primaryTypeMethod.invoke(dialogElement, null);
		Method additionalPropertiesMethod = dialogClass.getMethod("getAdditionalProperties", null);
		Map<String, String> additionalPropertiesReturn = (Map<String, String>) additionalPropertiesMethod.invoke(
			dialogElement, null);
		Element createdElement;
		if (StringUtils.isEmpty(namespace)) {
			createdElement = document.createElement(fieldName);
		} else {
			createdElement = document.createElementNS(namespace, fieldName);
		}
		createdElement.setAttributeNS(Constants.JCR_NS_URI, "jcr:primaryType", primaryType);
		Method[] methods = dialogElement.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (!DO_NOT_CALL.contains(methodName) && (methodName.startsWith("get") || methodName.startsWith("is"))) {
				Object methodReturn = method.invoke(dialogElement, null);
				if (methodReturn != null) {
					String value = methodReturn.toString();
					String propertyName = null;
					if (methodName.startsWith("get")) {
						propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(3));
					} else if (methodName.startsWith("is")) {
						propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(2));
						value = "{Boolean}" + value;
					}
					createdElement.setAttribute(propertyName, value);
				}
			}
		}
		if (additionalPropertiesReturn != null) {
			for (String key : additionalPropertiesReturn.keySet()) {
				createdElement.setAttribute(key, additionalPropertiesReturn.get(key));
			}
		}
		if (containedElementsReturn != null && containedElementsReturn.size() > 0) {
			for (DialogElement de : containedElementsReturn) {
				createdElement.appendChild(createElement(de, document));
			}
		}
		return createdElement;
	}

	private static final Document makeDocument(Dialog dialog) throws ParserConfigurationException,
		IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = createElement(dialog, document);

		document.appendChild(jcrRootElement);

		return document;
	}

	private static final String sanatize(String uncleanString) {
		uncleanString=uncleanString.replaceAll("[^A-Za-z0-9:_.-]","");
		if(uncleanString.matches("^(\\d(.*)|(?i)XML|:(.*)|\\.(.*))")){
			uncleanString=UUID.randomUUID().toString().replaceAll("-","").replaceAll("[0-9]","") + uncleanString;
		}
		return uncleanString;
	}
}
