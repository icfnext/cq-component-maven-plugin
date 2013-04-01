package com.citytechinc.cq.component.maven.util;

import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class WidgetConfigHolder {
	private Class<?> annotationClass;
	private Class<? extends AbstractWidget> widgetClass;
	private Class<? extends WidgetMaker> makerClass;
	private String[] xtypes;
	
	public WidgetConfigHolder(Class<?> annotationClass,Class<? extends AbstractWidget> widgetClass,Class<? extends WidgetMaker> makerClass,String[] xtypes){
		this.annotationClass=annotationClass;
		this.widgetClass=widgetClass;
		this.makerClass=makerClass;
		this.xtypes=xtypes;
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
	public String[] getXtypes() {
		return xtypes;
	}
}
