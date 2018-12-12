package com.citytechinc.cq.component.dialog.selection;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Selection.class, makerClass = SelectionWidgetMaker.class, xtype = SelectionWidget.XTYPE)
public class SelectionWidget extends AbstractWidget {

    public static final String XTYPE = "selection";

    private final String type;

    private final String options;

    private final String optionsProvider;

    private final String sortDir;

    public SelectionWidget(SelectionWidgetParameters parameters) {
        super(parameters);

        this.type = parameters.getType();
        this.options = parameters.getOptions();
        this.optionsProvider = parameters.getOptionsProvider();
        this.sortDir = parameters.getSortDir();
    }

    public String getType() {
        return type;
    }

    public String getOptions() {
        return options;
    }

    public String getOptionsProvider() {
        return optionsProvider;
    }

    public String getSortDir() {
        return sortDir;
    }
}
