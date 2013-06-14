package com.citytechinc.cq.component.dialog.sizefield;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class SizeFieldWidgetMaker extends AbstractWidgetMaker {

	public SizeFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		SizeField sizeFieldAnnotation = getAnnotation(SizeField.class);

		SizeFieldWidgetParameters parameters = new SizeFieldWidgetParameters();

		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

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
