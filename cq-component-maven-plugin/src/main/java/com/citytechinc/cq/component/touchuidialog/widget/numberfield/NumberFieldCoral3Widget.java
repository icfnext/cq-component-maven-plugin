package com.citytechinc.cq.component.touchuidialog.widget.numberfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = NumberField.class, makerClass = NumberFieldWidgetMaker.class,
    resourceType = NumberFieldCoral3Widget.RESOURCE_TYPE)
public class NumberFieldCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/numberfield";

    private final Double min;

    private final Double max;

    private final Double step;

    private final String typeHint;

    public NumberFieldCoral3Widget(NumberFieldWidgetParameters parameters) {
        super(parameters);

        min = parameters.getMin();
        max = parameters.getMax();
        step = parameters.getStep();
        typeHint = parameters.getTypeHint();
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getStep() {
        return step;
    }

    public String getTypeHint() {
        return typeHint;
    }

}
