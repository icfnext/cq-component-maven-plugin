package com.citytechinc.cq.component.dialog;

import java.util.List;

public interface SelectionWidget extends Widget {

	public String getSelectionType();

	public List<Option> getOptions();
}
