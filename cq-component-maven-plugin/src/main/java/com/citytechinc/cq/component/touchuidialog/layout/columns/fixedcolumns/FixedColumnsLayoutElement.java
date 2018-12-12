package com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns;

import com.citytechinc.cq.component.touchuidialog.layout.AbstractLayoutElement;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import org.codehaus.plexus.util.StringUtils;

public class FixedColumnsLayoutElement extends AbstractLayoutElement {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/layouts/fixedcolumns";
	public static final String RESOURCE_TYPE_CORAL3 = "granite/ui/components/coral/foundation/fixedcolumns";

	private NameSpacedAttribute<String> title;
	private String path;
	private final NameSpacedAttribute<Boolean> showOnCreate;
	private final NameSpacedAttribute<Boolean> hideOnEdit;
	private NameSpacedAttribute<String> orderBefore;

	public FixedColumnsLayoutElement(FixedColumnsLayoutElementParameters parameters) {
		super(parameters);

		if (StringUtils.isNotEmpty(parameters.getTitle())) {
			this.title =
					new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		}

		if (StringUtils.isNotEmpty(parameters.getPath())) {
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
