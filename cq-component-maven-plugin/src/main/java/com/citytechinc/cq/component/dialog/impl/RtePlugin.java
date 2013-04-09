package com.citytechinc.cq.component.dialog.impl;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.DialogElement;

public class RtePlugin extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String features;

	public RtePlugin(String fieldName, List<String> featuresList) {

		this(fieldName, "[" + StringUtils.join(featuresList.iterator(), ",") + "]");

	}

	public RtePlugin(String fieldName, String features) {

		this(fieldName, features, null);

	}

	public RtePlugin(String fieldName, String features, List<? extends DialogElement> containedElements) {

		super(PRIMARY_TYPE, null, fieldName, null, containedElements);

		this.features = features;
	}

	public String getFeatures() {
		return features;
	}

}
