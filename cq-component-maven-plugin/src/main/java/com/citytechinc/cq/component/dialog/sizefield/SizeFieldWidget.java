package com.citytechinc.cq.component.dialog.sizefield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = SizeField.class, makerClass = SizeFieldWidgetMaker.class, xtype = SizeFieldWidget.XTYPE)
public class SizeFieldWidget extends AbstractWidget {

    public static final String XTYPE = "sizefield";

    private final String heightParameter;

    private final String heightPrefix;

    private final String heightSuffix;

    private final String widthParameter;

    private final String widthPrefix;

    private final String widthSuffix;

    private final int fieldWidth;

    public SizeFieldWidget(SizeFieldWidgetParameters parameters) {
        super(parameters);

        this.heightParameter = parameters.getHeightParameter();
        this.heightPrefix = parameters.getHeightPrefix();
        this.heightSuffix = parameters.getHeightSuffix();
        this.widthParameter = parameters.getWidthParameter();
        this.widthPrefix = parameters.getWidthPrefix();
        this.widthSuffix = parameters.getWidthSuffix();
        this.fieldWidth = parameters.getFieldWidth();

    }

    public String getHeightParameter() {
        return heightParameter;
    }

    public String getHeightPrefix() {
        return heightPrefix;
    }

    public String getHeightSuffix() {
        return heightSuffix;
    }

    public String getWidthParameter() {
        return widthParameter;
    }

    public String getWidthPrefix() {
        return widthPrefix;
    }

    public String getWidthSuffix() {
        return widthSuffix;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

}
