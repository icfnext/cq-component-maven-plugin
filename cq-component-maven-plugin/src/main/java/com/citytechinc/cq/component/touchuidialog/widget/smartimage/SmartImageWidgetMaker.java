package com.citytechinc.cq.component.touchuidialog.widget.smartimage;

import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.html5smartimage.Html5SmartImageWidget;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

import java.util.Arrays;

public class SmartImageWidgetMaker extends AbstractTouchUIWidgetMaker<SmartImageWidgetParameters> {

    private static final String[] MIME_TYPES = { "image" };

    public SmartImageWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(SmartImageWidgetParameters widgetParameters) throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException {

        Html5SmartImage smartImageAnnotation = getAnnotation(Html5SmartImage.class);

        widgetParameters.setName(getNameAsPrefix(smartImageAnnotation) + Html5SmartImageWidget.NAME_SUFFIX);
        widgetParameters.setTitle(getTitleForField(smartImageAnnotation));
        widgetParameters.setText(getTextForField(smartImageAnnotation));
        widgetParameters.setIcon(getIconForField(smartImageAnnotation));
        widgetParameters.setMultiple(getMultipleForField(smartImageAnnotation));

        widgetParameters.setUploadUrl(getUploadUrlForField(smartImageAnnotation));
        widgetParameters.setUploadUrlBuilder(getUploadUrlBuilderForField(smartImageAnnotation));
        widgetParameters.setSizeLimit(getSizeLimitForField(smartImageAnnotation));
        widgetParameters.setAutoStart(getAutoStartForField(smartImageAnnotation));
        widgetParameters.setUseHTML5(getUseHTML5ForField(smartImageAnnotation));
        widgetParameters.setDropZone(getDropZoneForField(smartImageAnnotation));
        widgetParameters.setMimeTypes(Arrays.asList(MIME_TYPES));

        return new SmartImageWidget(widgetParameters);
    }

    public String getTitleForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
            return annotation.title();
        }

        return null;
    }

    public String getTextForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
            return annotation.text();
        }

        return null;
    }

    public String getIconForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.icon())) {
            return annotation.icon();
        }

        return null;
    }

    public boolean getMultipleForField(Html5SmartImage annotation) {
        if (annotation != null) {
            return annotation.multiple();
        }

        return false;
    }

    public String getUploadUrlForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrl())) {
            return annotation.touchUIUploadUrl();
        }

        return null;
    }

    public String getUploadUrlBuilderForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.uploadUrlBuilder())) {
            return annotation.uploadUrlBuilder();
        }

        return null;
    }

    public Long getSizeLimitForField(Html5SmartImage annotation) {
        if (annotation != null && annotation.sizeLimit() != 0) {
            return Long.valueOf(annotation.sizeLimit());
        }

        return null;
    }

    public boolean getAutoStartForField(Html5SmartImage annotation) {
        if (annotation != null) {
            return annotation.autoStart();
        }

        return false;
    }

    public boolean getUseHTML5ForField(Html5SmartImage annotation) {
        if (annotation != null) {
            return annotation.useHtml5();
        }

        return true;
    }

    public String getDropZoneForField(Html5SmartImage annotation) {
        if (annotation != null && StringUtils.isNotBlank(annotation.dropZone())) {
            return annotation.dropZone();
        }

        return null;
    }

    private String getNameAsPrefix(Html5SmartImage annotation) {
        StringBuilder sb = new StringBuilder();

        String overrideName = parameters.getDialogFieldConfig().getName();

        if (StringUtils.isNotEmpty(overrideName)) {
            sb.append(overrideName);

            if (!overrideName.endsWith("/")) {
                sb.append("/");
            }
        } else {
            if (parameters.isUseDotSlashInName()) {
                sb.append("./");
            }

            if (StringUtils.isNotEmpty(getName()) && !annotation.isSelf()) {
                sb.append(getName()).append("/");
            }
        }

        return sb.toString();
    }
}
