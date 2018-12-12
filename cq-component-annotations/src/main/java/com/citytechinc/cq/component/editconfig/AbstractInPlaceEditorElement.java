package com.citytechinc.cq.component.editconfig;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.XmlElement;

public abstract class AbstractInPlaceEditorElement extends AbstractXmlElement implements InPlaceEditorElement {
	private final Boolean active;
	private final String editorType;
	private final String type;
	private final String title;
	private ConfigElement configElement;

	public AbstractInPlaceEditorElement(InPlaceEditorParameters parameters) {
		super(parameters);
		this.active = parameters.isActive();
		if (active != null) {
			this.editorType = parameters.getEditorType();
			this.type = null;
		} else {
			this.type = parameters.getEditorType();
			this.editorType = null;
		}
		this.title = StringUtils.isNotEmpty(parameters.getTitle()) ? parameters.getTitle() : parameters.getFieldName();
		this.configElement = parameters.getConfigElement();
	}

	public Boolean getActive() {
		return active;
	}

	public String getEditorType() {
		return editorType;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public ConfigElement getConfigElement() {
		return configElement;
	}

	public void setConfigElement(ConfigElement configElement) {
		this.configElement = configElement;
	}

	public void setContainedElements(List<XmlElement> containedElements) {
		this.containedElements = containedElements;
	}
}
