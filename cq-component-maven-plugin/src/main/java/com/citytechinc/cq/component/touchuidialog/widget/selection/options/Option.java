package com.citytechinc.cq.component.touchuidialog.widget.selection.options;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public class Option extends AbstractTouchUIDialogElement {

    private final String name;

    private final String text;

    private final String value;

    private final boolean selected;

    public Option(OptionParameters parameters) {
        super(parameters);

        name = parameters.getName();
        text = parameters.getText();
        value = parameters.getValue();
        selected = parameters.isSelected();
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelected() {
        return selected;
    }

}
