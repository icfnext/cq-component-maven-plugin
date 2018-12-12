package com.citytechinc.cq.component.touchuidialog.widget.switchwidget;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class SwitchWidgetMaker extends AbstractTouchUIWidgetMaker<SwitchWidgetParameters> {

	public SwitchWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(SwitchWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		Switch switchAnnotation = getAnnotation(Switch.class);
		widgetParameters.setOnText(getOnTextForField(switchAnnotation));
		widgetParameters.setOffText(getOffTextForField(switchAnnotation));
		widgetParameters.setChecked(getCheckedForField(switchAnnotation));
		widgetParameters.setIgnoreData(getIgnoreDataField(switchAnnotation));
		widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

		if(TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
			return new SwitchCoral3Widget(widgetParameters);
		}
		return new SwitchWidget(widgetParameters);
	}

	private boolean getIgnoreDataField(Switch annotation) {
		return annotation != null && annotation.ignoreData();
	}

	private Boolean getCheckedForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.checked())) {
			if ("true".equals(annotation.checked())) {
				return true;
			} else if ("false".equals(annotation.checked())) {
				return false;
			}
		}

		return null;
	}

	public String getOnTextForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.onText())) {
			return annotation.onText();
		}

		return null;
	}

	public String getOffTextForField(Switch annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.offText())) {
			return annotation.offText();
		}

		return null;
	}

}
