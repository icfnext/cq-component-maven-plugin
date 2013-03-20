package com.citytechinc.cq.component.maven;

public class XtypeMapping {

	private String className;
	private String xtype;

	public String getClassName() {
		return className;
	}

	public String getXtype() {
		return xtype;
	}

	public Class<?> getClassObject(ClassLoader classLoader) throws ClassNotFoundException {
		return classLoader.loadClass(className);
	}
}
