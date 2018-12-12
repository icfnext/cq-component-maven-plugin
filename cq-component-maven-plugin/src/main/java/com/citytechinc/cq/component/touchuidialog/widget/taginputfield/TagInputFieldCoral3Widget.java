package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteCoral3Widget;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetParameters;

@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class,
	resourceType = TagInputFieldCoral3Widget.RESOURCE_TYPE)
public class TagInputFieldCoral3Widget extends AutoCompleteCoral3Widget {

	public static final String VALUES_RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/autocomplete/tags";
	public static final String OPTIONS_RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/autocomplete/list";

	public TagInputFieldCoral3Widget(AutoCompleteWidgetParameters parameters) {
		super(parameters);
	}

}
