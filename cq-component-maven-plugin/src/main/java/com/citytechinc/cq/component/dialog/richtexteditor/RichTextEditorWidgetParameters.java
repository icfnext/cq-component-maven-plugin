package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class RichTextEditorWidgetParameters extends DefaultWidgetParameters {

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for RichTextEditorWidget");
	}

	@Override
	public String getXtype() {
		return RichTextEditorWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for RichTextEditorWidget");
	}
}
