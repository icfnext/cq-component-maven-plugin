package com.citytechinc.cq.component.dialog;

import java.util.Map;

import com.citytechinc.cq.component.xml.XmlElementParameters;

public class DialogElementParameters extends XmlElementParameters {
	protected String primaryType;
	protected String nameSpace;
	protected String fieldName;
	protected Map<String, String> additionalProperties;
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
