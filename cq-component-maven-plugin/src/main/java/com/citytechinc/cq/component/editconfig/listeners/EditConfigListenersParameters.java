package com.citytechinc.cq.component.editconfig.listeners;

import java.util.Map;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigListenersParameters extends DefaultXmlElementParameters {
	private static final String EDIT_CONFIG_LISENERS_PRIMARY_TYPE = "cq:EditListenersConfig";
	private Map<String, String> listeners;

	public Map<String, String> getListeners() {
		return listeners;
	}

	public void setListeners(Map<String, String> listeners) {
		this.listeners = listeners;
	}

	@Override
	public String getNameSpace() {
		return Constants.CQ_NS_URI;
	}

	@Override
	public void setNameSpace(String nameSpace) {
		throw new UnsupportedOperationException("nameSpace is Static for EditConfigListeners");
	}

	@Override
	public String getFieldName() {
		return "cq:listeners";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfigListeners");
	}

	@Override
	public String getPrimaryType() {
		return EDIT_CONFIG_LISENERS_PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigListeners");
	}

}
