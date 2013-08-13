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
package com.citytechinc.cq.component.dialog.textfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.DefaultWidgetMaker;
import com.citytechinc.cq.component.dialog.widget.WidgetParameters;

@Widget(annotationClass = TextField.class, makerClass = DefaultWidgetMaker.class, xtype = TextFieldWidget.XTYPE)
public class TextFieldWidget extends AbstractWidget {

	public static final String XTYPE = "textfield";

	public TextFieldWidget(WidgetParameters parameters) {
		super(parameters);
	}
}
