package com.citytechinc.cq.component.touchuidialog.widget.tagspicker;

import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class TagsPickerWidgetMaker extends AbstractTouchUIWidgetMaker<TagsPickerWidgetParameters> {

    public TagsPickerWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(TagsPickerWidgetParameters widgetParameters) throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException, IllegalAccessException, InstantiationException {

        TagInputField widgetAnnotation = getAnnotation(TagInputField.class);

        //Tag Input Field specific parameters
        widgetParameters.setRootPath(getRootPathForField(widgetAnnotation));
        widgetParameters.setTagsPath(getTagsPathForField(widgetAnnotation));

        return new TagsPickerWidget(widgetParameters);
    }

    public String getRootPathForField(TagInputField annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.rootPath())) {
            return annotation.rootPath();
        }

        return null;
    }

    public String getTagsPathForField(TagInputField annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.tagsPath())) {
            return annotation.tagsPath();
        }

        return null;
    }
}
