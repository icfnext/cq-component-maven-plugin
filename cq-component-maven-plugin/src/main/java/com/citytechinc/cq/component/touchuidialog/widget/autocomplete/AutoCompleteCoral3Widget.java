package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.AutoComplete;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = AutoComplete.class, makerClass = AutoCompleteWidgetMaker.class,
	resourceType = AutoCompleteCoral3Widget.RESOURCE_TYPE)
public class AutoCompleteCoral3Widget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/autocomplete";

	protected final boolean multiple;
	protected final String mode;
	protected final boolean forceSelection;

	public AutoCompleteCoral3Widget(AutoCompleteWidgetParameters parameters) {
		super(parameters);

		multiple = parameters.isMultiple();
		mode = parameters.getMode();
		forceSelection = parameters.isForceSelection();
	}

	public boolean isMultiple() {
		return multiple;
	}

	public String getMode() {
		return mode;
	}

	public boolean isForceSelection(){
		return forceSelection;
	}
}