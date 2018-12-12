package com.citytechinc.cq.component.editconfig.droptargets;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigDropTargetParameters extends DefaultXmlElementParameters {
	private String[] accept;
	private String[] groups;
	private String propertyName;

	public String[] getAccept() {
		return accept;
	}

	public void setAccept(String[] accept) {
		this.accept = accept;
	}

	public String[] getGroups() {
		return groups;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getPrimaryType() {
		return "cq:DropTargetConfig";
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigActionConfig");
	}
}
