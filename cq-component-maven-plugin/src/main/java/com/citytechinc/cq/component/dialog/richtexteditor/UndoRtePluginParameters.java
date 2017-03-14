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
package com.citytechinc.cq.component.dialog.richtexteditor;

public class UndoRtePluginParameters extends RtePluginParameters {
	public static final String UNDO = "undo";
	private int maxUndoSteps;

	public int getMaxUndoSteps() {
		return maxUndoSteps;
	}

	public void setMaxUndoSteps(int maxUndoSteps) {
		this.maxUndoSteps = maxUndoSteps;
	}

	@Override
	public String getFieldName() {
		return UNDO;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for UndoRTEPlugin");
	}
}
