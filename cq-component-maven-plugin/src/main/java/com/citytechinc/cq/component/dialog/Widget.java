package com.citytechinc.cq.component.dialog;

import java.util.Map;

public interface Widget extends DialogElement {

	/**
	 *
	 * @return The xtype of the Dialog Element
	 */
	public String getXType();

	/**
	 * The name is the path under which the value of the dialog element is stored relative
	 * to its associated resource.
	 *
	 * @return The name of the Dialog Element
	 */
	public String getName();

	/**
	 *
	 * @return The field Label of the dialog element
	 */
	public String getLabel();

	/**
	 * While not technically part of a dialog widget, the field name is a unique, XML tag name safe, string which identifies
	 * the Widget.
	 *
	 * @return A unique name for the field
	 */
	public String getFieldName();

	public String getFieldDescription();

	public Boolean hasFieldDescription();

	public Boolean isRequired();
	
	public String getDefaultValue();

	public Map<String, String> getAdditionalProperties();

}
