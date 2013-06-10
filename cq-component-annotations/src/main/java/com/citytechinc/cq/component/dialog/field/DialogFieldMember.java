package com.citytechinc.cq.component.dialog.field;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.ParameterizedType;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public interface DialogFieldMember {

	public DialogField getAnnotation();

	public <T> T getAnnotation(Class<T> annotationClass) throws ClassNotFoundException;

	public CtMember getCtMember();

	public AccessibleObject getMember();

	public Class<?> getContainingClass();

	public ClassLoader getClassLoader();

	public ClassPool getClassPool();

	public boolean isField();

	public boolean isMethod();

	public WidgetConfigHolder getWidgetConfig() throws ClassNotFoundException;

	public WidgetConfigHolder getWidgetConfig(Integer rankCeiling) throws ClassNotFoundException;

	public String getName();

	public Class<?> getType();

	public ParameterizedType getParameterizedType();

	public CtClass getCtType();

	public WidgetRegistry getWidgetRegistry();

}
