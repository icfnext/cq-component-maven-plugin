package com.citytechinc.cq.component.dialog.xml;

import java.io.OutputStream;
import java.util.Map;

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

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.MultiValueWidget;
import com.citytechinc.cq.component.dialog.Option;
import com.citytechinc.cq.component.dialog.SelectionWidget;
import com.citytechinc.cq.component.dialog.Tab;
import com.citytechinc.cq.component.dialog.Widget;

public class DialogXmlWriter {

	public static final String JCR_NS_URI = "http://www.jcp.org/jcr/1.0";
	public static final String CQ_NS_URI = "http://www.day.com/jcr/cq/1.0";
	public static final String PRIMARY_TYPE_ATTRIBUTE = "jcr:primaryType";

	private static final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static final TransformerFactory transformerFactory = TransformerFactory.newInstance();

	public static final void writeDialog(Dialog dialog, OutputStream outputStream)
			throws ParserConfigurationException, TransformerException {
		Document dialogDocument = makeDocument(dialog);

		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		DOMSource domSource = new DOMSource(dialogDocument);

		StreamResult resultStream = new StreamResult(outputStream);

		transformer.transform(domSource, resultStream);
	}

	private static final Document makeDocument(Dialog dialog)
			throws ParserConfigurationException {

		DocumentBuilder documentBuilder = docFactory.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		Element jcrRootElement = document.createElementNS(JCR_NS_URI, "jcr:root");

		jcrRootElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, "cq:Dialog");

		//TODO: Title
		jcrRootElement.setAttribute("title", "Test Title");

		jcrRootElement.setAttribute("xtype", "dialog");

		jcrRootElement.setAttribute("activeTab", "0");

		document.appendChild(jcrRootElement);

		jcrRootElement.appendChild(makeElementForContainer(dialog.getWidgetCollection(), document));

		return document;
	}

	private static final Element makeElementForContainer(Container container, Document document) {

		Element containerElement = document.createElement(sanatize(container.getName()));

		containerElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, container.getPrimaryType());

		for (DialogElement curDialogElement : container.getContainedElements()) {
			containerElement.appendChild(makeElementForDialogElement(curDialogElement, document));
		}

		return containerElement;

	}

	private static final Element makeElementForDialogElement(DialogElement element, Document document) {
		if (element instanceof Container) {
			return makeElementForContainer((Container) element, document);
		}
		if (element instanceof Tab) {
			return makeElementForTab((Tab) element, document);
		}
		if (element instanceof Widget) {
			return makeElementForWidget((Widget) element, document);
		}

		return null;
	}

	private static final Element makeElementForTab(Tab element, Document document) {

		Element tabElement = document.createElement(sanatize(element.getName()));

		tabElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, element.getPrimaryType());

		tabElement.setAttribute("title", element.getTitle());

		tabElement.setAttribute("xtype", "panel");

		tabElement.appendChild(makeElementForContainer(element.getWidgetCollection(), document));

		return tabElement;
	}

	private static final Element makeElementForWidget(Widget widget, Document document) {

		Element widgetElement = document.createElement(sanatize(widget.getFieldName()));

		widgetElement.setAttribute("name", widget.getName());
		widgetElement.setAttribute("fieldLabel", widget.getLabel());
		if (widget.hasFieldDescription()) {
			widgetElement.setAttribute("fieldDescription", widget.getFieldDescription());
		}
		//TODO: Base construction on xtype to some extent
		widgetElement.setAttribute("xtype", widget.getXType());
		widgetElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, widget.getPrimaryType());

		if (widget.isRequired()) {
			widgetElement.setAttribute("allowBlank", "{Boolean}false");
		}

		Map<String, String> additionalProperties = widget.getAdditionalProperties();

		if (additionalProperties != null) {
			for (String curKey : additionalProperties.keySet()) {
				widgetElement.setAttribute(curKey, additionalProperties.get(curKey));
			}
		}

		/*
		 * Special handling for selection widgets
		 */
		if (widget instanceof SelectionWidget) {
			return augmentElementForSelectionWidget((SelectionWidget) widget, widgetElement, document);
		}

		/*
		 * Special handling for multi value widgets
		 */
		if (widget instanceof MultiValueWidget) {
			return augmentElementForMultiValueWidget((MultiValueWidget) widget, widgetElement, document);
		}

		return widgetElement;

	}

	private static final Element augmentElementForMultiValueWidget(MultiValueWidget widget, Element widgetElement, Document document) {

		/*
		 * multifield
		 */
		if (widget.isSingleFieldConfiguration()) {
			Element fieldConfigElement = document.createElement("fieldConfig");

			Widget fieldConfig = widget.getFieldConfigurations().get(0);

			fieldConfigElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, fieldConfig.getPrimaryType());
			fieldConfigElement.setAttribute("xtype", fieldConfig.getXType());

			widgetElement.appendChild(fieldConfigElement);
		}
		/*
		 * multicompositefield
		 */
		else {
			//TODO: Implement
		}

		return widgetElement;
	}

	private static final Element augmentElementForSelectionWidget(SelectionWidget widget, Element widgetElement, Document document) {

		widgetElement.setAttribute("type", widget.getSelectionType());

		Element optionsElement = document.createElement("options");
		optionsElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, "cq:WidgetCollection");

		widgetElement.appendChild(optionsElement);

		for (int i=0; i<widget.getOptions().size(); i++) {
			Option curOption = widget.getOptions().get(i);

			Element curOptionElement = document.createElement("option" + i);

			curOptionElement.setAttribute("text", curOption.getText());
			curOptionElement.setAttribute("value", curOption.getValue());
			curOptionElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, curOption.getPrimaryType());

			optionsElement.appendChild(curOptionElement);
		}

		return widgetElement;

	}

	private static final String sanatize(String uncleanString) {
		return uncleanString.replaceAll(" ", "");
	}

}
