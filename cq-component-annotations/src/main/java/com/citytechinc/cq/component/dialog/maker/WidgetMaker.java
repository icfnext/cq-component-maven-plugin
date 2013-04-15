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

	/**
	 * The interface which all concrete WidgetMakers will implement.  A WidgetMaker is a class which is intended to
	 * create a single Widget based on an annotated Field in a Component Class.  Such a Maker will return an object
	 * implementing DialogElement suitable for addition into a Dialog.
	 *
	 * @param xtype
	 * @param widgetField
	 * @param ctWidgetField
	 * @param containingClass
	 * @param ctContainingClass
	 * @param xtypeMap
	 * @param xTypeToWidgetMakerMap
	 * @param classLoader
	 * @param classPool
	 * @param useDotSlashInName
	 * @return A constructed DialogElement
	 * @throws ClassNotFoundException
	 * @throws InvalidComponentFieldException
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, String> xtypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap,
		ClassLoader classLoader, ClassPool classPool, boolean useDotSlashInName) throws ClassNotFoundException,
		InvalidComponentFieldException, CannotCompileException, NotFoundException, SecurityException,
		NoSuchFieldException;
}
