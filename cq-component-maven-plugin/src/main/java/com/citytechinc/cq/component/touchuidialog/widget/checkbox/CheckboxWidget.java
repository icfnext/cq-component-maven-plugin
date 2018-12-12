package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = CheckBox.class, makerClass = CheckboxWidgetMaker.class,
    resourceType = CheckboxWidget.RESOURCE_TYPE)
public class CheckboxWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/checkbox";

    private final String text;

    private final String title;

    private final boolean[] checked;

    public CheckboxWidget(CheckboxWidgetParameters parameters) {
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
