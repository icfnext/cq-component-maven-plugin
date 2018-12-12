package com.citytechinc.cq.component.touchuidialog.widget.tagspicker;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

/**
 * Touch UI Tags Picker Widget
 *
 * This widget is rendered in response to the @TagInputField annotation when the
 * tagspickerwidget feature flag is enabled.
 */
@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagsPickerWidgetMaker.class,
        resourceType = TagsPickerWidget.RESOURCE_TYPE, featureFlag = TagsPickerWidget.FEATURE_FLAG)
public class TagsPickerWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "cq/gui/components/common/tagspicker";
    public static final String FEATURE_FLAG = "tagspickerwidget";

    private String rootPath;
    private String tagsPath;

    public TagsPickerWidget(TagsPickerWidgetParameters parameters) {
        super(parameters);

        this.rootPath = parameters.getRootPath();
        this.tagsPath = parameters.getTagsPath();
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getTagsPath() {
        return tagsPath;
    }

}
