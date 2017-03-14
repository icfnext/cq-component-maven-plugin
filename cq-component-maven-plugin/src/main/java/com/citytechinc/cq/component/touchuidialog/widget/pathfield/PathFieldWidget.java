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
package com.citytechinc.cq.component.touchuidialog.widget.pathfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = PathField.class, makerClass = PathFieldWidgetMaker.class,
	resourceType = PathFieldWidget.RESOURCE_TYPE)
public class PathFieldWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/pathbrowser";

	private final String rootPath;
	private final String optionLoader;
	private final String optionLoaderRoot;
	private final String optionValueReader;
	private final String optionTitleReader;

	public PathFieldWidget(PathFieldWidgetParameters parameters) {
		super(parameters);

		rootPath = parameters.getRootPath();
		optionLoader = parameters.getOptionLoader();
		optionLoaderRoot = parameters.getOptionLoaderRoot();
		optionValueReader = parameters.getOptionValueReader();
		optionTitleReader = parameters.getOptionTitleReader();

	}

	public String getRootPath() {
		return rootPath;
	}

	public String getOptionLoader() {
		return optionLoader;
	}

	public String getOptionLoaderRoot() {
		return optionLoaderRoot;
	}

	public String getOptionValueReader() {
		return optionValueReader;
	}

	public String getOptionTitleReader() {
		return optionTitleReader;
	}

}
