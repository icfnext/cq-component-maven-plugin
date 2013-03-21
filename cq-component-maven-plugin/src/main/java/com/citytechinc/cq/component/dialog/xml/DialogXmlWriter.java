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

import org.codehaus.plexus.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Html5SmartImageWidget;
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
		if(element instanceof Html5SmartImageWidget){
			Element elementReturn=null;
			Html5SmartImageWidget smartImage=(Html5SmartImageWidget)element;
			if(smartImage.isTab()){
				elementReturn=makeElementForTab((Tab) element, document);
			}else{
				elementReturn=makeElementForWidget((Widget) element, document);
			}
			return augmentElementForHtml5SmartImageWidget(smartImage,elementReturn);
		}
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

	private static Element augmentElementForHtml5SmartImageWidget(Html5SmartImageWidget smartImage, Element element) {
		element.setAttribute("xtype", smartImage.getXType());
		element.setAttribute("name", smartImage.getName());
		element.setAttribute("disableFlush", "{Boolean}"+smartImage.isDisableFlush());
		element.setAttribute("disableInfo", "{Boolean}"+smartImage.isDisableInfo());
		element.setAttribute("disableZoom", "{Boolean}"+smartImage.isDisableZoom());
		if(!StringUtils.isEmpty(smartImage.getCropParameter())){
			element.setAttribute("cropParameter", smartImage.getCropParameter());
		}
		if(!StringUtils.isEmpty(smartImage.getFileNameParameter())){
			element.setAttribute("fileNameParameter", smartImage.getName()+"/"+smartImage.getFileNameParameter());
		}
		if(!StringUtils.isEmpty(smartImage.getFileReferenceParameter())){
			element.setAttribute("fileReferenceParameter",smartImage.getName()+"/"+smartImage.getFileReferenceParameter());
		}
		if(!StringUtils.isEmpty(smartImage.getMapParameter())){
			element.setAttribute("mapParameter", smartImage.getName()+"/"+smartImage.getMapParameter());
		}
		if(!StringUtils.isEmpty(smartImage.getRotateParameter())){
			element.setAttribute("rotateParameter", smartImage.getName()+"/"+smartImage.getRotateParameter());
		}
		if(!StringUtils.isEmpty(smartImage.getUploadUrl())){
			element.setAttribute("uploadUrl", smartImage.getUploadUrl());
		}
		if(!StringUtils.isEmpty(smartImage.getDdGroups())){
			element.setAttribute("ddGroups", smartImage.getDdGroups());
		}
		element.setAttribute("allowUpload", "{Boolean}"+smartImage.isAllowUpload());
		if (smartImage.isRequired()) {
			element.setAttribute("allowBlank", "{Boolean}false");
		}
		if(smartImage.getHeight()!=0){
			element.setAttribute("height", Integer.toString(smartImage.getHeight()));
		}
		element.setAttribute("fieldLabel", smartImage.getLabel());
		if (smartImage.hasFieldDescription()) {
			element.setAttribute("fieldDescription", smartImage.getFieldDescription());
		}
		return element;
	}

	private static final Element makeElementForTab(Tab element, Document document) {

		Element tabElement = document.createElement(sanatize(element.getName()));

		tabElement.setAttributeNS(JCR_NS_URI, PRIMARY_TYPE_ATTRIBUTE, element.getPrimaryType());

		tabElement.setAttribute("title", element.getTitle());

		tabElement.setAttribute("xtype", "panel");
		
		if(element.getWidgetCollection()!=null){
			tabElement.appendChild(makeElementForContainer(element.getWidgetCollection(), document));
		}

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
		
		if(widget.getDefaultValue()!=null){
			widgetElement.setAttribute("defaultValue", widget.getDefaultValue());
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
		return uncleanString.replaceAll(" ", "").replaceAll("./", "");
	}

}
