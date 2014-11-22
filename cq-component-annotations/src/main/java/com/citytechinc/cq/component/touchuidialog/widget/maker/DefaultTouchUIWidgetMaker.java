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
package com.citytechinc.cq.component.touchuidialog.widget.maker;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.SimpleTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.TouchUIWidgetParameters;

public class DefaultTouchUIWidgetMaker extends AbstractTouchUIWidgetMaker {

    public DefaultTouchUIWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make() {

        TouchUIWidgetParameters widgetParameters = new TouchUIWidgetParameters();

        widgetParameters.setFieldName(getFieldNameForField());
        widgetParameters.setName(getNameForField());
        widgetParameters.setFieldLabel(getFieldLabelForField());
        widgetParameters.setResourceType(parameters.getResourceType());

        return new SimpleTouchUIWidget(widgetParameters);

    }

}
