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

import java.util.List;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;
import com.citytechinc.cq.component.xml.XmlElement;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public abstract class InPlaceEditorParameters extends DefaultXmlElementParameters implements XmlElementParameters {
	public abstract Boolean isActive();

	public abstract void setActive(Boolean active);

	public abstract String getEditorType();

	public abstract void setEditorType(String editorType);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract ConfigElement getConfigElement();

	public abstract void setConfigElement(ConfigElement configNode);

	@Override
	public void setContainedElements(List<? extends XmlElement> containedElements) {
		throw new UnsupportedOperationException("contained elements should not be set for in place editors");
	}
}
