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
