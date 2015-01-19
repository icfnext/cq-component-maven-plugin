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
package com.citytechinc.cq.component.touchuidialog.util;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.IgnoreDialogField;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.DialogFieldConfig;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.util.DialogUtil;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogWriteException;
import com.citytechinc.cq.component.touchuidialog.factory.TouchUIDialogFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.registry.TouchUIWidgetRegistry;
import javassist.*;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TouchUIDialogUtil {

    private TouchUIDialogUtil() {}

    public static List<TouchUIDialog> buildDialogsFromClassList(
            List<CtClass> classList,
            ClassLoader classLoader,
            ClassPool classPool,
            TouchUIWidgetRegistry widgetRegistry,
            ComponentNameTransformer transformer,
            File buildDirectory,
            String componentPathBase,
            String defaultComponentPathSuffix,
            ZipArchiveOutputStream archiveStream,
            Set<String> reservedNames
    ) throws TouchUIDialogGenerationException, TouchUIDialogWriteException {

        List<TouchUIDialog> dialogList = new ArrayList<TouchUIDialog>();

        for(CtClass currentComponentClass : classList) {
            TouchUIDialog currentDialog = TouchUIDialogFactory.make(currentComponentClass, classLoader, classPool, widgetRegistry);

            if (currentDialog != null) {
                File currentDialogOutput = writeDialogToFile(transformer, currentDialog, currentComponentClass, buildDirectory, componentPathBase, defaultComponentPathSuffix);
                writeDialogToArchiveFile(transformer, currentDialogOutput, currentComponentClass, archiveStream, reservedNames, componentPathBase, defaultComponentPathSuffix);
                dialogList.add(currentDialog);
            }
        }

        return dialogList;
    }


    public static File writeDialogToFile(
            ComponentNameTransformer transformer,
            TouchUIDialog dialog,
            CtClass componentClass,
            File buildDirectory,
            String componentPathBase,
            String defaultComponentPathSuffix
    ) throws TouchUIDialogWriteException {
        try {
            return ComponentMojoUtil.writeElementToFile(
                    transformer,
                    dialog,
                    componentClass,
                    buildDirectory,
                    componentPathBase,
                    defaultComponentPathSuffix,
                    dialog.getFileName());
        } catch (Exception e) {
            throw new TouchUIDialogWriteException("Exception encountered writing Dialog to File", e);
        }
    }

    public static void writeDialogToArchiveFile(
            ComponentNameTransformer transformer,
            File dialogFile,
            CtClass componentClass,
            ZipArchiveOutputStream archiveStream,
            Set<String> reservedNames,
            String componentPathBase,
            String defaultComponentPathSuffix) throws TouchUIDialogWriteException {

        try {
            ComponentMojoUtil.writeElementToArchiveFile(
                    transformer,
                    dialogFile,
                    componentClass,
                    archiveStream,
                    reservedNames,
                    componentPathBase,
                    defaultComponentPathSuffix, "/" + dialogFile.getName());
        } catch (Exception e) {
            throw new TouchUIDialogWriteException("Exception encountered while writing Dialog File to Archive", e);
        }

    }

    public static List<TouchUIWidgetMakerParameters> getWidgetMakerParametersForComponentClass(CtClass componentClass, ClassLoader classLoader, ClassPool classPool, TouchUIWidgetRegistry widgetRegistry) throws NotFoundException, ClassNotFoundException, InvalidComponentClassException {

        List<TouchUIWidgetMakerParameters> widgetMakerParametersList = new ArrayList<TouchUIWidgetMakerParameters>();

        List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
        fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass));
        fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass));

        // Load the true class
        Class<?> trueComponentClass = classLoader.loadClass(componentClass.getName());

        //Iterate through all the fields creating configs for each and preparing the necessary widget maker parameters
        for (CtMember member : fieldsAndMethods) {
            if (!member.hasAnnotation(IgnoreDialogField.class)) {
                DialogFieldConfig dialogFieldConfig = null;
                if (member instanceof CtMethod) {
                    dialogFieldConfig = DialogUtil.getDialogFieldFromSuperClasses((CtMethod) member);
                } else {
                    if (member.hasAnnotation(DialogField.class)) {
                        dialogFieldConfig = new DialogFieldConfig(
                                (DialogField) member.getAnnotation(DialogField.class), member);
                    }
                }

                if (dialogFieldConfig != null && !dialogFieldConfig.isSuppressTouchUI()) {
                    TouchUIWidgetMakerParameters touchUIWidgetMakerParameters = new TouchUIWidgetMakerParameters();
                    touchUIWidgetMakerParameters.setClassLoader(classLoader);
                    touchUIWidgetMakerParameters.setContainingClass(trueComponentClass);
                    touchUIWidgetMakerParameters.setDialogFieldConfig(dialogFieldConfig);
                    touchUIWidgetMakerParameters.setClassPool(classPool);
                    touchUIWidgetMakerParameters.setUseDotSlashInName(true);
                    touchUIWidgetMakerParameters.setWidgetRegistry(widgetRegistry);
                    widgetMakerParametersList.add(touchUIWidgetMakerParameters);
                }
            }
        }

        return widgetMakerParametersList;

    }

}
