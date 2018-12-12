package com.citytechinc.cq.component.touchuidialog.container;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class Section extends Container {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/section";
	public static final String INCLUDE_RESOURCE_TYPE = "granite/ui/components/foundation/include";

	private NameSpacedAttribute<String> title;
	private String path;
	private String resourceType;
	private final NameSpacedAttribute<Boolean> showOnCreate;
	private final NameSpacedAttribute<Boolean> hideOnEdit;
	private NameSpacedAttribute<String> orderBefore;

	public Section(SectionParameters parameters) {
		super(parameters);

		if (StringUtils.isNotEmpty(parameters.getTitle())) {
			this.title =
				new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		}

		if (StringUtils.isNotEmpty(parameters.getPath())) {
			this.resourceType = RESOURCE_TYPE;
			this.path = parameters.getPath();
		}

		showOnCreate =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isShowOnCreate());

		hideOnEdit =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isHideOnEdit());

		if (StringUtils.isNotEmpty(parameters.getOrderBefore())) {
			orderBefore =
				new NameSpacedAttribute<String>(Constants.SLING_NS_PREFIX, Constants.SLING_NS_PREFIX,
					parameters.getOrderBefore());
		}
	}

	public NameSpacedAttribute<String> getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}

	public String getResourceType() {
		return resourceType;
	}

	public NameSpacedAttribute<Boolean> getShowOnCreate() {
		return showOnCreate;
	}

	public NameSpacedAttribute<Boolean> getHideOnEdit() {
		return hideOnEdit;
	}

	public NameSpacedAttribute<String> getOrderBefore() {
		return orderBefore;
	}

}
