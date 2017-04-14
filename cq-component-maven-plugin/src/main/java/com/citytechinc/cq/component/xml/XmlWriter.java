/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.xml;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
	private static final List<String> DO_NOT_CALL = Arrays.asList("getNameSpace", "getContainedElements",
		"getFieldName", "getClass", "getRanking");

	/** Widget field types that require special prefixes for correct JCR values. */
	private static final Set<Class> TYPES_WITH_PREFIXES;

	static {
		TYPES_WITH_PREFIXES = new HashSet<Class>();
		TYPES_WITH_PREFIXES.add(Double.class);
		TYPES_WITH_PREFIXES.add(Long.class);
		TYPES_WITH_PREFIXES.add(Boolean.class);
	}

	private XmlWriter() {
	}

	public static void writeXml(XmlElement rootXmlElement, OutputStream outputStream)
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
	private static Element createElement(XmlElement xmlElement, Document document) throws IllegalArgumentException,
		IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Class xmlClass = xmlElement.getClass();
		Method namespaceMethod = xmlClass.getMethod("getNameSpace", null);
		String namespace = (String) namespaceMethod.invoke(xmlElement, null);
		Method fieldNameMethod = xmlClass.getMethod("getFieldName", null);
		String fieldName = sanitize((String) fieldNameMethod.invoke(xmlElement, null));
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
							String key = entry.getKey().toString();
							Object value = entry.getValue();
							if (value instanceof NameSpacedAttribute<?>) {
								NameSpacedAttribute<?> nsa = (NameSpacedAttribute<?>) value;
								setPropertyOnElement(createdElement, nsa.getNameSpace(), nsa.getNameSpacePrefix(),
									StringUtils.isNotEmpty(nsa.getName()) ? nsa.getName() : key, nsa.getValue());
							} else {
								setPropertyOnElement(createdElement, null, null, key, value);
							}
						}
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
								StringUtils.isNotEmpty(nsa.getName()) ? nsa.getName() : methodName, nsaObject);
						}
					} else if (methodReturn instanceof List<?>) {
						List<?> listReturn = (List<?>) methodReturn;
						setPropertyOnElementForMethod(createdElement, null, null, methodName,
							generateStringFromList(listReturn));
					} else if (methodReturn.getClass().isArray()) {
						Object[] arrayReturn = (Object[]) methodReturn;
						setPropertyOnElementForMethod(createdElement, null, null, methodName,
							generateStringFromArray(arrayReturn));
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

	private static Document makeDocument(XmlElement rootXmlElement) throws ParserConfigurationException,
		IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
		NoSuchMethodException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = createElement(rootXmlElement, document);

		document.appendChild(jcrRootElement);

		return document;
	}

	private static String sanitize(String uncleanString) {
		uncleanString = uncleanString.replaceAll("[^A-Za-z0-9:_.-]", "");
		if (uncleanString.matches("^(\\d(.*)|(?i)XML|:(.*)|\\.(.*))")) {
			uncleanString = UUID.randomUUID().toString().replaceAll("-", "").replaceAll("[0-9]", "") + uncleanString;
		}
		return uncleanString;
	}

	private static void setPropertyOnElementForMethod(Element element, String nameSpace, String nameSpacePrefix,
		String methodName, Object methodReturn) {
		String propertyName = null;
		if (methodName.startsWith("get")) {
			propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(3));
		} else if (methodName.startsWith("is")) {
			propertyName = StringUtils.lowercaseFirstLetter(methodName.substring(2));
		} else {
			propertyName = methodName;
		}
		setPropertyOnElement(element, nameSpace, nameSpacePrefix, propertyName, methodReturn);
	}

	private static void setPropertyOnElement(Element element, String nameSpace, String nameSpacePrefix, String name,
		Object value) {
		if (value != null) {
			String propertyValue = value.toString();

			Class clazz = value.getClass();

			if (TYPES_WITH_PREFIXES.contains(clazz)) {
				propertyValue = "{" + clazz.getSimpleName() + "}" + propertyValue;
			}

			if (StringUtils.isEmpty(nameSpace)) {
				element.setAttribute(name, propertyValue);
			} else {
				if (StringUtils.isNotEmpty(nameSpacePrefix)) {
					element.setAttributeNS(nameSpace, nameSpacePrefix + ":" + name, propertyValue);
				} else {
					element.setAttributeNS(nameSpace, name, propertyValue);
				}
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
