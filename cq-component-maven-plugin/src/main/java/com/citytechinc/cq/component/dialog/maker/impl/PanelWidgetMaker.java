package com.citytechinc.cq.component.dialog.maker.impl;

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
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.field.impl.DefaultDialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.PanelWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class PanelWidgetMaker extends AbstractWidgetMaker {
	private static final String ITEMS = "items";

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws InvalidComponentFieldException, NotFoundException, ClassNotFoundException, SecurityException,
		CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException {

		Panel panelAnnotation = field.getAnnotation(Panel.class);

		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean collapseFirst = panelAnnotation.collapseFirst();
		boolean collapsible = panelAnnotation.collapsible();
		boolean collapsed = panelAnnotation.collapsed();
		boolean border = panelAnnotation.border();
		String title = getTitleForField(panelAnnotation);

		List<DialogElement> widgetCollection = buildWidgetCollection(field);

		PanelWidget widget = new PanelWidget(collapseFirst, collapsible, collapsed, border, title, fieldLabel,
			fieldDescription, hideLabel, fieldName, additionalProperties, widgetCollection);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	protected String getTitleForField(Panel panelAnnotation) {
		String title = panelAnnotation.title();
		if (StringUtils.isNotEmpty(title)) {
			return title;
		}

		return null;
	}

	protected List<DialogElement> buildWidgetCollection(DialogFieldMember field) throws InvalidComponentFieldException,
		NotFoundException, ClassNotFoundException, SecurityException, CannotCompileException, NoSuchFieldException,
		InstantiationException, IllegalAccessException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(field.getCtType()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(field.getCtType()));

		List<DialogElement> elements = new ArrayList<DialogElement>();

		for (CtMember curField : fieldsAndMethods) {
			if (curField.hasAnnotation(DialogField.class)) {

				DialogFieldMember curFieldMember = new DefaultDialogFieldMember(
					(DialogField) curField.getAnnotation(DialogField.class), curField, field.getType(),
					field.getClassLoader(), field.getClassPool(), field.getWidgetRegistry());

				DialogElement builtFieldWidget = WidgetFactory.make(curFieldMember, false, -1);

				elements.add(builtFieldWidget);
			}
		}

		return Arrays.asList(new DialogElement[] { new WidgetCollection(elements, ITEMS) });
	}

}
