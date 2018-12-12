package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.AutoComplete;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = AutoComplete.class, makerClass = AutoCompleteWidgetMaker.class,
    resourceType = AutoCompleteWidget.RESOURCE_TYPE)
public class AutoCompleteWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete";

    public static final String VALUES_FIELD_NAME = "values";

    public static final String OPTIONS_FIELD_NAME = "options";

    protected final boolean multiple;

    protected final String mode;

    public AutoCompleteWidget(AutoCompleteWidgetParameters parameters) {
        super(parameters);

        multiple = parameters.isMultiple();
        mode = parameters.getMode();

    }

    public boolean isMultiple() {
        return multiple;
    }

    public String getMode() {
        return mode;
    }

}
