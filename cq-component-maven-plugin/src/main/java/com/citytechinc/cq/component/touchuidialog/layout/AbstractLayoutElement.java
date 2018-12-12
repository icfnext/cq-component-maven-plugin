package com.citytechinc.cq.component.touchuidialog.layout;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public abstract class AbstractLayoutElement extends AbstractTouchUIDialogElement implements LayoutElement {

    public static final String ELEMENT_NAME = "layout";

    public static final String PRIMARY_TYPE = "nt:unstructured";

    public AbstractLayoutElement(LayoutElementParameters parameters) {
        super(parameters);
    }

}
