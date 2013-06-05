package com.citytechinc.cq.component.dialog.maker;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;

/**
 * The interface which all concrete WidgetMakers will implement. A
 * WidgetMaker is a class which is intended to create a single Widget based
 * on an annotated Field in a Component Class. Such a Maker will return an
 * object implementing DialogElement suitable for addition into a Dialog.
 *
 */
public interface WidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype) throws ClassNotFoundException, InvalidComponentFieldException, NotFoundException, SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException;

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException, NotFoundException, SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
