package com.citytechinc.cq.component.annotations.widgets.authorizable;

public enum AuthorizableValueType {
    ID("id"),
    PATH("path"),
    PRINCIPAL_NAME("principalname");

    private String value;

    AuthorizableValueType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
