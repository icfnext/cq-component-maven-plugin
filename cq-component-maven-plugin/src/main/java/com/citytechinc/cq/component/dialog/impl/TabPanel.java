package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.DialogElement;

public class TabPanel extends AbstractDialogElement {
	private static final String DEFAUL_FIELD_NAME="tabs";
	private static final String PRIMARY_TYPE="cq:TabPanel";

	public TabPanel() {
		this(new ArrayList<DialogElement>());
	}

	public TabPanel(DialogElement element) {
		this(Arrays.asList(new DialogElement[]{element}));
	}

	public TabPanel(List<DialogElement> elements) {
		super(PRIMARY_TYPE, null, DEFAUL_FIELD_NAME, null, elements);
	}

}
