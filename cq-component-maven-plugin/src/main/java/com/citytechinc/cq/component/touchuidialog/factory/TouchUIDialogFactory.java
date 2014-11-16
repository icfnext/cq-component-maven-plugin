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
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.tabs.TabsLayoutMaker;
import com.citytechinc.cq.component.xml.XmlElement;
import org.codehaus.plexus.util.StringUtils;
import javassist.CtClass;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TouchUIDialogFactory {

    private TouchUIDialogFactory() {}

    @Nullable
    public static TouchUIDialog make(
            CtClass componentClass
    ) throws TouchUIDialogGenerationException {
        try {

            Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

            //If output of the Touch UI dialog is disabled, return null
            if (componentAnnotation.suppressTouchUIDialog()) {
                return null;
            }

            TouchUIDialogParameters parameters = new TouchUIDialogParameters();

            parameters.setTitle(componentAnnotation.value());

            if (StringUtils.isNotBlank(componentAnnotation.helpPath())) {
                parameters.setHelpPath(componentAnnotation.helpPath());
            }

            //Determine the LayoutMaker to use
            LayoutMaker layoutMaker = new TabsLayoutMaker(new LayoutMakerParameters());

            //Delegate the rest of the production to the LayoutMaker
            Layout layout = layoutMaker.make();

            //Add the generated Layout to the Dialog's contained elements
            List<XmlElement> containedElements = new ArrayList<XmlElement>();
            containedElements.add(layout);

            parameters.setContainedElements(containedElements);

            return new TouchUIDialog(parameters);

        } catch (ClassNotFoundException e) {
            throw new TouchUIDialogGenerationException("ClassNotFound exception encountered generating Touch UI Dialog", e);
        }
    }

}
