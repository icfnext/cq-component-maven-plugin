package com.citytechinc.cq.component.annotations.widgets.authorizable;

public enum AuthorizableUserFilter {
    OFF("off"),
    INCLUDE_ONLY("includeonly"),
    EXCLUDE("exclude");

    private String value;

    AuthorizableUserFilter(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
