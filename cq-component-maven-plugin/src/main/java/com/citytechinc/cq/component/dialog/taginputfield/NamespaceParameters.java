package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class NamespaceParameters extends DefaultDialogElementParameters {
	private String name;
	private String maximum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	@Override
	public String getPrimaryType() {
		return Namespace.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for Namespace");
	}
}
