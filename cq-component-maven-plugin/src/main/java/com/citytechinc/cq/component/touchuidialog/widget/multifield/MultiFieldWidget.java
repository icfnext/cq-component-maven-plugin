package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

@TouchUIWidget(annotationClass = MultiField.class, makerClass = MultiFieldWidgetMaker.class,
	resourceType = MultiFieldWidget.RESOURCE_TYPE, ranking = MultiFieldWidget.RANKING)
public class MultiFieldWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/multifield";
	public static final int RANKING = 1000000;

	public MultiFieldWidget(DefaultTouchUIWidgetParameters parameters) {
		super(parameters);
	}

}
