package com.citytechinc.cq.component.xml;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

public class XmlWriter {

	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private static final List<String> DO_NOT_CALL = Arrays.asList(new String[] { "getNameSpace",
		"getContainedElements", "getFieldName", "getClass", "getRanking" });

	private XmlWriter() {
	}

	public static final void writeXml(XmlElement rootXmlElement, OutputStream outputStream)
		throws ParserConfigurationException, TransformerException, IllegalArgumentException, SecurityException,
		IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Document xmlDocument = makeDocument(rootXmlElement);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource domSource = new DOMSource(xmlDocument);

		StreamResult resultStream = new StreamResult(outputStream);

		transformer.transform(domSource, resultStream);
	}

	@SuppressWarnings({ "all" })
	private static final Element createElement(XmlElement xmlElement, Document document)
		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException,
		NoSuchMethodException {
		Class xmlClass = xmlElement.getClass();
		Method namespaceMethod = xmlClass.getMethod("getNameSpace", null);
		String namespace = (String) namespaceMethod.invoke(xmlElement, null);
		Method fieldNameMethod = xmlClass.getMethod("getFieldName", null);
		String fieldName = sanatize((String) fieldNameMethod.invoke(xmlElement, null));
		Method containedElementsMethod = xmlClass.getMethod("getContainedElements", null);
		List<XmlElement> containedElementsReturn = (List<XmlElement>) containedElementsMethod.invoke(xmlElement, null);
		Element createdElement;
		if (StringUtils.isEmpty(namespace)) {
			createdElement = document.createElement(fieldName);
		} else {
			createdElement = document.createElementNS(namespace, fieldName);
		}
		Method[] methods = xmlElement.getClass().getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (!DO_NOT_CALL.contains(methodName) && (methodName.startsWith("get") || methodName.startsWith("is"))) {
				Object methodReturn = method.invoke(xmlElement, null);
				if (methodReturn != null) {
					if (methodReturn instanceof Map<?, ?>) {
						Map<?, ?> returnMap = (Map<?, ?>) methodReturn;
						for (Entry<?, ?> entry : returnMap.entrySet()) {
							setPropertyOnElement(createdElement, null, null, entry.getKey().toString(),
								entry.getValue());
						}
					} else if (methodReturn instanceof List<?>) {
						List<?> listReturn = (List<?>) methodReturn;
						setPropertyOnElementForMethod(createdElement, null, null, methodName,
							generateStringFromList(listReturn));
					} else if (methodReturn.getClass().isArray()) {
						Object[] arrayReturn = (Object[]) methodReturn;
						setPropertyOnElementForMethod(createdElement, null, null, methodName,
							generateStringFromArray(arrayReturn));
					} else if (methodReturn instanceof NameSpacedAttribute<?>) {
						NameSpacedAttribute<?> nsa = (NameSpacedAttribute<?>) methodReturn;
						Object nsaObject = nsa.getValue();
						if (nsaObject != null) {
    						if (nsaObject instanceof List<?>) {
    							List<?> listReturn = (List<?>) nsaObject;
    							nsaObject = generateStringFromList(listReturn);
    						} else if (nsaObject.getClass().isArray()) {
    							Object[] arrayReturn = (Object[]) nsaObject;
    							nsaObject = generateStringFromArray(arrayReturn);
    						}
    						setPropertyOnElementForMethod(createdElement, nsa.getNameSpace(), nsa.getNameSpacePrefix(),
    							methodName, nsaObject);
						}
					} else {
						setPropertyOnElementForMethod(createdElement, null, null, methodName, methodReturn);
					}
				}
			}
		}
		if (containedElementsReturn != null && containedElementsReturn.size() > 0) {
			for (XmlElement de : containedElementsReturn) {
				createdElement.appendChild(createElement(de, document));
			}
		}
		return createdElement;
	}

	private static final Document makeDocument(XmlElement rootXmlElement) throws ParserConfigurationException,
		IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = createElement(rootXmlElement, document);

		document.appendChild(jcrRootElement);

		return document;
	}

	private static final String sanatize(String uncleanString) {
		uncleanString = uncleanString.replaceAll("[^A-Za-z0-9:_.-]", "");
		if (uncleanString.matches("^(\\d(.*)|(?i)XML|:(.*)|\\.(.*))")) {
			uncleanString = UUID.randomUUID().toString().replaceAll("-", "").replaceAll("[0-9]", "") + uncleanString;
		}
		return uncleanString;
	}

	private static final void setPropertyOnElementForMethod(Element element, String nameSpace, String nameSpacePrefix,
		String methodName, Object methodReturn) {
		String propertyName = null;
		if (methodName.startsWith("get")) {
			propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(3));
		} else if (methodName.startsWith("is")) {
			propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(2));
		}
		setPropertyOnElement(element, nameSpace, nameSpacePrefix, propertyName, methodReturn);
	}

	private static final void setPropertyOnElement(Element element, String nameSpace, String nameSpacePrefix,
		String name, Object value) {
		if (value != null) {
		    String propertyValue = value.toString();
			if (value instanceof Boolean) {
			    propertyValue = "{Boolean}" + propertyValue;
			}
			if (StringUtils.isEmpty(nameSpace)) {
				element.setAttribute(name, propertyValue);
			} else {
				element.setAttributeNS(nameSpace, nameSpacePrefix + ":" + name, propertyValue);
			}
		}
	}

	private static String generateStringFromList(List<?> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(StringUtils.join(list.toArray(), ",")).append("]");
		return sb.toString();
	}

	private static String generateStringFromArray(Object[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(StringUtils.join(array, ",")).append("]");
		return sb.toString();
	}
}
