package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.builder.RtePluginBuilder;
import com.citytechinc.cq.component.builder.RtePluginBuilderParameters;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

import java.util.Collections;

/**
 * Based on http://dev.day.com/docs/en/cq/current/administering/
 * configuring_rich_text_editor.html
 */
public class RichTextEditorMaker extends AbstractWidgetMaker<RichTextEditorWidgetParameters> {

    public RichTextEditorMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(RichTextEditorWidgetParameters widgetParameters) throws ClassNotFoundException {
        final RichTextEditor rteAnnotation = getAnnotation(RichTextEditor.class);
        final RtePlugins plugins = new RtePluginBuilder(new RtePluginBuilderParameters(rteAnnotation)).build();

        widgetParameters.setContainedElements(Collections.singletonList(plugins));

        return new RichTextEditorWidget(widgetParameters);
    }
}
