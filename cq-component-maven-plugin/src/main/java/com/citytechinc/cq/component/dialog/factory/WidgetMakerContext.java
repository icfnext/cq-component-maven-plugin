package com.citytechinc.cq.component.dialog.factory;

import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class WidgetMakerContext {

    private final Class<? extends WidgetMaker> maker;

    private final String xtype;

    public WidgetMakerContext(Class<? extends WidgetMaker> maker, String xtype) {
        this.maker = maker;
        this.xtype = xtype;
    }

    public Class<? extends WidgetMaker> getWidgetMaker() {
        return maker;
    }

    public String getXtype() {
        return xtype;
    }

}
