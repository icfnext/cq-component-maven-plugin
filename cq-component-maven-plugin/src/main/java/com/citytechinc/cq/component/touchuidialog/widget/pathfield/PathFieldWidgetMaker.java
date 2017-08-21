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

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class PathFieldWidgetMaker extends AbstractTouchUIWidgetMaker<PathFieldWidgetParameters> {

	public PathFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(PathFieldWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {
		PathField pathFieldAnnotation = getAnnotation(PathField.class);

		widgetParameters.setRootPath(getRootPathForField(pathFieldAnnotation));
		widgetParameters.setOptionLoader(getOptionLoaderForField(pathFieldAnnotation));
		widgetParameters.setOptionLoaderRoot(getOptionLoaderRootForField(pathFieldAnnotation));
		widgetParameters.setOptionValueReader(getOptionValueReaderForField(pathFieldAnnotation));
		widgetParameters.setOptionTitleReader(getOptionTitleReaderForField(pathFieldAnnotation));
		widgetParameters.setForceSelection(getForceSelectionForField(pathFieldAnnotation));
		widgetParameters.setFilter(getFilterForField(pathFieldAnnotation));
		widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

		if(TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
			return new PathFieldCoral3Widget(widgetParameters);
		}
		return new PathFieldWidget(widgetParameters);
	}

	public String getRootPathForField(PathField annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.rootPath())) {
			return annotation.rootPath();
		}

		return null;
	}

	public String getOptionLoaderForField(PathField annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.optionLoader())) {
			return annotation.optionLoader();
		}

		return null;
	}

	public String getOptionLoaderRootForField(PathField annotation) {
		return null;
	}

	public String getOptionValueReaderForField(PathField annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.optionValueReader())) {
			return annotation.optionValueReader();
		}

		return null;
	}

	public String getOptionTitleReaderForField(PathField annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.optionTitleReader())) {
			return annotation.optionTitleReader();
		}

		return null;
	}

	public boolean getForceSelectionForField(PathField annotation) {
		if (annotation != null) {
			return annotation.forceSelection();
		}

		return false;
	}

	public String getFilterForField(PathField annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.filter())) {
			return annotation.filter();
		}

		return null;
	}
}
