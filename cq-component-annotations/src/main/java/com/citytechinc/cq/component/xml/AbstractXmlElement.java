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
package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.util.Constants;

public class AbstractXmlElement implements XmlElement {
	protected NameSpacedAttribute<String> primaryType;
	protected String fieldName;
	protected Map<String, ?> additionalProperties;
	protected List<? extends XmlElement> containedElements;
	protected String nameSpace;

	public AbstractXmlElement(XmlElementParameters parameters) {
		primaryType =
			new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getPrimaryType());
		this.fieldName = parameters.getFieldName();
		this.additionalProperties = parameters.getAdditionalProperties();
		this.containedElements = parameters.getContainedElements();
		this.nameSpace = parameters.getNameSpace();
	}

	public NameSpacedAttribute<String> getPrimaryType() {
		return primaryType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Map<String, ?> getAdditionalProperties() {
		return additionalProperties;
	}

	public List<? extends XmlElement> getContainedElements() {
		return containedElements;
	}

	public String getNameSpace() {
		return nameSpace;
	}
}
