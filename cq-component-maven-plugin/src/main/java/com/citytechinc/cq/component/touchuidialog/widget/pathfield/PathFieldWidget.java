package com.citytechinc.cq.component.touchuidialog.widget.pathfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = PathField.class, makerClass = PathFieldWidgetMaker.class,
    resourceType = PathFieldWidget.RESOURCE_TYPE)
public class PathFieldWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/pathbrowser";

    private final String rootPath;

    private final String optionLoader;

    private final String optionLoaderRoot;

    private final String optionValueReader;

    private final String optionTitleReader;

    public PathFieldWidget(PathFieldWidgetParameters parameters) {
        super(parameters);

        rootPath = parameters.getRootPath();
        optionLoader = parameters.getOptionLoader();
        optionLoaderRoot = parameters.getOptionLoaderRoot();
        optionValueReader = parameters.getOptionValueReader();
        optionTitleReader = parameters.getOptionTitleReader();

    }

    public String getRootPath() {
        return rootPath;
    }

    public String getOptionLoader() {
        return optionLoader;
    }

    public String getOptionLoaderRoot() {
        return optionLoaderRoot;
    }

    public String getOptionValueReader() {
        return optionValueReader;
    }

    public String getOptionTitleReader() {
        return optionTitleReader;
    }

}
