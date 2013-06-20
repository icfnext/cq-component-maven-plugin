package com.citytechinc.cq.component.editconfig.actionconfigs;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public class EditConfigActionConfigParameters extends XmlElementParameters {
	private String text;
	private String handler;
	private String xtype;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	@Override
	public String getPrimaryType() {
		return Constants.NT_UNSTRUCTURED;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigActionConfig");
	}
}
