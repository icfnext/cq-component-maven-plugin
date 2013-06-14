package com.citytechinc.cq.component.dialog.richtexteditor;

import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class RichTextEditorWidgetParameters extends WidgetParameters {
	private List<DialogElement> rtePlugins;

	public List<DialogElement> getRtePlugins() {
		return rtePlugins;
	}

	public void setRtePlugins(List<DialogElement> rtePlugins) {
		this.rtePlugins = rtePlugins;
	}

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
