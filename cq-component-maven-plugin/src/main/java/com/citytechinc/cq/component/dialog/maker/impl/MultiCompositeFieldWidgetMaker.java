package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.MultiCompositeField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.MultiCompositeFieldWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class MultiCompositeFieldWidgetMaker extends AbstractWidgetMaker {
	private static final String FIELD_CONFIGS = "fieldConfigs";

	@Override
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		MultiCompositeField multiCompositeFieldAnnotation = (MultiCompositeField) ctWidgetField
			.getAnnotation(MultiCompositeField.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		boolean matchBaseName = multiCompositeFieldAnnotation.matchBaseName();
		String prefix = multiCompositeFieldAnnotation.prefix();

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		List<DialogElement> widgetCollection = buildWidgetCollection(ctContainingClass, ctWidgetField, widgetField,
			xtypeMap, xTypeToWidgetMakerMap, classLoader, classPool);

		return new MultiCompositeFieldWidget(matchBaseName, prefix, fieldLabel, fieldDescription, !isRequired,
			hideLabel, defaultValue, name, fieldName, additionalProperties, widgetCollection);
	}

	private List<DialogElement> buildWidgetCollection(CtClass componentClass, CtField curField, Field trueField,
		Map<Class<?>, WidgetConfigHolder> classToXTypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap,
		ClassLoader classLoader, ClassPool classPool) throws InvalidComponentFieldException, ClassNotFoundException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {
		CtClass clazz = curField.getType();
		if (List.class.isAssignableFrom(trueField.getType())) {
			ParameterizedType parameterizedType = (ParameterizedType) trueField.getGenericType();
			clazz = classPool.get(((Class<?>) parameterizedType.getActualTypeArguments()[0]).getName());
		}
		if (clazz.isArray()) {
			clazz = clazz.getComponentType();
		}
		CtField[] fields = ComponentMojoUtil.collectFields(clazz).toArray(new CtField[0]);
		List<DialogElement> elements = new ArrayList<DialogElement>();
		for (CtField field : fields) {
			if (field.hasAnnotation(DialogField.class)) {
				Field mcTrueField = classLoader.loadClass(field.getDeclaringClass().getName()).getDeclaredField(
					field.getName());
				DialogElement builtFieldWidget = WidgetFactory.make(componentClass, field, mcTrueField,
					classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool, false, -1);
				elements.add(builtFieldWidget);
			}
		}
		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, FIELD_CONFIGS) });
	}

}
