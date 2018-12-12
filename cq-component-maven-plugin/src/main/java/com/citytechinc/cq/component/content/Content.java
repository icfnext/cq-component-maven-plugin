package com.citytechinc.cq.component.content;

import java.util.List;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.AbstractXmlElement;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

/**
 * Based on http://dev.day.com/docs/en/cq/current/developing/components.html
 */
public class Content extends AbstractXmlElement {

	private final List<String> allowedChildren;
	private final List<String> allowedParents;
	private final String componentGroup;
	private final NameSpacedAttribute<String> cellName;
	private final NameSpacedAttribute<Boolean> isContainer;
	private final NameSpacedAttribute<Boolean> noDecoration;
	private final NameSpacedAttribute<String> templatePath;
	private final String dialogPath;
	private final NameSpacedAttribute<String> created;
	private final NameSpacedAttribute<String> description;
	private final NameSpacedAttribute<String> title;
	private final NameSpacedAttribute<String> resourceSuperType;
	private final String className;

	public Content(ContentParameters parameters) {
		super(parameters);

		allowedChildren = parameters.getAllowedChildren();
		allowedParents = parameters.getAllowedParents();
		componentGroup = parameters.getComponentGroup();
		cellName =
			new NameSpacedAttribute<String>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.getCellName());
		isContainer =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.getIsContainer());
		noDecoration =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.getNoDecoration());
		templatePath =
			new NameSpacedAttribute<String>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.getTemplatePath());
		dialogPath = parameters.getDialogPath();
		created =
			new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getCreated());
		description =
			new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getDescription());
		title = new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		resourceSuperType =
			new NameSpacedAttribute<String>(Constants.SLING_NS_URI, Constants.SLING_NS_PREFIX,
				parameters.getResourceSuperType());
		className = parameters.getClassName();
	}

	public List<String> getAllowedChildren() {
		return allowedChildren;
	}

	public List<String> getAllowedParents() {
		return allowedParents;
	}

	public String getClassName() {
		return className;
	}

	public String getComponentGroup() {
		return componentGroup;
	}

	public NameSpacedAttribute<String> getCellName() {
		return cellName;
	}

	public NameSpacedAttribute<Boolean> getIsContainer() {
		return isContainer;
	}

	public NameSpacedAttribute<Boolean> getNoDecoration() {
		return noDecoration;
	}

	public NameSpacedAttribute<String> getTemplatePath() {
		return templatePath;
	}

	public String getDialogPath() {
		return dialogPath;
	}

	public NameSpacedAttribute<String> getCreated() {
		return created;
	}

	public NameSpacedAttribute<String> getDescription() {
		return description;
	}

	public NameSpacedAttribute<String> getTitle() {
		return title;
	}

	public NameSpacedAttribute<String> getResourceSuperType() {
		return resourceSuperType;
	}
}
