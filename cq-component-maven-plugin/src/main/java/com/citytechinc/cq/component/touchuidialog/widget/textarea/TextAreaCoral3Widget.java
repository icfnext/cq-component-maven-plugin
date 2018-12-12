package com.citytechinc.cq.component.touchuidialog.widget.textarea;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = TextArea.class, makerClass = TextAreaWidgetMaker.class,
	resourceType = TextAreaCoral3Widget.RESOURCE_TYPE)
public class TextAreaCoral3Widget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/textarea";

	private final Integer cols;
	private final Integer rows;
	private final String resize;

	public TextAreaCoral3Widget(TextAreaWidgetParameters parameters) {

		super(parameters);

		this.cols = parameters.getCols();
		this.rows = parameters.getRows();
		this.resize = parameters.getResize();

	}

	public Integer getCols() {
		return cols;
	}

	public Integer getRows() {
		return rows;
	}

	public String getResize() {
		return resize;
	}

}
