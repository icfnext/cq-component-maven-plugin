package com.citytechinc.cq.component.touchuidialog.layout.maker;

public abstract class AbstractLayoutMaker implements LayoutMaker {

	protected final LayoutMakerParameters parameters;

	protected AbstractLayoutMaker(LayoutMakerParameters parameters) {
		this.parameters = parameters;
	}

}
