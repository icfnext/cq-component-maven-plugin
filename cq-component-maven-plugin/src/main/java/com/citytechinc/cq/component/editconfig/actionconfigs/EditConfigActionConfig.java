package com.citytechinc.cq.component.editconfig.actionconfigs;

import com.citytechinc.cq.component.xml.AbstractXmlElement;

public class EditConfigActionConfig extends AbstractXmlElement {

    private final String text;

    private final String handler;

    private final String xtype;

    public EditConfigActionConfig(EditConfigActionConfigParameters parameters) {
        super(parameters);
        this.text = parameters.getText();
        this.handler = parameters.getHandler();
        this.xtype = parameters.getXtype();
    }

    public String getText() {
        return text;
    }

    public String getHandler() {
        return handler;
    }

    public String getXtype() {
        return xtype;
    }
}
