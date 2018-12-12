package com.citytechinc.cq.component.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.XmlElement;

public abstract class AbstractDialogElement extends AbstractXmlElement implements DialogElement {
	private double ranking;

	public AbstractDialogElement(DefaultDialogElementParameters parameters) {
		super(parameters);
		if (containedElements != null) {
			Collections.sort(containedElements, new DialogElementComparator());
		}
		if (parameters.getListeners() != null) {
			List<XmlElement> newElements = new ArrayList<XmlElement>();
			if (containedElements != null) {
				newElements.addAll(containedElements);
			}
			newElements.add(parameters.getListeners());
			containedElements = newElements;
		}
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public double getRanking() {
		return ranking;
	}
}
