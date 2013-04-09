package com.citytechinc.cq.component.maven;

import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class WidgetMakerMapping {

	private String makerClassName;
	private String xtype;

	public String getMakerClassName() {
		return makerClassName;
	}

	public String getXtype() {
		return xtype;
	}

	public Class<?> getMakerClass(ClassLoader classLoader) throws ClassNotFoundException {
		return classLoader.loadClass(getMakerClassName());
	}

	public WidgetMaker getMaker(ClassLoader classLoader) throws InstantiationException, IllegalAccessException,
		ClassNotFoundException {
		return (WidgetMaker) getMakerClass(classLoader).newInstance();
	}
}
