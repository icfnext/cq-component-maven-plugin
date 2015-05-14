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
package com.citytechinc.cq.component.dialog.sizefield;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class SizeFieldWidgetMaker extends AbstractWidgetMaker<SizeFieldWidgetParameters> {

	public SizeFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(SizeFieldWidgetParameters parameters) throws ClassNotFoundException {

		SizeField sizeFieldAnnotation = getAnnotation(SizeField.class);

		parameters.setHeightParameter(getHeightParameterForField(sizeFieldAnnotation));
		parameters.setHeightPrefix(getHeightPrefixForField(sizeFieldAnnotation));
		parameters.setHeightSuffix(getHeightSuffixForField(sizeFieldAnnotation));
		parameters.setWidthParameter(getWidthParameterForField(sizeFieldAnnotation));
		parameters.setWidthPrefix(getWidthPrefixForField(sizeFieldAnnotation));
		parameters.setWidthSuffix(getWidthSuffixForField(sizeFieldAnnotation));
		parameters.setFieldWidth(getFieldWidthForField(sizeFieldAnnotation));

		return new SizeFieldWidget(parameters);

	}

	private int getFieldWidthForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.fieldWidth();
	}

	private String getWidthSuffixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.widthSuffix())) {
			return sizeFieldAnnotation.widthSuffix();
		}
		return null;
	}

	private String getWidthPrefixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.widthPrefix())) {
			return sizeFieldAnnotation.widthPrefix();
		}
		return null;
	}

	private String getWidthParameterForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.widthParameter();
	}

	private String getHeightSuffixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.heightSuffix())) {
			return sizeFieldAnnotation.heightSuffix();
		}
		return null;
	}

	private String getHeightPrefixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.heightPrefix())) {
			return sizeFieldAnnotation.heightPrefix();
		}
		return null;
	}

	private String getHeightParameterForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.heightParameter();
	}

}
