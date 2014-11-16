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
package com.citytechinc.cq.component.touchuidialog.factory;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialog;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import javassist.CtClass;

public class TouchUIDialogFactory {

    private TouchUIDialogFactory() {}

    public static TouchUIDialog make(
            CtClass componentClass
    ) throws TouchUIDialogGenerationException {
        try {

            Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

            TouchUIDialogParameters parameters = new TouchUIDialogParameters();

            parameters.setTitle(componentAnnotation.value());

            return new TouchUIDialog(parameters);

        } catch (ClassNotFoundException e) {
            throw new TouchUIDialogGenerationException("ClassNotFound exception encountered generating Touch UI Dialog", e);
        }
    }

}
