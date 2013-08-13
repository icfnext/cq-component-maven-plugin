package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.annotations.Listener;

public class ListenersParameters extends DialogElementParameters {
	public static final String FIELD_NAME = "listeners";
	public static final String PRIMARY_TYPE = "nt:unstructured";
	private Listener[] listenerAnnotations;

	public Listener[] getListenerAnnotations() {
		return listenerAnnotations;
	}

	public void setListenerAnnotations(Listener[] listenerAnnotations) {
		this.listenerAnnotations = listenerAnnotations;
	}

	@Override
	public String getPrimaryType() {
		return PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for ListenerParameters");
	}

	@Override
	public String getFieldName() {
		return FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("FieldName is Static for ListenerParameters");
	}
}
