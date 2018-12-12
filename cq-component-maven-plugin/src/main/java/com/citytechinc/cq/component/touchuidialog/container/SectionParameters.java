package com.citytechinc.cq.component.touchuidialog.container;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.xml.XmlElement;

public class SectionParameters extends ContainerParameters {

	private String title;
	private String path;
	private TouchUIDialogElement renderCondition;
	private boolean showOnCreate;
	private boolean hideOnEdit;
	private String nodeName;
	private String orderBefore;

	@Override
	public String getResourceType() {
		if (StringUtils.isNotEmpty(path)) {
			return Section.INCLUDE_RESOURCE_TYPE;
		} else {
			return Section.RESOURCE_TYPE;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TouchUIDialogElement getRenderCondition() {
		return renderCondition;
	}

	public void setRenderCondition(TouchUIDialogElement renderCondition) {
		this.renderCondition = renderCondition;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {

		List<XmlElement> elements = new ArrayList<XmlElement>();

		if (renderCondition != null) {
			elements.add(renderCondition);
		}

		if (super.getContainedElements() != null) {
			elements.addAll(super.getContainedElements());
		}

		return elements;
	}

	public boolean isShowOnCreate() {
		return showOnCreate;
	}

	public void setShowOnCreate(boolean showOnCreate) {
		this.showOnCreate = showOnCreate;
	}

	public boolean isHideOnEdit() {
		return hideOnEdit;
	}

	public void setHideOnEdit(boolean hideOnEdit) {
		this.hideOnEdit = hideOnEdit;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getOrderBefore() {
		return orderBefore;
	}

	public void setOrderBefore(String orderBefore) {
		this.orderBefore = orderBefore;
	}
}
