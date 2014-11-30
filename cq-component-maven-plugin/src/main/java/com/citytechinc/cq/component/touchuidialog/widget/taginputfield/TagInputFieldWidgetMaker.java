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
package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.datasource.AutoCompleteDataSource;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.options.AutoCompleteOptions;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.values.AutoCompleteValues;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class TagInputFieldWidgetMaker extends AutoCompleteWidgetMaker {

    private TagInputField fieldAnnotation;

    public TagInputFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException {
        fieldAnnotation = getAnnotation(TagInputField.class);
        return super.make();
    }

    @Override
    protected AutoCompleteDataSource makeDataSource() {
        TouchUIDialogElementParameters dataSourceParameters = new TouchUIDialogElementParameters();

        dataSourceParameters.setResourceType(TagInputFieldWidget.DATA_SOURCE_RESOURCE_TYPE);
        dataSourceParameters.setFieldName(TagInputFieldWidget.DATA_SOURCE_FIELD_NAME);
        dataSourceParameters.setPrimaryType("nt:unstructured");

        //TODO: Namespace restrictions go in here somewhere

        return new AutoCompleteDataSource(dataSourceParameters);
    }

    @Override
    protected AutoCompleteOptions makeOptions() {
        TouchUIDialogElementParameters optionsParameters = new TouchUIDialogElementParameters();

        optionsParameters.setResourceType(TagInputFieldWidget.OPTIONS_RESOURCE_TYPE);
        optionsParameters.setFieldName(TagInputFieldWidget.OPTIONS_FIELD_NAME);
        optionsParameters.setPrimaryType("nt:unstructured");

        return new AutoCompleteOptions(optionsParameters);
    }

    @Override
    protected AutoCompleteValues makeValues() {
        TouchUIDialogElementParameters valuesParameters = new TouchUIDialogElementParameters();

        valuesParameters.setResourceType(TagInputFieldWidget.VALUES_RESOURCE_TYPE);
        valuesParameters.setFieldName(TagInputFieldWidget.VALUES_FIELD_NAME);
        valuesParameters.setPrimaryType("nt:unstructured");

        return new AutoCompleteValues(valuesParameters);
    }


    @Override
    protected boolean getMultipleForField() {
        return fieldAnnotation.multiple();
    }

    @Override
    protected String getModeForField() {
        //TODO: Determine whether this should be dynamic at all
        return "contains";
    }
}
