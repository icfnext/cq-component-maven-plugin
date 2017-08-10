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
package com.citytechinc.cq.component.editconfig.maker;

import javassist.CtMember;

import com.citytechinc.cq.component.annotations.editconfig.InPlaceEditorConfig;

public class InPlaceEditorMakerParameters {
	private InPlaceEditorConfig inPlaceEditorConfig;
	private boolean setActive;

	public InPlaceEditorConfig getInPlaceEditorConfig() {
		return inPlaceEditorConfig;
	}

	public void setInPlaceEditorConfig(InPlaceEditorConfig inPlaceEditorConfig) {
		this.inPlaceEditorConfig = inPlaceEditorConfig;
	}

	public boolean isSetActive() {
		return setActive;
	}

	public void setSetActive(boolean setActive) {
		this.setActive = setActive;
	}

	public CtMember getCtMember() {
		return inPlaceEditorConfig.getMember();
	}
}
