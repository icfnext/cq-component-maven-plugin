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

import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import com.citytechinc.cq.component.dialog.exception.OutputFailureException;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogWriteException;
import com.citytechinc.cq.component.touchuidialog.factory.TouchUIDialogFactory;
import javassist.CtClass;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TouchUIDialogUtil {

    private TouchUIDialogUtil() {}

    public static List<TouchUIDialog> buildDialogsFromClassList(
            List<CtClass> classList,
            ComponentNameTransformer transformer,
            File buildDirectory,
            String componentPathBase,
            String defaultComponentPathSuffix,
            ZipArchiveOutputStream archiveStream,
            Set<String> reservedNames
    ) throws TouchUIDialogGenerationException, TouchUIDialogWriteException {

        List<TouchUIDialog> dialogList = new ArrayList<TouchUIDialog>();

        LogSingleton.getInstance().info("I was asked to build Touch UI Dialogs");

        for(CtClass currentComponentClass : classList) {
            TouchUIDialog currentDialog = TouchUIDialogFactory.make(currentComponentClass);

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

}
