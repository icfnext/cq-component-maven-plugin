package com.citytechinc.cq.component.dialog.impl;

import java.util.List;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.DialogElement;

public class RtePlugins extends AbstractDialogElement {

	private static final String PRIMARY_TYPE = "nt:unstructured";
	private static final String FIELD_NAME = "rtePlugins";

	public RtePlugins(List<DialogElement> plugins) {
		super(PRIMARY_TYPE, null, FIELD_NAME, null, plugins);
	}

}
