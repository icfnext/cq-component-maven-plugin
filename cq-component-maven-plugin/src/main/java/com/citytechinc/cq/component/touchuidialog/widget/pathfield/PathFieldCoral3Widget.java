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
	resourceType = PathFieldCoral3Widget.RESOURCE_TYPE)
public class PathFieldCoral3Widget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/pathfield";

	private final String rootPath;
	private final boolean forceSelection;
	private final String filter;


	public PathFieldCoral3Widget(PathFieldWidgetParameters parameters) {
		super(parameters);

		rootPath = parameters.getRootPath();
		forceSelection = parameters.isForceSelection();
		filter = parameters.getFilter();
	}

	public String getRootPath() {
		return rootPath;
	}

	public boolean isForceSelection() {
		return forceSelection;
	}

	public String getFilter() {
		return filter;
	}
}
