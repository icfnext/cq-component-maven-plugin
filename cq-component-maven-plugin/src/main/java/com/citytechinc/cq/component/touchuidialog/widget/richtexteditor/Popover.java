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
package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public class Popover extends AbstractTouchUIDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";
	private final String ref;
	private final String[] items;

	public Popover(PopoverParameters parameters) {
		super(parameters);
		ref = parameters.getRef();
		items = parameters.getItems();
	}

	public String getRef() {
		return ref;
	}

	public String[] getItems() {
		return items;
	}

}
