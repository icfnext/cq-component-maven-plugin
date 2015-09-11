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
package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

public class AutoCompleteWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete";
	public static final String VALUES_FIELD_NAME = "values";
	public static final String OPTIONS_FIELD_NAME = "options";

	protected final boolean multiple;
	protected final String mode;

	public AutoCompleteWidget(AutoCompleteWidgetParameters parameters) {
		super(parameters);

		multiple = parameters.isMultiple();
		mode = parameters.getMode();

	}

	public boolean isMultiple() {
		return multiple;
	}

	public String getMode() {
		return mode;
	}

}
