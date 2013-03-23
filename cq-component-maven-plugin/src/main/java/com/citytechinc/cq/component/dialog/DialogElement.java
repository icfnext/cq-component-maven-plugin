package com.citytechinc.cq.component.dialog;

import java.util.List;

public interface DialogElement {
	public String getPrimaryType();
	public String getNameSpace();
	public List<? extends DialogElement> getContainedElements();
	public String getFieldName();
}
