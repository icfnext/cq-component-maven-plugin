package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.options.AutoCompleteOptions;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.values.AutoCompleteValues;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource.TagsDataSource;
import com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource.TagsDataSourceParameters;

import java.util.ArrayList;
import java.util.List;

public class TagInputFieldWidgetMaker extends AutoCompleteWidgetMaker {

    private TagInputField fieldAnnotation;

    public TagInputFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
        try {
            fieldAnnotation = getAnnotation(TagInputField.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected DataSource makeDataSource() {
        TagsDataSourceParameters dataSourceParameters = new TagsDataSourceParameters();

        dataSourceParameters.setResourceType(TagsDataSource.RESOURCE_TYPE);

        List<String> namespaces = new ArrayList<String>();

        for (TagNameSpace currentNamespace : fieldAnnotation.namespaces()) {
            namespaces.add(currentNamespace.value());
        }

        dataSourceParameters.setNamespaces(namespaces);

        return new TagsDataSource(dataSourceParameters);
    }

    @Override
    protected AutoCompleteOptions makeOptions() {
        TouchUIDialogElementParameters optionsParameters = new DefaultTouchUIDialogElementParameters();

        if (TouchUIDialogType.CORAL3.isOfType(parameters.getTouchUIDialogType())) {
            optionsParameters.setResourceType(TagInputFieldCoral3Widget.OPTIONS_RESOURCE_TYPE);
        } else {
            optionsParameters.setResourceType(TagInputFieldWidget.OPTIONS_RESOURCE_TYPE);
        }
        optionsParameters.setFieldName(TagInputFieldWidget.OPTIONS_FIELD_NAME);
        optionsParameters.setPrimaryType("nt:unstructured");

        return new AutoCompleteOptions(optionsParameters);
    }

    @Override
    protected AutoCompleteValues makeValues() {
        TouchUIDialogElementParameters valuesParameters = new DefaultTouchUIDialogElementParameters();

        if (TouchUIDialogType.CORAL3.isOfType(parameters.getTouchUIDialogType())) {
            valuesParameters.setResourceType(TagInputFieldCoral3Widget.VALUES_RESOURCE_TYPE);
        } else {
            valuesParameters.setResourceType(TagInputFieldWidget.VALUES_RESOURCE_TYPE);
        }
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
        // TODO: Determine whether this should be dynamic at all
        return "contains";
    }
}
