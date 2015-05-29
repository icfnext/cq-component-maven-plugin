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
package com.citytechinc.cq.component.dialog.statik;

import com.citytechinc.cq.component.annotations.widgets.Static;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.google.common.base.Strings;

public class StaticWidgetMaker extends AbstractWidgetMaker {
	public StaticWidgetMaker(final WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public final DialogElement make() throws ClassNotFoundException {
		final Static staticAnnotation = getAnnotation(Static.class);
		final StaticWidgetParameters staticWidgetParameters = buildStaticWidgetParameters(staticAnnotation);
		return new StaticWidget(staticWidgetParameters);
	}

	private StaticWidgetParameters buildStaticWidgetParameters(final Static staticAnnotation) {
		final StaticWidgetParameters staticWidgetParameters = new StaticWidgetParameters();

		//Set custom widget parameters
		staticWidgetParameters.setBold(staticAnnotation.bold());
		staticWidgetParameters.setBottommargin(staticAnnotation.bottommargin());
		staticWidgetParameters.setHref(Strings.emptyToNull(staticAnnotation.href()));
		staticWidgetParameters.setHtml(Strings.emptyToNull(staticAnnotation.html()));
		staticWidgetParameters.setItalic(staticAnnotation.italic());
		staticWidgetParameters.setNoWrap(staticAnnotation.noWrap());
		staticWidgetParameters.setSmall(staticAnnotation.small());
		staticWidgetParameters.setTag(Strings.emptyToNull(staticAnnotation.tag()));
		staticWidgetParameters.setTarget(Strings.emptyToNull(staticAnnotation.target()));
		staticWidgetParameters.setText(Strings.emptyToNull(staticAnnotation.text()));
		staticWidgetParameters.setTopmargin(staticAnnotation.topmargin());

		//Set parameters from DialogField
		staticWidgetParameters.setFieldLabel(getFieldLabelForField());
		staticWidgetParameters.setFieldName(getFieldNameForField());
		staticWidgetParameters.setFieldDescription(getFieldDescriptionForField());
		staticWidgetParameters.setHideLabel(getHideLabelForField());
		staticWidgetParameters.setAdditionalProperties(getAdditionalPropertiesForField());
		staticWidgetParameters.setListeners(getListeners());

		return staticWidgetParameters;
	}
}
