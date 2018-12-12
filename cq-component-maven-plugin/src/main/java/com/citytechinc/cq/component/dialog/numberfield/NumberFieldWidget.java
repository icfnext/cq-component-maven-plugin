package com.citytechinc.cq.component.dialog.numberfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = NumberField.class, makerClass = NumberFieldWidgetMaker.class, xtype = NumberFieldWidget.XTYPE)
public class NumberFieldWidget extends AbstractWidget {

    public static final String XTYPE = "numberfield";

    private final boolean allowDecimals;

    private final boolean allowNegative;

    private final int decimalPrecision;

    private final String decimalSeparator;

    public NumberFieldWidget(NumberFieldWidgetParameters parameters) {
        super(parameters);
        this.allowDecimals = parameters.isAllowDecimals();
        this.allowNegative = parameters.isAllowNegative();
        this.decimalPrecision = parameters.getDecimalPrecision();
        this.decimalSeparator = parameters.getDecimalSeparator();
    }

    public boolean isAllowDecimals() {
        return allowDecimals;
    }

    public boolean isAllowNegative() {
        return allowNegative;
    }

    public int getDecimalPrecision() {
        return decimalPrecision;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

}
