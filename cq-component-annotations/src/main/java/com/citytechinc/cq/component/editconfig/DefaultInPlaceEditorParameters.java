package com.citytechinc.cq.component.editconfig;

public class DefaultInPlaceEditorParameters extends InPlaceEditorParameters {

    private Boolean active;

    private String editorType;

    private String title;

    private ConfigElement configElement;

    @Override
    public Boolean isActive() {
        return active;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String getEditorType() {
        return editorType;
    }

    @Override
    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ConfigElement getConfigElement() {
        return configElement;
    }

    @Override
    public void setConfigElement(ConfigElement configElement) {
        this.configElement = configElement;
    }
}
