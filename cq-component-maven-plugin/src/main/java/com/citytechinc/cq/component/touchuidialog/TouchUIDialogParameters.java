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
package com.citytechinc.cq.component.touchuidialog;

import org.codehaus.plexus.util.StringUtils;

public class TouchUIDialogParameters extends DefaultTouchUIDialogElementParameters {

	private static final String DEFAULT_FILE_NAME = "_cq_dialog";
	private static final String FIELD_NAME = "jcr:root";

	private String fileName;
	private String title;
	private String helpPath;
	private String[] extraClientlibs;

	@Override
	public String getResourceType() {
		return TouchUIDialog.RESOURCE_TYPE;
	}

	@Override
	public String getPrimaryType() {
		return TouchUIDialog.PRIMARY_TYPE;
	}

	public String getFileName() {
		if (StringUtils.isBlank(fileName)) {
			return DEFAULT_FILE_NAME;
		}

		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getFieldName() {
		return FIELD_NAME;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("field name is Static for Touch UI Dialog");
	}

	public String getHelpPath() {
		return helpPath;
	}

	public void setHelpPath(String helpPath) {
		this.helpPath = helpPath;
	}

	public void setExtraClientlibs(String[] extraClientlibs) { this.extraClientlibs = extraClientlibs; }

	public String[] getExtraClientlibs() {
		return extraClientlibs;
	}
}
