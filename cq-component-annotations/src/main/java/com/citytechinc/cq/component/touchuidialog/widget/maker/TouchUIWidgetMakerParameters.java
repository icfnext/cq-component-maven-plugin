/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
	private String touchUIDialogType;

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

	public String getTouchUIDialogType() {
		return touchUIDialogType;
	}

	public void setTouchUIDialogType(String touchUIDialogType) {
		this.touchUIDialogType = touchUIDialogType;
	}
}