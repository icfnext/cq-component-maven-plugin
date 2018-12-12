package com.citytechinc.cq.component.touchuidialog.layout.maker;

import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;

public interface LayoutMaker {

	public Layout make() throws LayoutMakerException;

}
