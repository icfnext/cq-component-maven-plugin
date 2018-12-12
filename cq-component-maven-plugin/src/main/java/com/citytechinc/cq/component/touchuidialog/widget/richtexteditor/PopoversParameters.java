package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class PopoversParameters extends DefaultTouchUIDialogElementParameters {
	@Override
	public String getPrimaryType() {
		return Popovers.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for Popover");
	}

	@Override
	public String getFieldName() {
		return Popovers.FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("PrimaryType is Static for Popover");
	}
}
