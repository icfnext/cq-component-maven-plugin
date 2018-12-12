package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class,
    xtype = TagInputFieldWidget.XTYPE)
public class TagInputFieldWidget extends AbstractWidget {

    public static final String XTYPE = "tags";

    private final boolean displayTitles;

    public TagInputFieldWidget(TagInputFieldWidgetParameters parameters) {
        super(parameters);
        this.displayTitles = parameters.isDisplayTitles();
    }

    public boolean isDisplayTitles() {
        return displayTitles;
    }
}
