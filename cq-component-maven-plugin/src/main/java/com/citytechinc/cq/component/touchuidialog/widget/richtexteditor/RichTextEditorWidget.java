package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = RichTextEditor.class, makerClass = RichTextEditorWidgetMaker.class,
    resourceType = RichTextEditorWidget.RESOURCE_TYPE, featureFlag = RichTextEditorWidget.FEATURE_FLAG)
public class RichTextEditorWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "cq/gui/components/authoring/dialog/richtext";

    public static final String FEATURE_FLAG = "rte-touchui";

    private final boolean useFixedInlineToolbar;

    public RichTextEditorWidget(RichTextEditorWidgetParameters parameters) {
        super(parameters);

        useFixedInlineToolbar = parameters.isUseFixedInlineToolbar();
    }

    public boolean isUseFixedInlineToolbar() {
        return useFixedInlineToolbar;
    }

}
