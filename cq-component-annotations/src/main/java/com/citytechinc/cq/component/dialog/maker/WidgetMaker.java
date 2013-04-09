package com.citytechinc.cq.component.dialog.maker;

import java.lang.reflect.Field;
import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

public interface WidgetMaker {

	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, String> xtypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap,
		ClassLoader classLoader, ClassPool classPool, boolean useDotSlashInName) throws ClassNotFoundException,
		InvalidComponentFieldException, CannotCompileException, NotFoundException, SecurityException,
		NoSuchFieldException;
}
