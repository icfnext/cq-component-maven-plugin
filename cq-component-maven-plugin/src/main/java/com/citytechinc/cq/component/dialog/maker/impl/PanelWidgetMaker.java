package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.Panel;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.PanelWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class PanelWidgetMaker extends AbstractWidgetMaker {
	private static final String ITEMS = "items";

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException {

		Panel panelAnnotation = (Panel) ctWidgetField.getAnnotation(Panel.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		boolean collapseFirst = panelAnnotation.collapseFirst();
		boolean collapsible = panelAnnotation.collapsible();
		boolean collapsed = panelAnnotation.collapsed();
		boolean border = panelAnnotation.border();
		String title = null;
		if (!StringUtils.isEmpty(panelAnnotation.title())) {
			title = panelAnnotation.title();
		}

		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		List<DialogElement> widgetCollection = buildWidgetCollection(ctContainingClass, ctWidgetField, widgetField,
			xtypeMap, xTypeToWidgetMakerMap, classLoader, classPool);

		PanelWidget widget = new PanelWidget(collapseFirst, collapsible, collapsed, border, title, fieldLabel, fieldDescription,
			hideLabel, fieldName, additionalProperties, widgetCollection);
		
		setListeners(widget,dialogFieldAnnotation.listeners());
		
		return widget;
	}

	private List<DialogElement> buildWidgetCollection(CtClass componentClass, CtMember curField,
		AccessibleObject trueField, Map<Class<?>, WidgetConfigHolder> classToXTypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool)
		throws InvalidComponentFieldException, ClassNotFoundException, CannotCompileException, NotFoundException,
		SecurityException, NoSuchFieldException {
		CtClass clazz = null;
		if (curField instanceof CtField) {
			CtField field = (CtField) curField;
			clazz = field.getType();
		} else {
			CtMethod method = (CtMethod) curField;
			clazz = method.getReturnType();
		}
		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(clazz));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(clazz));
		List<DialogElement> elements = new ArrayList<DialogElement>();
		for (CtMember field : fieldsAndMethods) {
			if (field.hasAnnotation(DialogField.class)) {
				AccessibleObject mcTrueField = null;
				Class<?> fieldClass = classLoader.loadClass(field.getDeclaringClass().getName());
				if (field instanceof CtField) {
					mcTrueField = ComponentMojoUtil.getField(fieldClass, field.getName());
				} else {
					mcTrueField = ComponentMojoUtil.getMethod(fieldClass, field.getName());
				}
				DialogElement builtFieldWidget = WidgetFactory.make(componentClass, field, mcTrueField,
					classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool, false, -1);
				elements.add(builtFieldWidget);
			}
		}
		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, ITEMS) });
	}

}
