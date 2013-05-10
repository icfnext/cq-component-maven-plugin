package com.citytechinc.cq.component.dialog;

import java.util.List;
import java.util.Map;

public interface DialogElement {
	public String getPrimaryType();

	public String getNameSpace();

	public String getFieldName();

	public Map<String, String> getAdditionalProperties();

	public List<? extends DialogElement> getContainedElements();

	public void setFieldName(String fieldName);
}
