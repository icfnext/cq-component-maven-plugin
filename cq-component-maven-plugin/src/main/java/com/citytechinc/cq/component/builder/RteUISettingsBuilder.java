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
package com.citytechinc.cq.component.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.citytechinc.cq.component.annotations.widgets.ToolbarConfig;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.CUI;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.CUIParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Popover;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.PopoverParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Popovers;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.PopoversParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Toolbar;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.ToolbarParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.UISettings;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.UISettingsParameters;

public class RteUISettingsBuilder {
	private final com.citytechinc.cq.component.annotations.widgets.rte.UISettings uiSettingsAnnotation;

	public RteUISettingsBuilder(com.citytechinc.cq.component.annotations.widgets.rte.UISettings uiSettingsAnnotation) {
		this.uiSettingsAnnotation = uiSettingsAnnotation;
	}

	public UISettings build() {
		List<Toolbar> toolbars = new ArrayList<Toolbar>();
		if (uiSettingsAnnotation.inline().length > 0) {
			toolbars.add(buildToolbar(uiSettingsAnnotation.inline()[0], "inline"));
		}

		if (uiSettingsAnnotation.fullscreen().length > 0) {
			toolbars.add(buildToolbar(uiSettingsAnnotation.fullscreen()[0], "fullscreen"));
		}

		CUIParameters cuiParamers = new CUIParameters();
		cuiParamers.setContainedElements(toolbars);
		CUI cui = new CUI(cuiParamers);

		UISettingsParameters uiSettingsParameters = new UISettingsParameters();
		uiSettingsParameters.setContainedElements(Collections.singletonList(cui));
		return new UISettings(uiSettingsParameters);
	}

	private Toolbar buildToolbar(ToolbarConfig toolbarConfig, String fieldName) {
		ToolbarParameters toolbarParameters = new ToolbarParameters();
		toolbarParameters.setFieldName(fieldName);
		toolbarParameters.setToolbar(toolbarConfig.toolbars());
		if (toolbarConfig.popovers().length > 0) {
			PopoversParameters popoversParameters = new PopoversParameters();
			List<Popover> popovers = new ArrayList<Popover>();
			for (com.citytechinc.cq.component.annotations.widgets.Popover popover : toolbarConfig.popovers()) {
				PopoverParameters popoverParameters = new PopoverParameters();
				popoverParameters.setFieldName(popover.ref());
				popoverParameters.setRef(popover.ref());
				popoverParameters.setItems(popover.items());
				popovers.add(new Popover(popoverParameters));
			}
			popoversParameters.setContainedElements(popovers);
			toolbarParameters.setContainedElements(Collections.singletonList(new Popovers(popoversParameters)));
		}

		return new Toolbar(toolbarParameters);
	}
}
