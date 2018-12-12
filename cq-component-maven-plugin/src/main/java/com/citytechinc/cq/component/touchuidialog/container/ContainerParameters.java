package com.citytechinc.cq.component.touchuidialog.container;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.LayoutElement;
import com.citytechinc.cq.component.xml.XmlElement;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ContainerParameters extends DefaultTouchUIDialogElementParameters {

    private String title;

    private String path;

    private TouchUIDialogElement renderCondition;

    private boolean showOnCreate;

    private boolean hideOnEdit;

    private String orderBefore;

    private boolean margin;

    private boolean maximized;

    private LayoutElement layoutElement;

    protected List<TouchUIDialogElement> items;

    public ContainerParameters() {
        items = new ArrayList<TouchUIDialogElement>();
    }

    @Override
    public String getResourceType() {
        if (StringUtils.isNotEmpty(path)) {
            return Section.INCLUDE_RESOURCE_TYPE;
        } else if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return Container.RESOURCE_TYPE_CORAL3;
        }

        return Container.RESOURCE_TYPE;
    }

    public boolean isMargin() {
        return margin;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMargin(final boolean margin) {
        this.margin = margin;
    }

    public void setMaximized(final boolean maximized) {
        this.maximized = maximized;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resource type is Static for Container");
    }

    @Override
    public String getPrimaryType() {
        return Container.PRIMARY_TYPE;
    }

    @Override
    public void setPrimaryType(String primaryType) {
        throw new UnsupportedOperationException("primary type is Static for Container");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public TouchUIDialogElement getRenderCondition() {
        return renderCondition;
    }

    public void setRenderCondition(final TouchUIDialogElement renderCondition) {
        this.renderCondition = renderCondition;
    }

    public boolean isShowOnCreate() {
        return showOnCreate;
    }

    public void setShowOnCreate(final boolean showOnCreate) {
        this.showOnCreate = showOnCreate;
    }

    public boolean isHideOnEdit() {
        return hideOnEdit;
    }

    public void setHideOnEdit(final boolean hideOnEdit) {
        this.hideOnEdit = hideOnEdit;
    }

    public String getOrderBefore() {
        return orderBefore;
    }

    public void setOrderBefore(final String orderBefore) {
        this.orderBefore = orderBefore;
    }

    public LayoutElement getLayoutElement() {
        return layoutElement;
    }

    public void setLayoutElement(LayoutElement layoutElement) {
        this.layoutElement = layoutElement;
    }

    public List<TouchUIDialogElement> getItems() {
        return items;
    }

    public Items getItemsElement() {
        ItemsParameters itemsParameters = new ItemsParameters();

        itemsParameters.setFieldName("items");

        if (!items.isEmpty()) {
            itemsParameters.setContainedElements(items);
            return new Items(itemsParameters);
        }

        return null;
    }

    public void addItem(TouchUIDialogElement element) {
        items.add(element);
    }

    @Override
    public List<? extends XmlElement> getContainedElements() {
        List<XmlElement> elements = new ArrayList<XmlElement>();

        if (layoutElement != null) {
            elements.add(layoutElement);
        }

        Items items = getItemsElement();

        if (items != null) {
            elements.add(items);
        }

        if (super.getContainedElements() != null) {
            elements.addAll(super.getContainedElements());
        }

        return elements;
    }
}
