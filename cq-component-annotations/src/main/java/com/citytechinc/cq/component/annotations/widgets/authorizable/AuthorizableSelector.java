package com.citytechinc.cq.component.annotations.widgets.authorizable;

public enum AuthorizableSelector {
    ALL("all"),
    USER("user"),
    GROUP("group");

    private String value;

    AuthorizableSelector(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
