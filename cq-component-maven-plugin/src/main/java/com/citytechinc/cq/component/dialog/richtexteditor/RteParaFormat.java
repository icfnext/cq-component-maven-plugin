package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class RteParaFormat extends AbstractDialogElement {

    public static final String PRIMARY_TYPE = "nt:unstructured";

    private final String tag;

    private final String description;

    public RteParaFormat(RteParaFormatParameters parameters) {
        super(parameters);

        this.tag = parameters.getTag();
        this.description = parameters.getDescription();
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }
}
