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
package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class TouchUIDialog extends AbstractTouchUIDialogElement {

	public static final String RESOURCE_TYPE = "cq/gui/components/authoring/dialog";
	public static final String PRIMARY_TYPE = "nt:unstructured";

	private String fileName;
	private NameSpacedAttribute<String> title;
	private String helpPath;
	private String[] extraClientlibs;

	public TouchUIDialog(TouchUIDialogParameters parameters) {
		super(parameters);

		this.fileName = parameters.getFileName();
		this.title =
			new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		this.helpPath = parameters.getHelpPath();
		this.extraClientlibs = parameters.getExtraClientlibs();
	}

	public String getFileName() {
		return fileName + ".xml";
	}

	public NameSpacedAttribute<String> getTitle() {
		return title;
	}

	public String getHelpPath() {
		return helpPath;
	}

	public String getMode() {
		return "edit";
	}

	public String[] getExtraClientlibs() {
		return extraClientlibs;
	}

}
