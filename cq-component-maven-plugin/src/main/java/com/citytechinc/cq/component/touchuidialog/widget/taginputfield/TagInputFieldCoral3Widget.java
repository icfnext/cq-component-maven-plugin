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
package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteCoral3Widget;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetParameters;

@TouchUIWidget(annotationClass = TagInputField.class, makerClass = TagInputFieldWidgetMaker.class,
	resourceType = TagInputFieldCoral3Widget.RESOURCE_TYPE)
public class TagInputFieldCoral3Widget extends AutoCompleteCoral3Widget {

	public static final String VALUES_RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/autocomplete/tags";
	public static final String OPTIONS_RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/autocomplete/list";

	public TagInputFieldCoral3Widget(AutoCompleteWidgetParameters parameters) {
		super(parameters);
	}

}
