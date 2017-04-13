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

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

public abstract class AbstractInPlaceEditorElement extends AbstractXmlElement implements InPlaceEditorElement {
	private final Boolean active;
	private final String editorType;
	private final String type;
	private final String title;

	public AbstractInPlaceEditorElement(InPlaceEditorParameters parameters) {
		super(parameters);
		this.active = parameters.isActive();
		if (active != null) {
			this.editorType = parameters.getEditorType();
			this.type = null;
		} else {
			this.type = parameters.getEditorType();
			this.editorType = null;
		}
		this.title = StringUtils.isNotEmpty(parameters.getTitle()) ? parameters.getTitle() : parameters.getFieldName();
	}

	public Boolean getActive() {
		return active;
	}

	public String getEditorType() {
		return editorType;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}
}
