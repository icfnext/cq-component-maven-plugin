package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class RteParaFormatParameters extends DefaultDialogElementParameters {
	private String tag;
	private String description;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getPrimaryType() {
		return RteParaFormat.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for RteParaFormat");
	}
}
