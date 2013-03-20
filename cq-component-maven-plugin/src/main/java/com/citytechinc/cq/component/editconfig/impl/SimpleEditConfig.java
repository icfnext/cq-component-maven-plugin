package com.citytechinc.cq.component.editconfig.impl;

import java.util.List;

import com.citytechinc.cq.component.editconfig.EditConfig;

public class SimpleEditConfig implements EditConfig {

	private final String title;
	private final List<String> actions;
	private final String dialogMode;
	private final String layout;
	private final String primaryType;

	public SimpleEditConfig(String title, List<String> actions, String dialogMode, String layout, String primaryType) {
		this.title = title;
		this.actions = actions;
		this.dialogMode = dialogMode;
		this.layout = layout;
		this.primaryType = primaryType;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getActions() {
		return actions;
	}

	public String getDialogMode() {
		return dialogMode;
	}

	public String getLayout() {
		return layout;
	}

	public String getPrimaryType() {
		return primaryType;
	}

}
