package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = CheckBox.class, makerClass = CheckboxWidgetMaker.class,
    resourceType = CheckboxCoral3Widget.RESOURCE_TYPE)
public class CheckboxCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/checkbox";

    private final String text;

    private final String title;

    private final boolean[] checked;

    public CheckboxCoral3Widget(CheckboxWidgetParameters parameters) {
        super(parameters);

        text = parameters.getText();
        title = parameters.getTitle();
        checked = parameters.getChecked();

    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getChecked() {
        if (checked != null && checked.length != 0) {
            return new Boolean(checked[0]);
        } else {
            return null;
        }
    }
}