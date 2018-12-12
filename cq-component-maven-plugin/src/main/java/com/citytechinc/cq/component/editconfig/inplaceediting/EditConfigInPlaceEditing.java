package com.citytechinc.cq.component.editconfig.inplaceediting;

import com.citytechinc.cq.component.editconfig.AbstractInPlaceEditorElement;

public class EditConfigInPlaceEditing extends AbstractInPlaceEditorElement {

    private final String configPath;

    public EditConfigInPlaceEditing(EditConfigInPlaceEditingParameters parameters) {
        super(parameters);
        configPath = parameters.getConfigPath();
    }

    public String getConfigPath() {
        return configPath;
    }
}
