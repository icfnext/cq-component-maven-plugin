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
