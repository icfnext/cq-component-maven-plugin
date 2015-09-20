/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog;

import javassist.CtMember;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.Listener;

public class DialogFieldConfig {
	private String xtype;
	private String resourceType;
	private String name;
	private String fieldLabel;
	private String fieldName;
	private String fieldDescription;
	private boolean required;
	private boolean hideLabel;
	private String defaultValue;
	private int tab;
	private String tabTitle;
	private Property[] additionalProperties;
	private Listener[] listeners;
	private double ranking;
	private CtMember member;
	private String value;
	private String title;
	private boolean disabled;
	private String cssClass;
	private boolean suppressTouchUI;
	private boolean renderReadOnly;
	private boolean showOnCreate;

	public DialogFieldConfig(DialogField dialogField, CtMember member) {
		this.xtype = dialogField.xtype();
		this.resourceType = dialogField.resourceType();
		this.name = dialogField.name();
		this.fieldLabel = dialogField.fieldLabel();
		this.fieldName = dialogField.fieldName();
		this.fieldDescription = dialogField.fieldDescription();
		this.required = dialogField.required();
		this.hideLabel = dialogField.hideLabel();
		this.defaultValue = dialogField.defaultValue();
		this.tab = dialogField.tab();
		this.tabTitle = dialogField.tabTitle();
		this.additionalProperties = dialogField.additionalProperties();
		this.listeners = dialogField.listeners();
		this.ranking = dialogField.ranking();
		this.member = member;
		this.value = dialogField.value();
		this.title = dialogField.title();
		this.disabled = dialogField.disabled();
		this.cssClass = dialogField.cssClass();
		this.suppressTouchUI = dialogField.suppressTouchUI();
		this.setRenderReadOnly(dialogField.renderReadOnly());
		this.setShowOnCreate(dialogField.showOnCreate());
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public Property[] getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Property[] additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public Listener[] getListeners() {
		return listeners;
	}

	public void setListeners(Listener[] listeners) {
		this.listeners = listeners;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public CtMember getMember() {
		return member;
	}

	public void setMember(CtMember member) {
		this.member = member;
	}

	public String getResourceType() {
		return resourceType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public boolean isSuppressTouchUI() {
		return suppressTouchUI;
	}

	public void setSuppressTouchUI(boolean suppressTouchUI) {
		this.suppressTouchUI = suppressTouchUI;
	}

	public boolean isRenderReadOnly() {
		return renderReadOnly;
	}

	public void setRenderReadOnly(boolean renderReadOnly) {
		this.renderReadOnly = renderReadOnly;
	}

	public boolean isShowOnCreate() {
		return showOnCreate;
	}

	public void setShowOnCreate(boolean showOnCreate) {
		this.showOnCreate = showOnCreate;
	}
}
