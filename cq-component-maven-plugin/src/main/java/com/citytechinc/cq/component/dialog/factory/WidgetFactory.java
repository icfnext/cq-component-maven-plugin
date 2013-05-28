package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.MultiFieldWidget;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.dialog.maker.impl.SimpleWidgetMaker;
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

	public static DialogElement make(CtClass componentClass, CtMember annotatedWidgetField,
		AccessibleObject widgetField, Map<Class<?>, WidgetConfigHolder> classToXTypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName, int rankingCeiling) throws InvalidComponentFieldException, ClassNotFoundException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		DialogField propertyAnnotation = (DialogField) annotatedWidgetField.getAnnotation(DialogField.class);

		if (propertyAnnotation == null) {
			throw new InvalidComponentFieldException();
		}

		String xtype = getXTypeForField(widgetField, annotatedWidgetField, propertyAnnotation, classToXTypeMap,
			classLoader, classPool, rankingCeiling);

		Class<?> containingClass = classLoader.loadClass(componentClass.getName());

		if (!xTypeToWidgetMakerMap.containsKey(xtype)) {
			return new SimpleWidgetMaker().make(xtype, widgetField, annotatedWidgetField, containingClass,
				componentClass, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool, useDotSlashInName);
		}

		return xTypeToWidgetMakerMap.get(xtype).make(xtype, widgetField, annotatedWidgetField, containingClass,
			componentClass, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool, useDotSlashInName);

	}

	/**
	 * <p>
	 * Three mechanisms are employed in attempting to determine the xtype of a
	 * particular field.
	 * </p>
	 * 
	 * <ol>
	 * <li>Declared xtype: If the xtype property of the DialogField annotation
	 * is set, this xtype is used</li>
	 * <li>Mapped Classes: If an xtype is defined explicitly for the class of
	 * the field or if a stacked annotation is present to which an xtype is
	 * mapped, this xtype is used</li>
	 * <li>Guessed xtype: If mechanism 1 and 2 do not produce an xtype, a
	 * guessed xtype may be provided for a number of simple xtypes.</li>
	 * </ol>
	 * 
	 * <p>
	 * If none of these mechanisms produce an xtype, an
	 * InvalidComponentFieldException is thrown.
	 * </p>
	 * 
	 * <p>
	 * The following mapping is the basis for xtype guessing:
	 * </p>
	 * 
	 * <ul>
	 * <li>Numbers and primative numeric types -&gt; numberfield</li>
	 * <li>String -&gt; textfield</li>
	 * <li>URI and URL -&gt; pathfield</li>
	 * <li>Enum -&gt; selection</li>
	 * <li>List and Array -&gt; multifield</li>
	 * </ul>
	 * 
	 * @param widgetField
	 * @param ctWidgetField
	 * @param propertyAnnotation
	 * @param classToXTypeMap
	 * @param classLoader
	 * @param classPool
	 * @return
	 * @throws InvalidComponentFieldException
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws ClassNotFoundException
	 */
	private static final String getXTypeForField(AccessibleObject widgetField, CtMember ctWidgetField,
		DialogField propertyAnnotation, Map<Class<?>, WidgetConfigHolder> classToXTypeMap, ClassLoader classLoader,
		ClassPool classPool, int rankingCeiling) throws InvalidComponentFieldException, CannotCompileException,
		NotFoundException, ClassNotFoundException {

		/*
		 * Handle annotated xtypes
		 * 
		 */

		Class<?> fieldClass = null;
		if (widgetField instanceof Field) {
			Field field = (Field) widgetField;
			fieldClass = field.getType();
		} else if (widgetField instanceof Method) {
			Method method = (Method) widgetField;
			fieldClass = method.getReturnType();
		} else {
			throw new InvalidComponentFieldException("Only methods and fields can be annotated");
		}

		/*
		 * Handle custom types.
		 * 
		 * Custom types may be either Classes or Annotations.
		 */
		Set<WidgetConfigHolder> possibleMatches = new HashSet<WidgetConfigHolder>();
		for (Class<?> curCustomClass : classToXTypeMap.keySet()) {
			if (curCustomClass.isAnnotation()) {

				if (ctWidgetField.hasAnnotation(curCustomClass)) {
					WidgetConfigHolder widget = classToXTypeMap.get(curCustomClass);
					if (isBelowRankingCeiling(rankingCeiling, widget)) {
						possibleMatches.add(widget);
					}
				}

			} else if (curCustomClass.isAssignableFrom(fieldClass)) {
				WidgetConfigHolder widget = classToXTypeMap.get(curCustomClass);
				if (isBelowRankingCeiling(rankingCeiling, widget)) {
					possibleMatches.add(widget);
				}
			}
		}
		if (possibleMatches.size() > 0) {
			return getMatchFromPossibleSet(possibleMatches).getXtype();
		}
		
		/*
		* The xtype property on the DialogField annotation takes precedence
		* over all other mechanisms of determining xtype.
		*/
		String overrideXType = propertyAnnotation.xtype();
		
		if (StringUtils.isNotEmpty(overrideXType)) {
			return overrideXType;
		}
		
		/*
		 * Handle standard types
		 */

		/*
		 * numberfield
		 */
		if (Number.class.isAssignableFrom(fieldClass) || fieldClass.equals(int.class)
			|| fieldClass.equals(double.class) || fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 * pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		/*
		 * selection
		 */
		if (fieldClass.isEnum()) {
			return SELECTION_XTYPE;
		}

		if (List.class.isAssignableFrom(fieldClass) || fieldClass.isArray()) {

			String simpleXtype = getInnerXTypeForField(widgetField, classToXTypeMap, rankingCeiling, fieldClass);

			if (simpleXtype == null) {
				throw new InvalidComponentFieldException(
					"Parameterized class for List is not of a supported type.  Currently supported types are numbers, strings, and links");
			}
			if (rankingCeiling < MultiFieldWidget.RANKING) {
				return MULTIFIELD_XTYPE;
			} else {
				return simpleXtype;
			}

		}

		/*
		 * If we could not determine an xtype, throw an exception
		 */
		throw new InvalidComponentFieldException("An xtype could not be determined for the field {}"
			+ ctWidgetField.getName());
	}

	private static final String getInnerXTypeForField(AccessibleObject widgetField,
		Map<Class<?>, WidgetConfigHolder> xtypeMap, int rankingCeiling, Class<?> fieldClass)
		throws InvalidComponentFieldException {

		if (List.class.isAssignableFrom(fieldClass)) {
			return getInnerXTypeForListField(widgetField, xtypeMap, rankingCeiling);
		}
		if (fieldClass.isArray()) {
			return getInnerXTypeForArrayField(widgetField, xtypeMap, rankingCeiling);
		}

		throw new InvalidComponentFieldException(
			"List dialog property found with a paramaterized type count not equal to 1");

	}

	private static final String getInnerXTypeForListField(AccessibleObject widgetField,
		Map<Class<?>, WidgetConfigHolder> xtypeMap, int rankingCeiling) throws InvalidComponentFieldException {
		ParameterizedType parameterizedType = null;
		if (widgetField instanceof Field) {
			Field field = (Field) widgetField;
			parameterizedType = (ParameterizedType) field.getGenericType();
		} else {
			Method method = (Method) widgetField;
			parameterizedType = (ParameterizedType) method.getGenericReturnType();
		}

		if (parameterizedType.getActualTypeArguments().length == 0
			|| parameterizedType.getActualTypeArguments().length > 1) {
			throw new InvalidComponentFieldException(
				"List dialog property found with a paramaterized type count not equal to 1");
		}

		String simpleXtype = getSimpleXTypeForClass((Class<?>) parameterizedType.getActualTypeArguments()[0], xtypeMap,
			rankingCeiling);

		return simpleXtype;
	}

	private static final String getInnerXTypeForArrayField(AccessibleObject widgetField,
		Map<Class<?>, WidgetConfigHolder> xtypeMap, int rankingCeiling) {
		Class<?> fieldClass = null;
		if (widgetField instanceof Field) {
			Field field = (Field) widgetField;
			fieldClass = field.getType();
		} else {
			Method method = (Method) widgetField;
			fieldClass = method.getReturnType();
		}

		return getSimpleXTypeForClass(fieldClass.getComponentType(), xtypeMap, rankingCeiling);
	}

	private static final String getSimpleXTypeForClass(Class<?> fieldClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		int rankingCeiling) {

		/*
		 * Handle custom types
		 */
		Set<WidgetConfigHolder> possibleMatches = new HashSet<WidgetConfigHolder>();
		for (Class<?> curCustomClass : xtypeMap.keySet()) {
			WidgetConfigHolder widget = xtypeMap.get(curCustomClass);
			if (curCustomClass.isAssignableFrom(fieldClass) && isBelowRankingCeiling(rankingCeiling, widget)) {
				possibleMatches.add(widget);
			}
		}
		if (possibleMatches.size() > 0) {
			return getMatchFromPossibleSet(possibleMatches).getXtype();
		}

		/*
		 * numberfield
		 */
		if (Number.class.isAssignableFrom(fieldClass) || fieldClass.equals(int.class)
			|| fieldClass.equals(double.class) || fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 * pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		return null;

	}

	private static final boolean isBelowRankingCeiling(int rankingCeiling, WidgetConfigHolder widget) {
		return !(rankingCeiling > 0 && widget.getRanking() >= rankingCeiling);
	}

	private static final WidgetConfigHolder getMatchFromPossibleSet(Set<WidgetConfigHolder> possibleMatches) {
		WidgetConfigHolder match = null;
		for (WidgetConfigHolder possibleMatch : possibleMatches) {
			if (match == null || match.getRanking() < possibleMatch.getRanking()) {
				match = possibleMatch;
			}
		}
		return match;
	}
}
