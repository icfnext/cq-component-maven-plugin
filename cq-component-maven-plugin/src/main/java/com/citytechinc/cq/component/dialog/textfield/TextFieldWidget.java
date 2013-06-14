package com.citytechinc.cq.component.dialog.textfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.SimpleWidgetMaker;
import com.citytechinc.cq.component.dialog.widget.WidgetParameters;

@Widget(annotationClass = TextField.class, makerClass = SimpleWidgetMaker.class, xtype = TextFieldWidget.XTYPE)
public class TextFieldWidget extends AbstractWidget {

	public static final String XTYPE = "textfield";

	public TextFieldWidget(WidgetParameters parameters) {
		super(parameters);
	}
}
