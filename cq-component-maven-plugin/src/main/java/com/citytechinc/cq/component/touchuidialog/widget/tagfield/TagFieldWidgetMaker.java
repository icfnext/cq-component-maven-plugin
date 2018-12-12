package com.citytechinc.cq.component.touchuidialog.widget.tagfield;

import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class TagFieldWidgetMaker extends AbstractTouchUIWidgetMaker<TagFieldWidgetParameters> {

    public TagFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(TagFieldWidgetParameters widgetParameters)
        throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException,
        IllegalAccessException, InstantiationException {
        TagInputField widgetAnnotation = getAnnotation(TagInputField.class);

        widgetParameters.setRootPath(getRootPathForField(widgetAnnotation));
        widgetParameters.setMultiple(widgetAnnotation.multiple());

        return new TagFieldWidget(widgetParameters);
    }

    public String getRootPathForField(TagInputField annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.rootPath())) {
            return annotation.rootPath();
        }

        return null;
    }
}
