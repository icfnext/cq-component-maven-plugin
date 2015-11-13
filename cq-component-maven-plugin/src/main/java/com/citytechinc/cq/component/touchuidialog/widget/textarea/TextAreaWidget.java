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
package com.citytechinc.cq.component.touchuidialog.widget.textarea;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = TextArea.class, makerClass = TextAreaWidgetMaker.class,
	resourceType = TextAreaWidget.RESOURCE_TYPE)
public class TextAreaWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/textarea";

	private final Integer cols;
	private final Integer rows;
	private final String resize;

	public TextAreaWidget(TextAreaWidgetParameters parameters) {

		super(parameters);

		this.cols = parameters.getCols();
		this.rows = parameters.getRows();
		this.resize = parameters.getResize();

	}

	public Integer getCols() {
		return cols;
	}

	public Integer getRows() {
		return rows;
	}

	public String getResize() {
		return resize;
	}

}
