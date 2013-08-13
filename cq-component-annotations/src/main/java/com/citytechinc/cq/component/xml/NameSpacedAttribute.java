package com.citytechinc.cq.component.xml;

public class NameSpacedAttribute<T> {
	private final String nameSpace;
	private final String nameSpacePrefix;
	private final T value;

	public NameSpacedAttribute(String nameSpace, String nameSpacePrefix, T value) {
		this.nameSpace = nameSpace;
		this.nameSpacePrefix = nameSpacePrefix;
		this.value = value;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public String getNameSpacePrefix() {
		return nameSpacePrefix;
	}

	public T getValue() {
		return value;
	}
}
