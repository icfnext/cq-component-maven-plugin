/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog.dialogfieldset;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElementComparator;
import javassist.CannotCompileException;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;

public class DialogFieldSetWidgetMaker extends AbstractWidgetMaker {

	public DialogFieldSetWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	private static final String ITEMS = "items";

	public DialogElement make() throws ClassNotFoundException, SecurityException, InvalidComponentFieldException,
		NotFoundException, CannotCompileException, NoSuchFieldException, InstantiationException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		DialogFieldSet dialogFieldSetAnnotation = getAnnotation(DialogFieldSet.class);

		DialogFieldSetWidgetParameters widgetParameters = new DialogFieldSetWidgetParameters();

		widgetParameters.setFieldName(getFieldNameForField());
		widgetParameters.setFieldLabel(getFieldLabelForField());
		widgetParameters.setFieldDescription(getFieldDescriptionForField());
		widgetParameters.setAdditionalProperties(getAdditionalPropertiesForField());
		widgetParameters.setHideLabel(getHideLabelForField());

		widgetParameters.setCollapseFirst(dialogFieldSetAnnotation.collapseFirst());
		widgetParameters.setCollapsible(dialogFieldSetAnnotation.collapsible());
		widgetParameters.setCollapsed(dialogFieldSetAnnotation.collapsed());
		widgetParameters.setBorder(dialogFieldSetAnnotation.border());
		String title = null;
		if (!StringUtils.isEmpty(dialogFieldSetAnnotation.title())) {
			title = dialogFieldSetAnnotation.title();
		}
		widgetParameters.setTitle(title);
		widgetParameters.setListeners(getListeners());
		widgetParameters.setContainedElements(buildWidgetCollection(dialogFieldSetAnnotation));

		return new DialogFieldSetWidget(widgetParameters);

	}

	private List<DialogElement> buildWidgetCollection(DialogFieldSet dialogFieldSetAnnotation)
		throws InvalidComponentFieldException, NotFoundException, ClassNotFoundException, SecurityException,
		CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(getCtType()));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(getCtType()));

		List<DialogElement> elements = new ArrayList<DialogElement>();

		for (CtMember curField : fieldsAndMethods) {
			if (curField.hasAnnotation(DialogField.class)) {
				Class<?> fieldClass = parameters.getClassLoader().loadClass(curField.getDeclaringClass().getName());

                DialogField dialogField = (DialogField)curField.getAnnotation(DialogField.class);
                double ranking = dialogField.ranking();

				WidgetMakerParameters curFieldMember = new WidgetMakerParameters(
					dialogField, curField, fieldClass,
					parameters.getClassLoader(), parameters.getClassPool(), parameters.getWidgetRegistry(), null, true);

				DialogElement builtFieldWidget = WidgetFactory.make(curFieldMember, -1);
				if (builtFieldWidget instanceof AbstractWidget
					&& StringUtils.isNotEmpty(dialogFieldSetAnnotation.namePrefix())) {
					AbstractWidget widget = (AbstractWidget) builtFieldWidget;
					String name = widget.getName();
					String newName;
					if (name.startsWith("./")) {
						newName = name.substring(2);
					} else {
						newName = name;
					}
					newName = dialogFieldSetAnnotation.namePrefix() + newName;
					if (name.startsWith("./")) {
						newName = "./" + newName;
					}
					widget.setName(newName);
				}
                builtFieldWidget.setRanking(ranking);
				elements.add(builtFieldWidget);
			}
		}
        Collections.sort(elements, new DialogElementComparator());
		WidgetCollectionParameters wcp = new WidgetCollectionParameters();
		wcp.setContainedElements(elements);
		wcp.setFieldName(ITEMS);
		return Arrays.asList(new DialogElement[] { new WidgetCollection(wcp) });

	}
}
