package com.citytechinc.cq.component.dialog;

import java.util.List;

public interface MultiValueWidget extends Widget {

	public List<Widget> getFieldConfigurations();

	public Boolean isSingleFieldConfiguration();

}
