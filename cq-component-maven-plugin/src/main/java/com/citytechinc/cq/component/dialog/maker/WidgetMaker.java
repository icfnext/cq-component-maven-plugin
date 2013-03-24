package com.citytechinc.cq.component.dialog.maker;

import java.lang.reflect.Field;
import java.util.Map;

import com.citytechinc.cq.component.dialog.Widget;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

public interface WidgetMaker {

	public Widget make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap) throws ClassNotFoundException, InvalidComponentFieldException, CannotCompileException, NotFoundException;

}
