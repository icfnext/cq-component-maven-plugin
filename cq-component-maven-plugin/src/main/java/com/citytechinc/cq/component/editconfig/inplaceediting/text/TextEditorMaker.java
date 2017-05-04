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
package com.citytechinc.cq.component.editconfig.inplaceediting.text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.citytechinc.cq.component.builder.RtePluginBuilder;
import com.citytechinc.cq.component.builder.RtePluginBuilderParameters;
import com.citytechinc.cq.component.editconfig.ConfigElement;
import com.citytechinc.cq.component.editconfig.DefaultInPlaceEditorParameters;
import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.maker.AbstractInPlaceEditorMaker;
import com.citytechinc.cq.component.editconfig.maker.InPlaceEditorMakerParameters;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;
import com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.TextEditor;

public class TextEditorMaker extends AbstractInPlaceEditorMaker<DefaultInPlaceEditorParameters> {

	public TextEditorMaker(InPlaceEditorMakerParameters parameters) {
		super(parameters);
	}

	@Override
	protected InPlaceEditorElement make(DefaultInPlaceEditorParameters parameters) throws ClassNotFoundException,
		IllegalAccessException, InstantiationException {
		TextEditor textEditor = (TextEditor) this.parameters.getInPlaceEditorConfig().getInPlaceEditorAnnotation();
		parameters.setTitle(textEditor.title());
		parameters.setEditorType("text");

		// Builds config node
		DefaultXmlElementParameters configParameters = new DefaultXmlElementParameters();
		configParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		configParameters.setContainedElements(Arrays.asList(new RtePluginBuilder(new RtePluginBuilderParameters(
			textEditor)).build()));
		Map<String, Object> additionalProperties = new HashMap<String, Object>();
		additionalProperties.put("name", "./" + getName());
		additionalProperties.put("propertyName", "./" + getName());
		additionalProperties.put("textPropertyName", "./" + getName());
		configParameters.setAdditionalProperties(additionalProperties);

		ConfigElement config = new ConfigElement(configParameters);
		parameters.setConfigElement(config);

		return new com.citytechinc.cq.component.editconfig.inplaceediting.text.TextEditor(parameters);
	}
}
