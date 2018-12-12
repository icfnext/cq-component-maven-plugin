package com.citytechinc.cq.component;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

public class AspectRatio extends AbstractXmlElement {

    private String value;

    private String ratio;

    private final String text;

    private final String name;

    public AspectRatio(AspectRatioParameters parameters) {
        super(parameters);
        if (parameters.getWidth() != null && parameters.getHeight() != null) {
            value = parameters.getWidth() + "," + parameters.getHeight();
        }
        if (parameters.getRatio() != null) {
            ratio = Double.toString(parameters.getRatio());
        }
        text = parameters.getText();
        name = parameters.getName();
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getRatio() {
        return ratio;
    }

    public String getName() {
        return name;
    }
}
