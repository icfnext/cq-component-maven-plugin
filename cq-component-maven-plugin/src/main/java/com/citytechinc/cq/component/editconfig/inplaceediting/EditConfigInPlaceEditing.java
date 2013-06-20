package com.citytechinc.cq.component.editconfig.inplaceediting;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

public class EditConfigInPlaceEditing extends AbstractXmlElement {
	private final boolean active;
	private final String configPath;
	private final String editorType;

	public EditConfigInPlaceEditing(EditConfigInPlaceEditingParameters parameters) {
		super(parameters);
		active = parameters.isActive();
		configPath = parameters.getConfigPath();
		editorType = parameters.getEditorType();
	}

	public boolean isActive() {
		return active;
	}

	public String getConfigPath() {
		return configPath;
	}

	public String getEditorType() {
		return editorType;
	}
}
