package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.annotations.widgets.rte.UISettings;
import com.citytechinc.cq.component.builder.RtePluginBuilder;
import com.citytechinc.cq.component.builder.RtePluginBuilderParameters;
import com.citytechinc.cq.component.builder.RteUISettingsBuilder;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class RichTextEditorWidgetMaker extends AbstractTouchUIWidgetMaker<RichTextEditorWidgetParameters> {

    public RichTextEditorWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(RichTextEditorWidgetParameters parameters) throws ClassNotFoundException,
        InvalidComponentFieldException, TouchUIDialogGenerationException, IllegalAccessException,
        InstantiationException {
        List<XmlElement> children = new ArrayList<XmlElement>();
        final RichTextEditor rteAnnotation = getAnnotation(RichTextEditor.class);

        children.add(new RtePluginBuilder(new RtePluginBuilderParameters(rteAnnotation)).build());

        if (rteAnnotation.uiSettings().length > 0) {
            UISettings uiSettings = rteAnnotation.uiSettings()[0];
            children.add(new RteUISettingsBuilder(uiSettings).build());
        }

        parameters.setContainedElements(children);
        parameters.setUseFixedInlineToolbar(rteAnnotation.useFixedInlineToolbar());

        return new RichTextEditorWidget(parameters);
    }
}
