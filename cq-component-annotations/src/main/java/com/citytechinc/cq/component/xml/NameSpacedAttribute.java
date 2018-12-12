package com.citytechinc.cq.component.xml;

public class NameSpacedAttribute<T> {
	private final String nameSpace;
	private final String nameSpacePrefix;
	private final String name;
	private final T value;

	public NameSpacedAttribute(String nameSpace, String nameSpacePrefix, T value) {
		this.nameSpace = nameSpace;
		this.nameSpacePrefix = nameSpacePrefix;
		this.name = null;
		this.value = value;
	}

	public NameSpacedAttribute(String nameSpace, String nameSpacePrefix, String name, T value) {
		this.nameSpace = nameSpace;
		this.nameSpacePrefix = nameSpacePrefix;
		this.name = name;
		this.value = value;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public String getNameSpacePrefix() {
		return nameSpacePrefix;
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}
}
