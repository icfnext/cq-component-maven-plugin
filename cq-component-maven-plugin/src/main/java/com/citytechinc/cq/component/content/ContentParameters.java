/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.content;

import java.util.List;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class ContentParameters extends DefaultXmlElementParameters {

	private static final String CONTENT_PRIMARY_TYPE = "cq:Component";

	private List<String> allowedChildren;
	private List<String> allowedParents;
	private String componentGroup;
	private String cellName;
	private Boolean isContainer;
	private Boolean noDecoration;
	private String templatePath;
	private String dialogPath;
	private String created;
	private String description;
	private String title;
	private String resourceSuperType;
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getAllowedChildren() {
		return allowedChildren;
	}

	public void setAllowedChildren(List<String> allowedChildren) {
		this.allowedChildren = allowedChildren;
	}

	public List<String> getAllowedParents() {
		return allowedParents;
	}

	public void setAllowedParents(List<String> allowedParents) {
		this.allowedParents = allowedParents;
	}

	public String getComponentGroup() {
		return componentGroup;
	}

	public void setComponentGroup(String componentGroup) {
		this.componentGroup = componentGroup;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public Boolean getIsContainer() {
		return isContainer;
	}

	public void setIsContainer(Boolean isContainer) {
		this.isContainer = isContainer;
	}

	public Boolean getNoDecoration() {
		return noDecoration;
	}

	public void setNoDecoration(Boolean noDecoration) {
		this.noDecoration = noDecoration;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getDialogPath() {
		return dialogPath;
	}

	public void setDialogPath(String dialogPath) {
		this.dialogPath = dialogPath;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResourceSuperType() {
		return resourceSuperType;
	}

	public void setResourceSuperType(String resourceSuperType) {
		this.resourceSuperType = resourceSuperType;
	}

	@Override
	public String getFieldName() {
		return "jcr:root";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfig");
	}

	@Override
	public String getPrimaryType() {
		return CONTENT_PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfig");
	}

}
