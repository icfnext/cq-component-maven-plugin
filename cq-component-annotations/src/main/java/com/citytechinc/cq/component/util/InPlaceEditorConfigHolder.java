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
package com.citytechinc.cq.component.util;

import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.maker.InPlaceEditorMaker;

public class InPlaceEditorConfigHolder {

	private final Class<?> annotationClass;
	private final Class<? extends InPlaceEditorElement> inPlaceEditorClass;
	private final Class<? extends InPlaceEditorMaker> makerClass;
	private final String editorType;

	public InPlaceEditorConfigHolder(Class<?> annotationClass,
		Class<? extends InPlaceEditorElement> inPlaceEditorClass, Class<? extends InPlaceEditorMaker> makerClass,
		String editorType) {
		this.annotationClass = annotationClass;
		this.inPlaceEditorClass = inPlaceEditorClass;
		this.makerClass = makerClass;
		this.editorType = editorType;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}

	public Class<? extends InPlaceEditorElement> getInPlaceEditorClass() {
		return inPlaceEditorClass;
	}

	public Class<? extends InPlaceEditorMaker> getMakerClass() {
		return makerClass;
	}

	public String getEditorType() {
		return editorType;
	}
}
