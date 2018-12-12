package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class CheckboxWidgetParameters extends DefaultTouchUIWidgetParameters {

	private String text;
	private String title;
	private boolean[] checked;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public boolean[] getChecked() {
		return checked;
	}

	public void setChecked(boolean[] checked) {
		this.checked = checked;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getResourceType() {
		if(TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
			return CheckboxCoral3Widget.RESOURCE_TYPE;
		}
		return CheckboxWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for CheckboxWidget");
	}
}