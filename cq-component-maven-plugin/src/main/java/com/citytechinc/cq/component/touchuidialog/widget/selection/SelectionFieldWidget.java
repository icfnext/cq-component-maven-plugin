package com.citytechinc.cq.component.touchuidialog.widget.selection;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = Selection.class, makerClass = SelectionFieldWidgetMaker.class,
	resourceType = SelectionFieldWidget.RESOURCE_TYPE)
public class SelectionFieldWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/select";

	private final boolean multiple;

	public SelectionFieldWidget(SelectionFieldWidgetParameters parameters) {
		super(parameters);

		this.multiple = parameters.isMultiple();
	}

	public boolean getMultiple() {
		return multiple;
	}
}
