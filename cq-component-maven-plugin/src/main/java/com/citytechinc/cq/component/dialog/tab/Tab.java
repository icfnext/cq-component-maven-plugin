package com.citytechinc.cq.component.dialog.tab;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Tab extends AbstractDialogElement {

    private final String title;

    public Tab(TabParameters parameters) {
        super(parameters);
        this.title = parameters.getTitle();
    }

    public String getTitle() {
        return title;
    }
}
