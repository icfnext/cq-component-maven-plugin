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

public class DefaultXmlElementParameters implements XmlElementParameters {
	protected String primaryType;
	protected String fieldName;
	protected Map<String, ?> additionalProperties;
	protected List<? extends XmlElement> containedElements;
	protected String nameSpace;

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Map<String, ?> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, ?> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public List<? extends XmlElement> getContainedElements() {
		return containedElements;
	}

	public void setContainedElements(List<? extends XmlElement> containedElements) {
		this.containedElements = containedElements;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}
