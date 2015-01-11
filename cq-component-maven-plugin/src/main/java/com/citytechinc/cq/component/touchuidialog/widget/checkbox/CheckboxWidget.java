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
package com.citytechinc.cq.component.touchuidialog.widget.checkbox;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = CheckBox.class, makerClass = CheckboxWidgetMaker.class, resourceType = CheckboxWidget.RESOURCE_TYPE)
public class CheckboxWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/checkbox";

    private final String text;
    private final String title;
    private final boolean checked;

    public CheckboxWidget(CheckboxWidgetParameters parameters) {
        super(parameters);

        text = parameters.getText();
        title = parameters.getTitle();
        checked = parameters.isChecked();

    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return checked;
    }

}
