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
package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public interface XmlElementParameters {

	public abstract String getPrimaryType();

	public abstract void setPrimaryType(String primaryType);

	public abstract String getFieldName();

	public abstract void setFieldName(String fieldName);

	public abstract Map<String, ?> getAdditionalProperties();

	public abstract void setAdditionalProperties(Map<String, ?> additionalProperties);

	public abstract List<? extends XmlElement> getContainedElements();

	public abstract void setContainedElements(List<? extends XmlElement> containedElements);

	public abstract String getNameSpace();

	public abstract void setNameSpace(String nameSpace);

}