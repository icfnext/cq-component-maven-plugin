package com.citytechinc.cq.component.editconfig;

import com.citytechinc.cq.component.util.ComponentUtil;
import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class DefaultEditConfig extends AbstractXmlElement implements EditConfig {
	private static final String CQ_NAMESPACE_PREFIX = "cq";

	private final NameSpacedAttribute<String> actions;

	private final NameSpacedAttribute<String> dialogMode;

	private final NameSpacedAttribute<String> layout;

	private final NameSpacedAttribute<String> emptyText;

	private final NameSpacedAttribute<Boolean> inherit;

	private final NameSpacedAttribute<Boolean> disableTargeting;

	public DefaultEditConfig(EditConfigParameters parameters) {
		super(parameters);
		this.actions = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, ComponentUtil.generateStringFromList(parameters.getActions()));
		this.dialogMode = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getDialogMode());
		this.layout = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getLayout());
		this.emptyText = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getEmptyText());
		this.inherit = new NameSpacedAttribute<Boolean>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getInherit());
		this.disableTargeting = new NameSpacedAttribute<Boolean>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getDisableTargeting());
	}

	public NameSpacedAttribute<String> getActions() {
		return actions;
	}

	public NameSpacedAttribute<String> getDialogMode() {
		return dialogMode;
	}

	public NameSpacedAttribute<String> getLayout() {
		return layout;
	}

	public NameSpacedAttribute<String> getEmptyText() {
		return emptyText;
	}

	public NameSpacedAttribute<Boolean> isInherit() {
		return inherit;
	}

	public NameSpacedAttribute<Boolean> isDisableTargeting() {
		return disableTargeting;
	}
}
