/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.builder.RtePluginBuilder;
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
        final RtePlugins plugins = new RtePluginBuilder(rteAnnotation).build();

        widgetParameters.setContainedElements(Collections.singletonList(plugins));

        return new RichTextEditorWidget(widgetParameters);
    }
}
