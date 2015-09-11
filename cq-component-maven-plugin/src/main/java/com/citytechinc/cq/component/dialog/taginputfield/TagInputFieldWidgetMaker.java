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
package com.citytechinc.cq.component.dialog.taginputfield;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;

public class TagInputFieldWidgetMaker extends AbstractWidgetMaker<TagInputFieldWidgetParameters> {

	public TagInputFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(TagInputFieldWidgetParameters parameters) throws ClassNotFoundException {

		TagInputField tagAnnotation = getAnnotation(TagInputField.class);

		parameters.setContainedElements(getWidgetCollectionHolderForField(tagAnnotation));

		parameters.setDisplayTitles(tagAnnotation.displayTitles());

		return new TagInputFieldWidget(parameters);

	}

	private List<DialogElement> getWidgetCollectionHolderForField(TagInputField tagAnnotation) {

		if (tagAnnotation.namespaces().length == 0) {
			return null;
		}

		List<DialogElement> namespaces = new ArrayList<DialogElement>();
		for (int i = 0; i < tagAnnotation.namespaces().length; i++) {
			TagNameSpace ns = tagAnnotation.namespaces()[i];

			String max = null;
			if (ns.maximum() > -1) {
				max = Integer.toString(ns.maximum());
			}
			NamespaceParameters namespaceParameters = new NamespaceParameters();
			namespaceParameters.setFieldName("namespace" + i);
			namespaceParameters.setName(ns.value());
			namespaceParameters.setMaximum(max);
			Namespace n = new Namespace(namespaceParameters);
			namespaces.add(n);
		}

		WidgetCollectionParameters wcp = new WidgetCollectionParameters();
		wcp.setFieldName("namespaces");
		wcp.setContainedElements(namespaces);
		return Arrays.asList(new DialogElement[] { new WidgetCollection(wcp) });

	}

}
