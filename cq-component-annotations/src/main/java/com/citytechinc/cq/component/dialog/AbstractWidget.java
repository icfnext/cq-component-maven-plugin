package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;

/**
 * Abstract class representing a Widget in the context of a Dialog. Concrete
 * Widgets will extend this class and add any properties required by the
 * extending Widget.
 */
public abstract class AbstractWidget extends AbstractDialogElement {
	private final String xtype;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean allowBlank;
	private final String defaultValue;
	private String name;
	private final boolean hideLabel;

	/**
	 * The constructor for the abstract Widget sets a number of properties
	 * common to all widget types.
	 * 
	 * @param parameters
	 */
	public AbstractWidget(WidgetParameters parameters) {
		super(parameters);
		this.xtype = parameters.getXtype();
		this.fieldLabel = parameters.getFieldLabel();
		this.fieldDescription = parameters.getFieldDescription();
		this.allowBlank = parameters.isAllowBlank();
		this.defaultValue = parameters.getDefaultValue();
		this.name = parameters.getName();
		this.hideLabel = parameters.isHideLabel();
	}

	/**
	 * 
	 * @return xtype of the Widget
	 */
	public final String getXtype() {
		return xtype;
	}

	/**
	 * 
	 * @return name of the Widget
	 */
	public final String getName() {
		return name;
	}

	/**
	 * 
	 * @return field label to be applied to the rendered Widget
	 */
	public final String getFieldLabel() {
		return fieldLabel;
	}

	/**
	 * 
	 * @return field description to be applied to the rendered Widget
	 */
	public final String getFieldDescription() {
		return fieldDescription;
	}

	/**
	 * 
	 * @return Indication of whether the Widget may be left blank in an
	 *         authoring Dialog
	 */
	public final boolean isAllowBlank() {
		return allowBlank;
	}

	/**
	 * 
	 * @return Indication of whether the field label should be hidden for the
	 *         Widget in the authoring Dialog
	 */
	public final boolean isHideLabel() {
		return hideLabel;
	}

	/**
	 * 
	 * @return Default value of the Widget input
	 */
	public final String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 
	 * @param name
	 */
	public final void setName(String name) {
		this.name = name;
	}

}
