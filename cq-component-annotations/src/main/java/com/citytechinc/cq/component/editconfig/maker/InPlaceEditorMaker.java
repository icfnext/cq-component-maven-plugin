package com.citytechinc.cq.component.editconfig.maker;

import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;

public interface InPlaceEditorMaker {
	public InPlaceEditorElement make() throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
