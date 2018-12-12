package com.citytechinc.cq.component.dialog.datefield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class DateFieldWidgetParameters extends DefaultWidgetParameters {

    private int startDay;

    private boolean showToday;

    private String format;

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public boolean isShowToday() {
        return showToday;
    }

    public void setShowToday(boolean showToday) {
        this.showToday = showToday;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String getPrimaryType() {
        return Constants.CQ_WIDGET;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("PrimaryType is Static for DateFieldWidget");
    }

    @Override
    public String getXtype() {
        return DateFieldWidget.XTYPE;
    }

    @Override
    public void setXtype(String xtype) {
        throw new UnsupportedOperationException("xtype is Static for DateFieldWidget");
    }
}
