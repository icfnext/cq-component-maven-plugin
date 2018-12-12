package com.citytechinc.cq.component.touchuidialog.widget.textfield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TextFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return TextFieldCoral3Widget.RESOURCE_TYPE;
        }
        return TextFieldWidget.RESOURCE_TYPE;
    }
}