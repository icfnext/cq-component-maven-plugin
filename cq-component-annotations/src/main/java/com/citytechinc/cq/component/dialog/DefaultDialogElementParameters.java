package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class DefaultDialogElementParameters extends DefaultXmlElementParameters implements DialogElementParameters {
	protected double ranking;
	protected Listeners listeners;

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public Listeners getListeners() {
		return listeners;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}
}
