package com.citytechinc.cq.component;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class AspectRatiosParameters extends DefaultXmlElementParameters {
	private static final String PRIMARY_TYPE = "nt:unstructured";
	private static final String FIELD_NAME = "aspectRatios";

	@Override
	public String getFieldName() {
		return FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("FieldName is static for AspectRatios");
	}

	@Override
	public String getPrimaryType() {
		return PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for AspectRatios");
	}
}
