package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class CheckboxWidgetMaker extends AbstractTouchUIWidgetMaker<CheckboxWidgetParameters> {

	public CheckboxWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(CheckboxWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		CheckBox checkboxAnnotation = getAnnotation(CheckBox.class);

		widgetParameters.setText(getTextForField(checkboxAnnotation));
		widgetParameters.setTitle(getTitleForField(checkboxAnnotation));
		widgetParameters.setChecked(getCheckedForField(checkboxAnnotation));
		widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

		if(TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
			return new CheckboxCoral3Widget(widgetParameters);
		}
		return new CheckboxWidget(widgetParameters);
	}

	public String getTextForField(CheckBox annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
			return annotation.text();
		}

		return null;
	}

	public String getTitleForField(CheckBox annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
			return annotation.title();
		}

		return null;
	}
	
	public boolean[] getCheckedForField(CheckBox annotation) {
		if (annotation != null) {
			return annotation.touchUIChecked();
		}

		return new boolean[0];
	}
}