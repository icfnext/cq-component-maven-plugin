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
package com.citytechinc.cq.component.editconfig;

import java.util.List;

import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class EditConfig extends AbstractXmlElement {
	private static final String CQ_NAMESPACE_PREFIX = "cq";

	private final NameSpacedAttribute<List<String>> actions;

	private final NameSpacedAttribute<String> dialogMode;

	private final NameSpacedAttribute<String> layout;

	private final NameSpacedAttribute<String> emptyText;

	private final NameSpacedAttribute<Boolean> inherit;

	private final NameSpacedAttribute<Boolean> disableTargeting;

	public EditConfig(EditConfigParameters parameters) {
		super(parameters);
		this.actions =
			new NameSpacedAttribute<List<String>>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
				CQ_NAMESPACE_PREFIX, parameters.getActions());
		this.dialogMode =
			new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI, CQ_NAMESPACE_PREFIX,
				parameters.getDialogMode());
		this.layout =
			new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI, CQ_NAMESPACE_PREFIX,
				parameters.getLayout());
		this.emptyText =
			new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI, CQ_NAMESPACE_PREFIX,
				parameters.getEmptyText());
		this.inherit =
			new NameSpacedAttribute<Boolean>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
				CQ_NAMESPACE_PREFIX, parameters.getInherit());
		this.disableTargeting =
			new NameSpacedAttribute<Boolean>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
				CQ_NAMESPACE_PREFIX, parameters.getDisableTargeting());
	}

	public NameSpacedAttribute<List<String>> getActions() {
		return actions;
	}

	public NameSpacedAttribute<String> getDialogMode() {
		return dialogMode;
	}

	public NameSpacedAttribute<String> getLayout() {
		return layout;
	}

	public NameSpacedAttribute<String> getEmptyText() {
		return emptyText;
	}

	public NameSpacedAttribute<Boolean> getInherit() {
		return inherit;
	}

	public NameSpacedAttribute<Boolean> getDisableTargeting() {
		return disableTargeting;
	}
}
