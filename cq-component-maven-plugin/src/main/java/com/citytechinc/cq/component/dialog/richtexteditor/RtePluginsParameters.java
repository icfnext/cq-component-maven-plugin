package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.DialogElementParameters;

public class RtePluginsParameters extends DialogElementParameters {
	@Override
	public String getPrimaryType() {
		return RtePlugins.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for RtePlugins");
	}

	@Override
	public String getFieldName() {
		return RtePlugins.FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("PrimaryType is Static for RtePlugins");
	}
}
