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

import com.citytechinc.cq.component.annotations.config.InPlaceEditor;
import com.citytechinc.cq.component.editconfig.AbstractInPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.DefaultInPlaceEditorParameters;

@InPlaceEditor(annotationClass = com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.ImageEditor.class,
	editorType = "image", makerClass = ImageEditorMaker.class)
public class ImageEditor extends AbstractInPlaceEditorElement {

	public ImageEditor(DefaultInPlaceEditorParameters parameters) {
		super(parameters);
	}

}
