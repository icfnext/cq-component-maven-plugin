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
package com.citytechinc.cq.component.touchuidialog.widget.hidden;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

@TouchUIWidget(annotationClass = Hidden.class, makerClass = HiddenWidgetMaker.class,
    resourceType = HiddenWidget.RESOURCE_TYPE, featureFlag = HiddenWidget.FEATURE_FLAG)
public class HiddenWidget extends AbstractTouchUIWidget {
    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/hidden";
    public static final String FEATURE_FLAG = "hiddenfieldwidget";

    private final String value;

    public HiddenWidget(DefaultTouchUIWidgetParameters parameters) {
        super(parameters);
        this.value = parameters.getValue();
    }

    public String getValue() {
        return value;
    }
}
