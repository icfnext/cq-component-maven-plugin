package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;

public abstract class AbstractWidget extends AbstractDialogElement {
	private final String xtype;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean allowBlank;
	private final String defaultValue;
	private final String name;
	private final boolean hideLabel;

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

	public final String getXtype() {
		return xtype;
	}

	public final String getName() {
		return name;
	}

	public final String getFieldLabel() {
		return fieldLabel;
	}

	public final String getFieldDescription() {
		return fieldDescription;
	}

	public final boolean isAllowBlank() {
		return allowBlank;
	}

	public final boolean isHideLabel() {
		return hideLabel;
	}

	public final String getDefaultValue() {
		return defaultValue;
	}

}
