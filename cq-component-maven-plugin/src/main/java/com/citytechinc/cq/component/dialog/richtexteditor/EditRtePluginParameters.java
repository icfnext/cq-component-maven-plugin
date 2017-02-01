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
package com.citytechinc.cq.component.dialog.richtexteditor;

public class EditRtePluginParameters extends RtePluginParameters {
	public static final String EDIT = "edit";
	private String defaultPasteMode;

	public String getDefaultPasteMode() {
		return defaultPasteMode;
	}

	public void setDefaultPasteMode(String defaultPasteMode) {
		this.defaultPasteMode = defaultPasteMode;
	}

	@Override
	public String getFieldName() {
		return EDIT;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for EditRTEPlugin");
	}
}
