package com.citytechinc.cq.component.dialog;

import java.util.List;

public interface Container extends DialogElement {

	public List<? extends DialogElement> getContainedElements();

	public String getName();

}
