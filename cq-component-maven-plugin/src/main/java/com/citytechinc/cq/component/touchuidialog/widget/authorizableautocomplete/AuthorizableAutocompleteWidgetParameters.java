package com.citytechinc.cq.component.touchuidialog.widget.authorizableautocomplete;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class AuthorizableAutocompleteWidgetParameters extends DefaultTouchUIWidgetParameters {

    private String emptyText;

    private String validation;

    private boolean multiple;

    private boolean forceSelection;

    private String valueType;

    private String selector;

    private String serviceUserFilter;

    private String impersonableUserFilter;

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(final String emptyText) {
        this.emptyText = emptyText;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(final String validation) {
        this.validation = validation;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(final String valueType) {
        this.valueType = valueType;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(final String selector) {
        this.selector = selector;
    }

    public String getServiceUserFilter() {
        return serviceUserFilter;
    }

    public void setServiceUserFilter(final String serviceUserFilter) {
        this.serviceUserFilter = serviceUserFilter;
    }

    public String getImpersonableUserFilter() {
        return impersonableUserFilter;
    }

    public void setImpersonableUserFilter(final String impersonableUserFilter) {
        this.impersonableUserFilter = impersonableUserFilter;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isForceSelection() {
        return forceSelection;
    }

    public void setForceSelection(boolean forceSelection) {
        this.forceSelection = forceSelection;
    }

    @Override
    public String getResourceType() {
        return AuthorizableAutocompleteWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException();
    }
}