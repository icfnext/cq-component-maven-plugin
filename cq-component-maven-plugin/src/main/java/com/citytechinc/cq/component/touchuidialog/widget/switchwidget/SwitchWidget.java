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
package com.citytechinc.cq.component.touchuidialog.widget.switchwidget;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = Switch.class, makerClass = SwitchWidgetMaker.class,
	resourceType = SwitchWidget.RESOURCE_TYPE)
public class SwitchWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/switch";

	private final String onText;
	private final String offText;
	private final Boolean checked;
	private final boolean ignoreData;

	public SwitchWidget(SwitchWidgetParameters parameters) {
		super(parameters);
		this.onText = parameters.getOnText();
		this.offText = parameters.getOffText();
		this.checked = parameters.getChecked();
		this.ignoreData = parameters.isIgnoreData();
	}

	public String getOnText() {
		return onText;
	}

	public String getOffText() {
		return offText;
	}

	public Boolean getChecked() {
		return checked;
	}

	public boolean isIgnoreData() {
		return ignoreData;
	}

}
