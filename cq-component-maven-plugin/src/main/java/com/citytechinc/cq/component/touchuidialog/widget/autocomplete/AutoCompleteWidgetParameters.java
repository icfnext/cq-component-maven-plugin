package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteWidgetParameters extends DefaultTouchUIWidgetParameters {

    private boolean multiple;

    private String mode;

    private TouchUIDialogElement datasource;

    private TouchUIDialogElement values;

    private TouchUIDialogElement options;

    private boolean forceSelection;

    // TODO: Look into the translateOptions mechanism

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public TouchUIDialogElement getDatasource() {
        return datasource;
    }

    public void setDatasource(TouchUIDialogElement datasource) {
        this.datasource = datasource;
    }

    public TouchUIDialogElement getValues() {
        return values;
    }

    public void setValues(TouchUIDialogElement values) {
        this.values = values;
    }

    public TouchUIDialogElement getOptions() {
        return options;
    }

    public void setOptions(TouchUIDialogElement options) {
        this.options = options;
    }

    public boolean isForceSelection() {
        return forceSelection;
    }

    public void setForceSelection(boolean forceSelection) {
        this.forceSelection = forceSelection;
    }

    @Override
    public List<? extends XmlElement> getContainedElements() {
        List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

        if (getDatasource() != null) {
            allContainedElements.add(getDatasource());
        }
        if (getOptions() != null) {
            allContainedElements.add(getOptions());
        }
        if (getValues() != null) {
            allContainedElements.add(getValues());
        }

        if (containedElements != null) {
            allContainedElements.addAll(containedElements);
        }

        return allContainedElements;
    }

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return AutoCompleteCoral3Widget.RESOURCE_TYPE;
        }
        return AutoCompleteWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for AutoCompleteWidget");
    }
}