package com.citytechinc.cq.component.dialog.impl;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Option;

public class SimpleOption implements Option {

	private final String primaryType;
	private final String text;
	private final String value;

	public SimpleOption(String text, String value) {

		this.primaryType = "nt:unstructured";

		this.text = text;
		this.value = value;

	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}
	
	public List<? extends DialogElement> getContainedElements() {
		return null;
	}

	public String getNameSpace() {
		return null;
	}

	public String getName() {
		return null;
	}
	
	public String getFieldName(){
		return StringUtils.deleteWhitespace(value);
	}

}
