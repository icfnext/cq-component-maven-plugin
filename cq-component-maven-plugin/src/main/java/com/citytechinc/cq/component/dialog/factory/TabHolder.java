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
package com.citytechinc.cq.component.dialog.factory;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Listeners;

public class TabHolder {
	private final List<DialogElement> elements = new ArrayList<DialogElement>();
	private String title;
	private Listeners listeners;

	public List<DialogElement> getElements() {
		return elements;
	}

	public void addElement(DialogElement element) {
		elements.add(element);
	}

	public Listeners getListeners() {
		return listeners;
	}

	public String getTitle() {
		return title;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
