package com.citytechinc.cq.component.touchuidialog.widget.numberfield;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class NumberFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

	private Double min;
	private Double max;
	private Double step;

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getStep() {
		return step;
	}

	public void setStep(Double step) {
		this.step = step;
	}

	@Override
	public String getResourceType() {
		return NumberFieldWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for NumberFieldWidget");
	}

}
