package com.citytechinc.cq.component.touchuidialog.widget.maker;

import javassist.ClassPool;
import javassist.CtMember;

import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.touchuidialog.widget.registry.TouchUIWidgetRegistry;

public class TouchUIWidgetMakerParameters {

	private DialogFieldConfig dialogFieldConfig;
	private Class<?> containingClass;
	private ClassLoader classLoader;
	private ClassPool classPool;
	private String resourceType;
	private boolean useDotSlashInName;
	private TouchUIWidgetRegistry widgetRegistry;

	public DialogFieldConfig getDialogFieldConfig() {
		return dialogFieldConfig;
	}

	public void setDialogFieldConfig(DialogFieldConfig dialogFieldConfig) {
		this.dialogFieldConfig = dialogFieldConfig;
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public boolean isUseDotSlashInName() {
		return useDotSlashInName;
	}

	public void setUseDotSlashInName(boolean useDotSlashInName) {
		this.useDotSlashInName = useDotSlashInName;
	}

	/**
	 *
	 * @return The CtMember representing the Widget
	 */
	public CtMember getCtMember() {
		return dialogFieldConfig.getMember();
	}

	public TouchUIWidgetRegistry getWidgetRegistry() {
		return widgetRegistry;
	}

	public void setWidgetRegistry(TouchUIWidgetRegistry widgetRegistry) {
		this.widgetRegistry = widgetRegistry;
	}
}
