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
package com.citytechinc.cq.component.dialog.selection;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Option extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";
	private final String text;
	private final String value;
	private final String qtip;

	public Option(OptionParameters parameters) {
		super(parameters);
		this.text = parameters.getText();
		this.value = parameters.getValue();
		this.qtip = parameters.getQtip();
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	public String getQtip() {
		return qtip;
	}
}
