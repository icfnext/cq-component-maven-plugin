package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtMember;
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
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class PanelWidgetMaker extends AbstractWidgetMaker {
	public PanelWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	private static final String ITEMS = "items";

	public DialogElement make() throws InvalidComponentFieldException, NotFoundException, ClassNotFoundException,
		SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		Panel panelAnnotation = getAnnotation(Panel.class);

		String parametersName = getFieldNameForField();
		String parametersLabel = getFieldLabelForField();
		String parametersDescription = getFieldDescriptionForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		boolean hideLabel = getHideLabelForField();

		boolean collapseFirst = panelAnnotation.collapseFirst();
		boolean collapsible = panelAnnotation.collapsible();
		boolean collapsed = panelAnnotation.collapsed();
		boolean border = panelAnnotation.border();
		String title = getTitleForField(panelAnnotation);

		List<DialogElement> widgetCollection = buildWidgetCollection();

		PanelWidget widget = new PanelWidget(collapseFirst, collapsible, collapsed, border, title, parametersLabel,
			parametersDescription, hideLabel, parametersName, additionalProperties, widgetCollection);

		setListeners(widget);

		return widget;

	}

	protected String getTitleForField(Panel panelAnnotation) {
		String title = panelAnnotation.title();
		if (StringUtils.isNotEmpty(title)) {
			return title;
		}

		return null;
	}

	private List<DialogElement> buildWidgetCollection() throws InvalidComponentFieldException, NotFoundException,
		ClassNotFoundException, SecurityException, CannotCompileException, NoSuchFieldException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(getCtType()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(getCtType()));

		List<DialogElement> elements = new ArrayList<DialogElement>();

		for (CtMember curField : fieldsAndMethods) {
			if (curField.hasAnnotation(DialogField.class)) {

				Class<?> fieldClass = parameters.getClassLoader().loadClass(curField.getDeclaringClass().getName());

				WidgetMakerParameters curFieldMember = new WidgetMakerParameters(
					(DialogField) curField.getAnnotation(DialogField.class), curField, fieldClass,
					parameters.getClassLoader(), parameters.getClassPool(), parameters.getWidgetRegistry(), null, true);

				DialogElement builtFieldWidget = WidgetFactory.make(curFieldMember, -1);

				elements.add(builtFieldWidget);
			}
		}

		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, ITEMS) });

	}

}
