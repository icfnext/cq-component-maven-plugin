package com.citytechinc.cq.component;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class AspectRatioParameters extends DefaultXmlElementParameters {
	private static final String PRIMARY_TYPE = "nt:unstructured";
	private Integer height;
	private Integer width;
	private Double ratio;
	private String text;
	private String name;

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPrimaryType() {
		return PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for AspectRatio");
	}
}
