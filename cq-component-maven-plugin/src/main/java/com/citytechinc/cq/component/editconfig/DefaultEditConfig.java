package com.citytechinc.cq.component.editconfig;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class DefaultEditConfig extends AbstractXmlElement implements EditConfig {
	private static final String CQ_NAMESPACE_PREFIX = "cq";

	private final NameSpacedAttribute<String> actions;

	private final NameSpacedAttribute<String> dialogMode;

	private final NameSpacedAttribute<String> layout;

	private final NameSpacedAttribute<String> emptyText;

	private final NameSpacedAttribute<Boolean> inherit;

	public DefaultEditConfig(EditConfigParameters parameters) {
		super(parameters);
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(StringUtils.join(parameters.getActions().toArray(), ",")).append("]");
		this.actions = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, sb.toString());
		this.dialogMode = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getDialogMode());
		this.layout = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getLayout());
		this.emptyText = new NameSpacedAttribute<String>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getEmptyText());
		this.inherit = new NameSpacedAttribute<Boolean>(com.citytechinc.cq.component.util.Constants.CQ_NS_URI,
			CQ_NAMESPACE_PREFIX, parameters.getInherit());
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
}
