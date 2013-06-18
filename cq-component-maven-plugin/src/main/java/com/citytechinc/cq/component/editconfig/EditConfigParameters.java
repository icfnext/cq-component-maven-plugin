package com.citytechinc.cq.component.editconfig;

import java.util.List;

import com.citytechinc.cq.component.xml.XmlElementParameters;

public class EditConfigParameters extends XmlElementParameters {
	private static final String EDIT_CONFIG_PRIMARY_TYPE = "cq:EditConfig";
	private List<String> actions;
	private String dialogMode;
	private String layout;
	private String emptyText;
	private Boolean inherit;

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public String getDialogMode() {
		return dialogMode;
	}

	public void setDialogMode(String dialogMode) {
		this.dialogMode = dialogMode;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getEmptyText() {
		return emptyText;
	}

	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	public Boolean getInherit() {
		return inherit;
	}

	public void setInherit(Boolean inherit) {
		this.inherit = inherit;
	}

	@Override
	public String getFieldName() {
		return "jcr:root";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfig");
	}

	@Override
	public String getPrimaryType() {
		return EDIT_CONFIG_PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfig");
	}
}
