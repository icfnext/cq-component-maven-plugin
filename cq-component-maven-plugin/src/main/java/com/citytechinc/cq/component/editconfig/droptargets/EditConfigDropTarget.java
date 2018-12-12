package com.citytechinc.cq.component.editconfig.droptargets;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

public class EditConfigDropTarget extends AbstractXmlElement {
	private final String[] accept;
	private final String[] groups;
	private final String propertyName;

	public EditConfigDropTarget(EditConfigDropTargetParameters parameters) {
		super(parameters);
		accept = parameters.getAccept();
		groups = parameters.getGroups();
		propertyName = parameters.getPropertyName();
	}

	public String[] getAccept() {
		return accept;
	}

	public String[] getGroups() {
		return groups;
	}

	public String getPropertyName() {
		return propertyName;
	}

}
