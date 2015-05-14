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
package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.textarea.TextAreaWidgetMaker;

public class RichTextEditorWidgetMaker extends TextAreaWidgetMaker {

	public RichTextEditorWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
		LogSingleton
			.getInstance()
			.warn(
				"There is no RichTextEditor dialog widget currently for Touch UI. This widget maker will render the widget as a Text Area but that is likely not what is intended.  To provide rich text editing capabilities in a component authorable via the Touch UI, expose these capabilities via a 'text' inline editor.");
	}

}
