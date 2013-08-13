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
package com.citytechinc.cq.component.editconfig.inplaceediting;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public class EditConfigInPlaceEditingParameters extends XmlElementParameters {
	private static final String EDIT_CONFIG_IN_PLACE_EDITING_PRIMARY_TYPE = "cq:InplaceEditingConfig";

	private boolean active;
	private String configPath;
	private String editorType;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	@Override
	public String getNameSpace() {
		return Constants.CQ_NS_URI;
	}

	@Override
	public void setNameSpace(String nameSpace) {
		throw new UnsupportedOperationException("nameSpace is Static for EditConfigInPlaceEditingParameters");
	}

	@Override
	public String getFieldName() {
		return "cq:inplaceEditing";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfigInPlaceEditingParameters");
	}

	@Override
	public String getPrimaryType() {
		return EDIT_CONFIG_IN_PLACE_EDITING_PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigInPlaceEditingParameters");
	}
}
