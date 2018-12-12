package com.citytechinc.cq.component.editconfig.maker;

import com.citytechinc.cq.component.annotations.editconfig.InPlaceEditorConfig;
import javassist.CtMember;

public class InPlaceEditorMakerParameters {

    private InPlaceEditorConfig inPlaceEditorConfig;

    private boolean setActive;

    public InPlaceEditorConfig getInPlaceEditorConfig() {
        return inPlaceEditorConfig;
    }

    public void setInPlaceEditorConfig(InPlaceEditorConfig inPlaceEditorConfig) {
        this.inPlaceEditorConfig = inPlaceEditorConfig;
    }

    public boolean isSetActive() {
        return setActive;
    }

    public void setSetActive(boolean setActive) {
        this.setActive = setActive;
    }

    public CtMember getCtMember() {
        return inPlaceEditorConfig.getMember();
    }
}
