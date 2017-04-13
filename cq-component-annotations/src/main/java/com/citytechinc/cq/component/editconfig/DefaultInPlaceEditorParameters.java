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
package com.citytechinc.cq.component.editconfig;

public class DefaultInPlaceEditorParameters extends InPlaceEditorParameters {
	private Boolean active;
	private String editorType;
	private String title;
	private ConfigElement configElement;

	@Override
	public Boolean isActive() {
		return active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String getEditorType() {
		return editorType;
	}

	@Override
	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public ConfigElement getConfigElement() {
		return configElement;
	}

	@Override
	public void setConfigElement(ConfigElement configElement) {
		this.configElement = configElement;
	}
}
