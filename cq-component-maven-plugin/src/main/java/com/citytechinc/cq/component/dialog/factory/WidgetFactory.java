package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.MultiFieldWidget;
import com.citytechinc.cq.component.dialog.maker.impl.SimpleWidgetMaker;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class WidgetFactory {

	public static final String TEXTFIELD_XTYPE = "textfield";
	public static final String NUMBERFIELD_XTYPE = "numberfield";
	public static final String PATHFIELD_XTYPE = "pathfield";
	public static final String SELECTION_XTYPE = "selection";
	public static final String MULTIFIELD_XTYPE = "multifield";
	public static final String HTML5SMARTIMAGE_XTYPE = "html5smartimage";

	private WidgetFactory() {
	}

	public static DialogElement make(DialogFieldMember dialogFieldMember,
		boolean useDotSlashInName, int rankingCeiling) throws InvalidComponentFieldException, ClassNotFoundException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException, InstantiationException, IllegalAccessException {

		WidgetMakerContext widgetMakerContext = getWidgetMakerForField(dialogFieldMember.getWidgetRegistry(), dialogFieldMember, rankingCeiling);

		return widgetMakerContext.getWidgetMaker().make(dialogFieldMember, widgetMakerContext.getXtype(), useDotSlashInName);

	}

	private static final WidgetMakerContext getWidgetMakerForField(WidgetRegistry widgetRegistry, DialogFieldMember dialogFieldMember, int rankingCeiling) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvalidComponentFieldException {

		WidgetConfigHolder widget = dialogFieldMember.getWidgetConfig(rankingCeiling);

		if (widget != null && widget.hasMakerClass()) {
			return new WidgetMakerContext(widget.getMakerClass().newInstance(), widget.getXtype());
		}

		if (widget != null && widget.hasXtype()) {
			return new WidgetMakerContext(new SimpleWidgetMaker(), widget.getXtype());
		}

		String xtype = getXTypeForField(dialogFieldMember, rankingCeiling);

		if (StringUtils.isEmpty(xtype)) {
			throw new InvalidComponentFieldException("An xtype could not be determined for the field {}"
				+ dialogFieldMember.getName());
		}

		return new WidgetMakerContext(new SimpleWidgetMaker(), xtype);
	}

	private static final String getXTypeForField(DialogFieldMember field, Integer rankingCeiling) throws InvalidComponentFieldException {

		if (StringUtils.isNotEmpty(field.getAnnotation().xtype())) {
			return field.getAnnotation().xtype();
		}

		/*
		 * Handle standard types
		 */

		Class<?> fieldClass = field.getType();

		String simpleXType = getSimpleXTypeForClass(fieldClass);

		if (StringUtils.isNotEmpty(simpleXType)) {
			return simpleXType;
		}

		/*
		 * selection
		 */
		if (fieldClass.isEnum()) {
			return SELECTION_XTYPE;
		}

		if (List.class.isAssignableFrom(fieldClass) || fieldClass.isArray()) {

			String simpleInnerXtype = getInnerXTypeForField(field, rankingCeiling);

			if (simpleInnerXtype == null) {
				throw new InvalidComponentFieldException(
					"Parameterized class for List is not of a supported type.  Currently supported types are numbers, strings, and uri/url");
			}
			if (rankingCeiling < MultiFieldWidget.RANKING) {
				return MULTIFIELD_XTYPE;
			} else {
				return simpleInnerXtype;
			}

		}

		return null;
	}

	private static final String getInnerXTypeForField(DialogFieldMember field, Integer rankingCeiling) throws InvalidComponentFieldException {

		if (List.class.isAssignableFrom(field.getType())) {
			return getInnerXTypeForListField(field);
		}
		if (field.getType().isArray()) {
			return getInnerXTypeForArrayField(field);
		}

		return null;

	}


	private static final String getInnerXTypeForListField(DialogFieldMember field) throws InvalidComponentFieldException {

		ParameterizedType parameterizedType = field.getParameterizedType();

		if (parameterizedType.getActualTypeArguments().length == 0
			|| parameterizedType.getActualTypeArguments().length > 1) {
			throw new InvalidComponentFieldException(
				"List dialog property found with a paramaterized type count not equal to 1");
		}

		return getSimpleXTypeForClass((Class<?>) parameterizedType.getActualTypeArguments()[0]);

	}

	private static final String getInnerXTypeForArrayField(DialogFieldMember field) {
		return getSimpleXTypeForClass(field.getType());
	}

	private static final String getSimpleXTypeForClass(Class<?> clazz) {

		/*
		 * numberfield
		 */
		if (Number.class.isAssignableFrom(clazz) || clazz.equals(int.class)
			|| clazz.equals(double.class) || clazz.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (clazz.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 * pathfield
		 */
		if (URI.class.isAssignableFrom(clazz) || URL.class.isAssignableFrom(clazz)) {
			return PATHFIELD_XTYPE;
		}

		return null;

	}


}
