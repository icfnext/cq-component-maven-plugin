package com.citytechinc.cq.component.dialog.maker;

import java.lang.reflect.InvocationTargetException;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;

/**
 * The interface which all concrete WidgetMakers will implement. A WidgetMaker
 * is a class which is intended to create a single Widget based on an annotated
 * Field in a Component Class. Such a Maker will return an object implementing
 * DialogElement suitable for addition into a Dialog.
 * 
 */
public interface WidgetMaker {

	public DialogElement make() throws InvalidComponentFieldException, NotFoundException, ClassNotFoundException,
		SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
}
