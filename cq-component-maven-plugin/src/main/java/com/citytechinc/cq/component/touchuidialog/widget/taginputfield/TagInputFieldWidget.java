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
package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidget;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetParameters;

@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class, resourceType = TagInputFieldWidget.RESOURCE_TYPE)
public class TagInputFieldWidget extends AutoCompleteWidget {

    public static final String DATA_SOURCE_RESOURCE_TYPE = "cq/gui/components/common/datasources/tags";
    public static final String VALUES_RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete/tags";
    public static final String OPTIONS_RESOURCE_TYPE = "granite/ui/components/foundation/form/autocomplete/list";

    public TagInputFieldWidget(AutoCompleteWidgetParameters parameters) {
        super(parameters);
    }

}
