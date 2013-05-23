package com.citytechinc.cq.component.maven.util;

import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

/**
 * A container for the elements of configuration which define a Widget.
 * 
 * @see com.citytechinc.cq.component.annotations.config.Widget
 * @see com.citytechinc.cq.component.dialog.AbstractWidget
 * @see com.citytechinc.cq.component.dialog.maker.WidgetMaker
 */
public class WidgetConfigHolder {
	private final Class<?> annotationClass;
	private final Class<? extends AbstractWidget> widgetClass;
	private final Class<? extends WidgetMaker> makerClass;
	private final String xtype;
	private final int ranking;

	public WidgetConfigHolder(Class<?> annotationClass, Class<? extends AbstractWidget> widgetClass,
		Class<? extends WidgetMaker> makerClass, String xtype, int ranking) {
		this.annotationClass = annotationClass;
		this.widgetClass = widgetClass;
		this.makerClass = makerClass;
		this.xtype = xtype;
		this.ranking = ranking;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}

	public Class<? extends AbstractWidget> getWidgetClass() {
		return widgetClass;
	}

	public Class<? extends WidgetMaker> getMakerClass() {
		return makerClass;
	}

	public String getXtype() {
		return xtype;
	}

	public int getRanking() {
		return ranking;
	}
}
