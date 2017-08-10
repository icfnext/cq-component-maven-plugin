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
package com.citytechinc.cq.component.editconfig.inplaceediting.image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.citytechinc.cq.component.AspectRatio;
import com.citytechinc.cq.component.AspectRatioParameters;
import com.citytechinc.cq.component.AspectRatios;
import com.citytechinc.cq.component.AspectRatiosParameters;
import com.citytechinc.cq.component.editconfig.ConfigElement;
import com.citytechinc.cq.component.editconfig.DefaultInPlaceEditorParameters;
import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.maker.AbstractInPlaceEditorMaker;
import com.citytechinc.cq.component.editconfig.maker.InPlaceEditorMakerParameters;
import com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.ImageEditor;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElement;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class ImageEditorMaker extends AbstractInPlaceEditorMaker<DefaultInPlaceEditorParameters> {

	public ImageEditorMaker(InPlaceEditorMakerParameters parameters) {
		super(parameters);
	}

	@Override
	protected InPlaceEditorElement make(DefaultInPlaceEditorParameters parameters) throws ClassNotFoundException,
		IllegalAccessException, InstantiationException {
		ImageEditor imageEditor = (ImageEditor) this.parameters.getInPlaceEditorConfig().getInPlaceEditorAnnotation();
		parameters.setTitle(imageEditor.title());
		parameters.setEditorType("image");

		List<XmlElement> pluginChildren = new ArrayList<XmlElement>();
		// Builds rotate node
		DefaultXmlElementParameters rotateParameters = new DefaultXmlElementParameters();
		rotateParameters.setFieldName("rotate");
		rotateParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		rotateParameters.setAdditionalProperties(Collections.singletonMap("features", imageEditor.enableRotate() ? "*"
			: "-"));
		pluginChildren.add(new DefaultXmlElement(rotateParameters));

		// Builds crop node
		DefaultXmlElementParameters cropParameters = new DefaultXmlElementParameters();
		cropParameters.setFieldName("crop");
		cropParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		cropParameters.setAdditionalProperties(Collections.singletonMap("features", imageEditor.enableCrop() ? "*"
			: "-"));

		if (imageEditor.cropAspectRatios().length > 0) {
			List<AspectRatio> aspectRatioChildren = new ArrayList<AspectRatio>();
			for (int i = 0; i < imageEditor.cropAspectRatios().length; i++) {
				com.citytechinc.cq.component.annotations.AspectRatio aspectRatio = imageEditor.cropAspectRatios()[i];
				AspectRatioParameters arp = new AspectRatioParameters();
				arp.setName(aspectRatio.text());
				arp.setRatio((double) aspectRatio.height() / aspectRatio.width());
				arp.setFieldName(Integer.toString(i));
				aspectRatioChildren.add(new AspectRatio(arp));
			}

			AspectRatiosParameters aspectRatiosParameters = new AspectRatiosParameters();
			aspectRatiosParameters.setContainedElements(aspectRatioChildren);
			cropParameters.setContainedElements(Arrays.asList(new AspectRatios(aspectRatiosParameters)));
		}

		pluginChildren.add(new DefaultXmlElement(cropParameters));

		// Builds map node
		DefaultXmlElementParameters mapParameters = new DefaultXmlElementParameters();
		mapParameters.setFieldName("map");
		mapParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		mapParameters
			.setAdditionalProperties(Collections.singletonMap("features", imageEditor.enableMap() ? "*" : "-"));
		pluginChildren.add(new DefaultXmlElement(mapParameters));

		// Builds plugin node
		DefaultXmlElementParameters pluginsParameters = new DefaultXmlElementParameters();
		pluginsParameters.setFieldName("plugins");
		pluginsParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		pluginsParameters.setContainedElements(pluginChildren);
		DefaultXmlElement plugins = new DefaultXmlElement(pluginsParameters);

		// Builds config node
		DefaultXmlElementParameters configParameters = new DefaultXmlElementParameters();
		configParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
		configParameters.setContainedElements(Arrays.asList(plugins));
		ConfigElement config = new ConfigElement(configParameters);
		parameters.setConfigElement(config);

		return new com.citytechinc.cq.component.editconfig.inplaceediting.image.ImageEditor(parameters);
	}
}
