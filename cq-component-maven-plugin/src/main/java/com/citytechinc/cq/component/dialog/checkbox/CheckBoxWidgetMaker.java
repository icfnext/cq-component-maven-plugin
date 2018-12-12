package com.citytechinc.cq.component.dialog.checkbox;

import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class CheckBoxWidgetMaker extends AbstractWidgetMaker<CheckBoxWidgetParameters> {

    public CheckBoxWidgetMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(CheckBoxWidgetParameters widgetParameters) throws ClassNotFoundException {

        CheckBox checkBoxAnnotation = getAnnotation(CheckBox.class);

        String inputValue = getInputValueForField(checkBoxAnnotation);
        boolean checked = getCheckedForField(checkBoxAnnotation);

        widgetParameters.setInputValue(inputValue);
        widgetParameters.setChecked(checked);

        return new CheckBoxWidget(widgetParameters);

    }

    protected String getInputValueForField(CheckBox annotation) {
        return annotation.inputValue();
    }

    protected boolean getCheckedForField(CheckBox annotation) {
        return annotation.checked();
    }

}
