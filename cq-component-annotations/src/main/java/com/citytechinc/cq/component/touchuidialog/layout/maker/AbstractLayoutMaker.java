package com.citytechinc.cq.component.touchuidialog.layout.maker;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;

public abstract class AbstractLayoutMaker implements LayoutMaker {

    protected final LayoutMakerParameters parameters;

    protected AbstractLayoutMaker(LayoutMakerParameters parameters) {
        this.parameters = parameters;
    }

    protected Component getComponentAnnotation() throws LayoutMakerException {
        Component componentAnnotation;

        try {
            componentAnnotation = (Component) parameters.getComponentClass().getAnnotation(Component.class);
        } catch (ClassNotFoundException e) {
            throw new LayoutMakerException("Class Not Found Exception encountered looking up Component annotation", e);
        }

        return componentAnnotation;
    }
}
