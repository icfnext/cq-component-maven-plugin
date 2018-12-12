package com.citytechinc.cq.component.touchuidialog.container;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public class Container extends AbstractTouchUIDialogElement {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/container";
	public static final String RESOURCE_TYPE_CORAL3 = "granite/ui/components/coral/foundation/container";
	public static final String PRIMARY_TYPE = "nt:unstructured";

	public Container(ContainerParameters parameters) {
		super(parameters);
	}

}
