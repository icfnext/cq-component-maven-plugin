package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = RichTextEditor.class, makerClass = RichTextEditorMaker.class, xtype = "richtext")
public class RichTextEditorWidget extends AbstractWidget {

	public static final String XTYPE = "richtext";

	public RichTextEditorWidget(RichTextEditorWidgetParameters parameters) {

		super(parameters);

	}

}
