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

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TextAreaWidgetParameters extends DefaultTouchUIWidgetParameters {

	private Integer cols;
	private Integer rows;
	private String resize;

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getResize() {
		return resize;
	}

	public void setResize(String resize) {
		this.resize = resize;
	}

	@Override
	public String getResourceType() {
		return TextAreaWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for TextAreaWidget");
	}
}
