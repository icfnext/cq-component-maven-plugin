package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = MultiField.class, makerClass = MultiFieldWidgetMaker.class,
    resourceType = MultiFieldCoral3Widget.RESOURCE_TYPE, ranking = MultiFieldCoral3Widget.RANKING)
public class MultiFieldCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/multifield";

    public static final int RANKING = 1000000;

    protected final boolean composite;

    public MultiFieldCoral3Widget(MultiFieldWidgetParameters parameters) {
        super(parameters);

        composite = parameters.isComposite();
    }

    public boolean isComposite() {
        return composite;
    }
}