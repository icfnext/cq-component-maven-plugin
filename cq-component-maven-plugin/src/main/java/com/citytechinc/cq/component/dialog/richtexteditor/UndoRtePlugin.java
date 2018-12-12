package com.citytechinc.cq.component.dialog.richtexteditor;

public class UndoRtePlugin extends RtePlugin {

    public static final String UNDO = "undo";

    private final int maxUndoSteps;

    public UndoRtePlugin(UndoRtePluginParameters parameters) {

        super(parameters);

        this.maxUndoSteps = parameters.getMaxUndoSteps();

    }

    public int getMaxUndoSteps() {
        return maxUndoSteps;
    }
}
