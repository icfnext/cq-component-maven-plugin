package com.citytechinc.cq.component.touchuidialog.widget.authorizableautocomplete;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.AuthorizableAutocomplete;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = AuthorizableAutocomplete.class, makerClass = AuthorizableAutocompleteWidgetMaker.class,
    resourceType = AuthorizableAutocompleteWidget.RESOURCE_TYPE)
public class AuthorizableAutocompleteWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/authorizable/autocomplete";

    private final String emptyText;

    private final String validation;

    private final boolean multiple;

    private final boolean forceSelection;

    private final String valueType;

    private final String selector;

    private final String serviceUserFilter;

    private final String impersonableUserFilter;

    public AuthorizableAutocompleteWidget(AuthorizableAutocompleteWidgetParameters parameters) {
        super(parameters);

        emptyText = parameters.getEmptyText();
        validation = parameters.getValidation();
        multiple = parameters.isMultiple();
        forceSelection = parameters.isForceSelection();
        valueType = parameters.getValueType();
        selector = parameters.getSelector();
        serviceUserFilter = parameters.getServiceUserFilter();
        impersonableUserFilter = parameters.getImpersonableUserFilter();
    }

    public boolean isMultiple() {
        return multiple;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public String getValidation() {
        return validation;
    }

    public boolean isForceSelection() {
        return forceSelection;
    }

    public String getValueType() {
        return valueType;
    }

    public String getSelector() {
        return selector;
    }

    public String getServiceUserFilter() {
        return serviceUserFilter;
    }

    public String getImpersonableUserFilter() {
        return impersonableUserFilter;
    }
}
