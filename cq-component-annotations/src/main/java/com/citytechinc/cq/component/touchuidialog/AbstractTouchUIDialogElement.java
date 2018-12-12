package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public abstract class AbstractTouchUIDialogElement extends AbstractXmlElement implements TouchUIDialogElement {

	private NameSpacedAttribute<String> resourceType;
	private double ranking;

	public AbstractTouchUIDialogElement(TouchUIDialogElementParameters parameters) {
		super(parameters);

		resourceType =
			new NameSpacedAttribute<String>(Constants.SLING_NS_URI, Constants.SLING_NS_PREFIX, "resourceType",
				parameters.getResourceType());
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public NameSpacedAttribute<String> getSlingResourceType() {
		return resourceType;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
