package com.citytechinc.cq.component.touchuidialog.widget.radiogroup;

import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class RadioGroupWidgetMaker extends AbstractTouchUIWidgetMaker<RadioGroupWidgetParameters> {

    public RadioGroupWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(RadioGroupWidgetParameters widgetParameters) throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException {
        Selection selectionField = getAnnotation(Selection.class);

        widgetParameters.setText(getFieldLabelForField());
        widgetParameters.setDataSource(getDataSourceForField(selectionField));
        widgetParameters.setOptions(TouchUIDialogUtil.getOptionsForSelection(selectionField, widgetParameters,
            getType(), parameters.getClassLoader(), parameters.getClassPool()));
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new RadioGroupCoral3Widget(widgetParameters);
        }

        return new RadioGroupWidget(widgetParameters);
    }

    public DataSource getDataSourceForField(Selection annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.dataSource())) {
            DataSourceParameters dataSourceParameters = new DataSourceParameters();
            dataSourceParameters.setResourceType(annotation.dataSource());
            return new DataSource(dataSourceParameters);
        }

        return null;
    }
}
