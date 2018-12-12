package com.citytechinc.cq.component.dialog.textarea;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.DefaultWidgetMaker;
import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;

@Widget(annotationClass = TextArea.class, makerClass = DefaultWidgetMaker.class, xtype = TextAreaWidget.XTYPE)
public class TextAreaWidget extends AbstractWidget {

	public static final String XTYPE = "textarea";

	public TextAreaWidget(DefaultWidgetParameters parameters) {
		super(parameters);
	}
}
