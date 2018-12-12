package com.citytechinc.cq.component.touchuidialog.widget.hidedialogfield;

import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class HideDialogFieldWidget extends AbstractTouchUIWidget {

    public HideDialogFieldWidget(HideDialogFieldWidgetParameters parameters) {
        super(parameters);
    }

    public NameSpacedAttribute<Boolean> isHideResource() {
        return new NameSpacedAttribute<Boolean>(Constants.SLING_NS_URI, Constants.SLING_NS_PREFIX, true);
    }
}
