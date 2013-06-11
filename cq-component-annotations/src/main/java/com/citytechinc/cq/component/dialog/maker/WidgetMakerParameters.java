package com.citytechinc.cq.component.dialog.maker;

import javassist.ClassPool;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;

public class WidgetMakerParameters {

	private DialogField annotation;
	private CtMember ctMember;
	private Class<?> containingClass;
	private ClassLoader classLoader;
	private ClassPool classPool;
	private WidgetRegistry widgetRegistry;
	private String xtype;
	private boolean useDotSlashInName;

	public WidgetMakerParameters(DialogField annotation, CtMember ctMember, Class<?> containingClass,
		ClassLoader classLoader, ClassPool classPool, WidgetRegistry widgetRegistry, String xtype,
		boolean useDotSlashInName) throws InvalidComponentFieldException, NotFoundException {

		this.annotation = annotation;
		this.ctMember = ctMember;
		this.containingClass = containingClass;
		this.classLoader = classLoader;
		this.classPool = classPool;
		this.widgetRegistry = widgetRegistry;
		this.xtype = xtype;
		this.useDotSlashInName = useDotSlashInName;
	}

	public DialogField getAnnotation() {
		return annotation;
	}

	public void setAnnotation(DialogField annotation) {
		this.annotation = annotation;
	}

	public CtMember getCtMember() {
		return ctMember;
	}

	public void setCtMember(CtMember ctMember) {
		this.ctMember = ctMember;
	}

	public Class<?> getContainingClass() {
		return containingClass;
	}

	public void setContainingClass(Class<?> containingClass) {
		this.containingClass = containingClass;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public ClassPool getClassPool() {
		return classPool;
	}

	public void setClassPool(ClassPool classPool) {
		this.classPool = classPool;
	}

	public WidgetRegistry getWidgetRegistry() {
		return widgetRegistry;
	}

	public void setWidgetRegistry(WidgetRegistry widgetRegistry) {
		this.widgetRegistry = widgetRegistry;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public boolean isUseDotSlashInName() {
		return useDotSlashInName;
	}

	public void setUseDotSlashInName(boolean useDotSlashInName) {
		this.useDotSlashInName = useDotSlashInName;
	}
}
