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
package com.citytechinc.cq.component.editconfig.formparameters;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class EditConfigFormParametersParameters extends DefaultXmlElementParameters {
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
		return "cq:formParameters";
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("fieldName is Static for EditConfigInPlaceEditingParameters");
	}

	@Override
	public String getPrimaryType() {
		return Constants.NT_UNSTRUCTURED;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for EditConfigInPlaceEditingParameters");
	}
}
