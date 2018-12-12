package com.citytechinc.cq.component.editconfig.inplaceediting.text;

import com.citytechinc.cq.component.annotations.config.InPlaceEditor;
import com.citytechinc.cq.component.editconfig.AbstractInPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.DefaultInPlaceEditorParameters;

@InPlaceEditor(annotationClass = com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.TextEditor.class,
	editorType = "image", makerClass = TextEditorMaker.class)
public class TextEditor extends AbstractInPlaceEditorElement {

	public TextEditor(DefaultInPlaceEditorParameters parameters) {
		super(parameters);
	}

}
