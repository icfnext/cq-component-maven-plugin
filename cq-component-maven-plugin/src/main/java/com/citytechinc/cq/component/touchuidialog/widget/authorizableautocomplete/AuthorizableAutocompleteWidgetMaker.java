package com.citytechinc.cq.component.touchuidialog.widget.authorizableautocomplete;

import com.citytechinc.cq.component.annotations.widgets.AuthorizableAutocomplete;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class AuthorizableAutocompleteWidgetMaker extends AbstractTouchUIWidgetMaker<AuthorizableAutocompleteWidgetParameters> {

    public AuthorizableAutocompleteWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(AuthorizableAutocompleteWidgetParameters widgetParameters)
        throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException {
        AuthorizableAutocomplete fieldAnnotation = getAnnotation(AuthorizableAutocomplete.class);

        widgetParameters.setEmptyText(fieldAnnotation.emptyText());
        widgetParameters.setValidation(fieldAnnotation.validation());
        widgetParameters.setMultiple(fieldAnnotation.multiple());
        widgetParameters.setForceSelection(fieldAnnotation.forceSelection());
        widgetParameters.setValueType(fieldAnnotation.valueType().getValue());
        widgetParameters.setSelector(fieldAnnotation.selector().getValue());
        widgetParameters.setServiceUserFilter(fieldAnnotation.serviceUserFilter().getValue());
        widgetParameters.setImpersonableUserFilter(fieldAnnotation.impersonableUserFilter().getValue());

        return new AuthorizableAutocompleteWidget(widgetParameters);
    }
}