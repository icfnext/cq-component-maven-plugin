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
package com.citytechinc.cq.component.touchuidialog.layout;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class LayoutElementParameters extends DefaultTouchUIDialogElementParameters {

	@Override
	public String getFieldName() {
		return AbstractLayoutElement.ELEMENT_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("field name is Static for Layout Element");
	}

	@Override
	public String getPrimaryType() {
		return AbstractLayoutElement.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String fieldName) {
		throw new UnsupportedOperationException("primary type is Static for Layout Element");
	}

}
