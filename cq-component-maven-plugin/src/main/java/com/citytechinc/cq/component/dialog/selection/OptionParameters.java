package com.citytechinc.cq.component.dialog.selection;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;

public class OptionParameters extends DefaultDialogElementParameters {

    private String text;

    private String value;

    private String qtip;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    @Override
    public String getPrimaryType() {
        return Option.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for Option");
    }
}
