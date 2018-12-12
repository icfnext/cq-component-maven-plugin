package com.citytechinc.cq.component.touchuidialog.widget.pathfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = PathField.class, makerClass = PathFieldWidgetMaker.class,
    resourceType = PathFieldCoral3Widget.RESOURCE_TYPE)
public class PathFieldCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/pathfield";

    private final String rootPath;

    private final boolean forceSelection;

    private final String filter;


    public PathFieldCoral3Widget(PathFieldWidgetParameters parameters) {
        super(parameters);

        rootPath = parameters.getRootPath();
        forceSelection = parameters.isForceSelection();
        filter = parameters.getFilter();
    }

    public String getRootPath() {
        return rootPath;
    }

    public boolean isForceSelection() {
        return forceSelection;
    }

    public String getFilter() {
        return filter;
    }
}
