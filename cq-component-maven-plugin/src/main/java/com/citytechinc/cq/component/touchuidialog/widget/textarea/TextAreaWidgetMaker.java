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
package com.citytechinc.cq.component.touchuidialog.widget.textarea;

import com.citytechinc.cq.component.annotations.widgets.TextArea;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.textfield.TextFieldWidget;
import org.codehaus.plexus.util.StringUtils;

public class TextAreaWidgetMaker extends AbstractTouchUIWidgetMaker {

    public TextAreaWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException {

        TextAreaWidgetParameters widgetParameters = new TextAreaWidgetParameters();

        widgetParameters.setFieldName(getFieldNameForField());
        widgetParameters.setName(getNameForField());
        widgetParameters.setFieldLabel(getFieldLabelForField());
        widgetParameters.setFieldDescription(getFieldDescriptionForField());
        widgetParameters.setRequired(getRequiredForField());
        widgetParameters.setDefaultValue(getDefaultValueForField());
        widgetParameters.setResourceType(TextAreaWidget.RESOURCE_TYPE);
        widgetParameters.setValue(getValueForField());
        widgetParameters.setDisabled(getDisabledForField());
        widgetParameters.setCssClass(getCssClassForField());

        TextArea widgetAnnotation = getAnnotation(TextArea.class);

        //Text Area specific properties
        widgetParameters.setCols(getColsForField(widgetAnnotation));
        widgetParameters.setRows(getRowsForField(widgetAnnotation));
        widgetParameters.setResize(getResizeForField(widgetAnnotation));

        return new TextAreaWidget(widgetParameters);

    }

    public Integer getColsForField(TextArea annotation) {
        if (annotation.cols() != -1) {
            return annotation.cols();
        }

        return null;
    }

    public Integer getRowsForField(TextArea annotation) {
        if (annotation.rows() != -1) {
            return annotation.rows();
        }

        return null;
    }

    public String getResizeForField(TextArea annotation) {
        if (StringUtils.isNotBlank(annotation.resize())) {
            return annotation.resize();
        }

        return null;
    }

}
