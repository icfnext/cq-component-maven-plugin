package com.citytechinc.cq.component.touchuidialog.container;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import org.codehaus.plexus.util.StringUtils;

public class Container extends AbstractTouchUIDialogElement {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/container";

    public static final String RESOURCE_TYPE_CORAL3 = "granite/ui/components/coral/foundation/container";

    public static final String PRIMARY_TYPE = "nt:unstructured";

    private final boolean margin;

    private final boolean maximized;

    private NameSpacedAttribute<String> title;

    private final NameSpacedAttribute<Boolean> showOnCreate;

    private final NameSpacedAttribute<Boolean> hideOnEdit;

    private NameSpacedAttribute<String> orderBefore;

    public Container(ContainerParameters parameters) {
        super(parameters);

        margin = parameters.isMargin();
        maximized = parameters.isMaximized();

        if (StringUtils.isNotEmpty(parameters.getTitle())) {
            title = new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX,
                parameters.getTitle());
        }

        showOnCreate =
            new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isShowOnCreate());

        hideOnEdit =
            new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isHideOnEdit());

        if (StringUtils.isNotEmpty(parameters.getOrderBefore())) {
            orderBefore = new NameSpacedAttribute<String>(Constants.SLING_NS_PREFIX, Constants.SLING_NS_PREFIX,
                parameters.getOrderBefore());
        }
    }

    public boolean isMargin() {
        return margin;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public NameSpacedAttribute<String> getTitle() {
        return title;
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
