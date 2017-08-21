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

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class SwitchWidgetMaker extends AbstractTouchUIWidgetMaker<SwitchWidgetParameters> {

	public SwitchWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(SwitchWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		Switch switchAnnotation = getAnnotation(Switch.class);
		widgetParameters.setOnText(getOnTextForField(switchAnnotation));
		widgetParameters.setOffText(getOffTextForField(switchAnnotation));
		widgetParameters.setChecked(getCheckedForField(switchAnnotation));
		widgetParameters.setIgnoreData(getIgnoreDataField(switchAnnotation));
		widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

		if(TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
			return new SwitchCoral3Widget(widgetParameters);
		}
		return new SwitchWidget(widgetParameters);
	}

	private boolean getIgnoreDataField(Switch annotation) {
		return annotation != null && annotation.ignoreData();
	}

	private Boolean getCheckedForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.checked())) {
			if ("true".equals(annotation.checked())) {
				return true;
			} else if ("false".equals(annotation.checked())) {
				return false;
			}
		}

		return null;
	}

	public String getOnTextForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.onText())) {
			return annotation.onText();
		}

		return null;
	}

	public String getOffTextForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.offText())) {
			return annotation.offText();
		}

		return null;
	}

}
