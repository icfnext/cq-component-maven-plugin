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
package com.citytechinc.cq.component.touchuidialog.widget.dialogfieldset;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.util.DialogUtil;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementComparator;
import com.citytechinc.cq.component.touchuidialog.container.ContainerParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.LayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.well.WellLayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.well.WellLayoutElementParameters;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogFieldSetWidgetMaker extends AbstractTouchUIWidgetMaker {

    public DialogFieldSetWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException {

        DialogFieldSetWidgetParameters widgetParameters = new DialogFieldSetWidgetParameters();
        DialogFieldSet dialogFieldSetAnnotation = getAnnotation(DialogFieldSet.class);

        //Common properties
        widgetParameters.setFieldName(getFieldNameForField());

        //TODO: The rest of the common properties

        widgetParameters.setResourceType(DialogFieldSetWidget.RESOURCE_TYPE);
        
        try {
            widgetParameters.setItems(buildLayoutItems(dialogFieldSetAnnotation));
        } catch (NotFoundException e) {
            throw new TouchUIDialogGenerationException("Exception encountered while constructing contained elements for the DialogFieldSet " + parameters.getDialogFieldConfig().getFieldName() + " of class " + parameters.getContainingClass().getName(), e);
        }

        return new DialogFieldSetWidget(widgetParameters);

    }

    private List<TouchUIDialogElement> buildLayoutItems(DialogFieldSet dialogFieldSetAnnotation) throws NotFoundException, InvalidComponentFieldException, ClassNotFoundException, TouchUIDialogGenerationException {

        List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();

        fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(getCtType()));
        fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(getCtType()));

        List<TouchUIDialogElement> elements = new ArrayList<TouchUIDialogElement>();

        for (CtMember member : fieldsAndMethods) {
            if (!member.hasAnnotation(IgnoreDialogField.class)) {

                DialogFieldConfig dialogFieldConfig = null;
                if (member instanceof CtMethod) {
                    try {
                        dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
                    } catch (InvalidComponentClassException e) {
                        throw new InvalidComponentFieldException(e.getMessage(), e);
                    }
                } else {
                    if (member.hasAnnotation(DialogField.class)) {
                        dialogFieldConfig = new DialogFieldConfig(
                                (DialogField) member.getAnnotation(DialogField.class), member);
                    }
                }

                if (dialogFieldConfig != null) {
                    Class<?> fieldClass = parameters.getClassLoader().loadClass(member.getDeclaringClass().getName());

                    double ranking = dialogFieldConfig.getRanking();

                    TouchUIWidgetMakerParameters curFieldMember = new TouchUIWidgetMakerParameters();
                    curFieldMember.setDialogFieldConfig(dialogFieldConfig);
                    curFieldMember.setContainingClass(fieldClass);
                    curFieldMember.setClassLoader(parameters.getClassLoader());
                    curFieldMember.setClassPool(parameters.getClassPool());
                    curFieldMember.setWidgetRegistry(parameters.getWidgetRegistry());
                    curFieldMember.setUseDotSlashInName(true);

                    TouchUIDialogElement currentDialogElement = TouchUIWidgetFactory.make(curFieldMember, -1);

                    if (    currentDialogElement instanceof AbstractTouchUIWidget &&
                            StringUtils.isNotBlank(dialogFieldSetAnnotation.namePrefix())) {

                        AbstractTouchUIWidget widget = (AbstractTouchUIWidget) currentDialogElement;
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

                    currentDialogElement.setRanking(ranking);
                    elements.add(currentDialogElement);
                }
            }
        }

        Collections.sort(elements, new TouchUIDialogElementComparator());

        return elements;
    }

}
