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
package com.citytechinc.cq.component.touchuidialog.widget.selection.options;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class OptionsParameters extends DefaultTouchUIDialogElementParameters {

	private List<Option> options;

	@Override
	public String getFieldName() {
		return Options.OPTIONS_FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for OptionsParameters");
	}

	@Override
	public String getPrimaryType() {
		return "nt:unstructured";
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("Primary Type is static for OptionsParameters");
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {
		List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

		allContainedElements.addAll(options);

		if (containedElements != null) {
			allContainedElements.addAll(containedElements);
		}

		return allContainedElements;
	}

}
