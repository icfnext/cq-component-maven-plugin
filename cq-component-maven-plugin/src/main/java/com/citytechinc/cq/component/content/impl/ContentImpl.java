package com.citytechinc.cq.component.content.impl;

import com.citytechinc.cq.component.content.Content;

public class ContentImpl implements Content {

	private final Boolean isContainer;
	private final String primaryType;
	private final String title;
	private final String group;
	private final String resourceSuperType;

	public ContentImpl(String title, String group, String resourceSuperType,Boolean isContainer) {
		this.title = title;
		this.group = group;
		this.isContainer = isContainer;
		this.resourceSuperType=resourceSuperType;
		this.primaryType = "cq:Component";
	}

	public Boolean isContainer() {
		return isContainer;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getTitle() {
		return title;
	}

	public String getGroup() {
		return group;
	}

	public String getResourceSuperType() {
		return resourceSuperType;
	}

}
