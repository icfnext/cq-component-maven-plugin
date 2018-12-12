package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidget;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetParameters;

@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class,
	resourceType = TagInputFieldWidget.RESOURCE_TYPE)
public class TagInputFieldWidget extends AutoCompleteWidget {

	public static final String VALUES_RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete/tags";
	public static final String OPTIONS_RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete/list";

	public TagInputFieldWidget(AutoCompleteWidgetParameters parameters) {
		super(parameters);
	}

}
