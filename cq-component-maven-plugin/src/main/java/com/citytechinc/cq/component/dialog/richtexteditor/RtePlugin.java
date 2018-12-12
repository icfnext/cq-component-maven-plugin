package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class RtePlugin extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String features;

	public RtePlugin(RtePluginParameters parameters) {

		super(parameters);

		this.features = parameters.getFeatures();
	}

	public String getFeatures() {
		return features;
	}

}
