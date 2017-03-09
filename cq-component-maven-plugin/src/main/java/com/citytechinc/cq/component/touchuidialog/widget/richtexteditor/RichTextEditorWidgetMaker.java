/**
 *    Copyright 2013 CITYTECH, Inc.
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
package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.builder.RtePluginBuilder;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.richtexteditor.RtePlugins;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

import java.util.Collections;

public class RichTextEditorWidgetMaker extends AbstractTouchUIWidgetMaker<RichTextEditorWidgetParameters> {

    public RichTextEditorWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(RichTextEditorWidgetParameters parameters)
        throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException,
        IllegalAccessException, InstantiationException {
        final RichTextEditor rteAnnotation = getAnnotation(RichTextEditor.class);
        final RtePlugins plugins = new RtePluginBuilder(rteAnnotation).build();

        parameters.setContainedElements(Collections.singletonList(plugins));

        return new RichTextEditorWidget(parameters);
    }
}
