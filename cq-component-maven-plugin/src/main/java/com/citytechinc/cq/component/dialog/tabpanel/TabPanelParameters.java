package com.citytechinc.cq.component.dialog.tabpanel;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.DialogElementParameters;

public class TabPanelParameters extends DialogElementParameters {
	private static final String DEFAULT_FIELD_NAME = "tabs";
	private static final String PRIMARY_TYPE = "cq:TabPanel";

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for HiddenWidget");
	}

	@Override
	public String getPrimaryType() {
		return PRIMARY_TYPE;
	}

	@Override
	public String getFieldName() {
		if (StringUtils.isEmpty(fieldName)) {
			return DEFAULT_FIELD_NAME;
		}
		return fieldName;
	}

	@Override
	public List<? extends DialogElement> getContainedElements() {
		if (containedElements == null) {
			containedElements = new ArrayList<DialogElement>();
		}
		return containedElements;
	}
}
