/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.touchuidialog.widget.tagfield;

import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class TagFieldWidgetMaker extends AbstractTouchUIWidgetMaker<TagFieldWidgetParameters> {

    public TagFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(TagFieldWidgetParameters widgetParameters)
        throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException,
        IllegalAccessException, InstantiationException {
        TagInputField widgetAnnotation = getAnnotation(TagInputField.class);

        widgetParameters.setRootPath(getRootPathForField(widgetAnnotation));
        widgetParameters.setMultiple(widgetAnnotation.multiple());

        return new TagFieldWidget(widgetParameters);
    }

    public String getRootPathForField(TagInputField annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.rootPath())) {
            return annotation.rootPath();
        }

        return null;
    }
}
