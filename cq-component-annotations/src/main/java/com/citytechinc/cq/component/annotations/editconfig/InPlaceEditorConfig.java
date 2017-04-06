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
package com.citytechinc.cq.component.annotations.editconfig;

import javassist.CtMember;

public class InPlaceEditorConfig {
	private Object inPlaceEditorAnnotation;
	private CtMember member;
	private Class<?> annotationClass;

	public InPlaceEditorConfig(Object inPlaceEditorAnnotation, CtMember member, Class<?> annotationClass) {
		this.inPlaceEditorAnnotation = inPlaceEditorAnnotation;
		this.member = member;
		this.annotationClass = annotationClass;
	}

	public Object getInPlaceEditorAnnotation() {
		return inPlaceEditorAnnotation;
	}

	public void setInPlaceEditorAnnotation(Object inPlaceEditorAnnotation) {
		this.inPlaceEditorAnnotation = inPlaceEditorAnnotation;
	}

	public CtMember getMember() {
		return member;
	}

	public void setMember(CtMember member) {
		this.member = member;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}

	public void setAnnotationClass(Class<?> annotationClass) {
		this.annotationClass = annotationClass;
	}
}
