package com.citytechinc.cq.component.touchuidialog.widget.numberfield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class NumberFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    private Double min;

    private Double max;

    private Double step;

    private String typeHint;

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }

    public String getTypeHint() {
        return typeHint;
    }

    public void setTypeHint(String typeHint) {
        this.typeHint = typeHint;
    }

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return NumberFieldCoral3Widget.RESOURCE_TYPE;
        }
        return NumberFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for NumberFieldWidget");
    }

}
