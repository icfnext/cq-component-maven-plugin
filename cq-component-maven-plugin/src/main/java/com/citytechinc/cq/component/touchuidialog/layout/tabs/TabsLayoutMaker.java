package com.citytechinc.cq.component.touchuidialog.layout.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementComparator;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.container.Section;
import com.citytechinc.cq.component.touchuidialog.container.SectionParameters;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.LayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.columns.Column;
import com.citytechinc.cq.component.touchuidialog.layout.columns.ColumnParameters;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutElementParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.AbstractLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.XmlElement;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsLayoutMaker extends AbstractLayoutMaker {

    public TabsLayoutMaker(LayoutMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public Layout make() throws LayoutMakerException {
        // Construct the TabsLayout Parameters
        TabsLayoutParameters layoutParameters = new TabsLayoutParameters();

        // Construct the appropriate Layout Element
        TabsLayoutElementParameters tabsLayoutElementParameters = new TabsLayoutElementParameters();
        tabsLayoutElementParameters.setType("nav");
        TabsLayoutElement layoutElement = new TabsLayoutElement(tabsLayoutElementParameters);

        // Add the Layout element and Items to the contained elements list
        List<XmlElement> containedElements = new ArrayList<XmlElement>();
        containedElements.add(layoutElement);
        containedElements.add(makeItems());

        layoutParameters.setContainedElements(containedElements);

        // TODO: Determine how to set this - in the case of nested layouts this
        // won't always be "content"
        layoutParameters.setFieldName("content");

        return new TabsLayout(layoutParameters);

    }

    protected Items makeItems() throws LayoutMakerException {

        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");

        Component componentAnnotation;
        try {
            componentAnnotation = (Component) parameters.getComponentClass().getAnnotation(Component.class);
        } catch (ClassNotFoundException e) {
            throw new LayoutMakerException("Class Not Found Exception encountered looking up Component annotation", e);
        }

        // Determine the Tabs to create
        List<SectionParameters> tabParametersList = new ArrayList<SectionParameters>();
        List<ColumnParameters> tabContentParametersList = new ArrayList<ColumnParameters>();
        for (Tab currentTabAnnotation : componentAnnotation.tabs()) {
            if (StringUtils.isNotEmpty(currentTabAnnotation.title())
                && StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
                throw new LayoutMakerException("Tabs can have only a path or a title");
            }
            if (StringUtils.isNotEmpty(currentTabAnnotation.title())
                || StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
                SectionParameters currentTabParameters = new SectionParameters();
                if (StringUtils.isNotEmpty(currentTabAnnotation.title())) {
                    currentTabParameters.setTitle(currentTabAnnotation.title());
                    currentTabParameters.setNodeName(currentTabAnnotation.touchUINodeName());
                    LayoutElement currentTabLayoutElement =
                        new FixedColumnsLayoutElement(new FixedColumnsLayoutElementParameters());
                    currentTabParameters.setLayoutElement(currentTabLayoutElement);
                    ColumnParameters tabContentParameters = new ColumnParameters();
                    tabContentParameters.setFieldName("column");
                    tabContentParametersList.add(tabContentParameters);
                }

                if (StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
                    currentTabParameters.setPath(currentTabAnnotation.touchUIPath());
                    tabContentParametersList.add(null);
                }
                if (StringUtils.isNotEmpty(currentTabAnnotation.renderConditionResourceType())) {
                    TouchUIDialogElementParameters renderParameters = new DefaultTouchUIDialogElementParameters();
                    renderParameters.setFieldName("rendercondition");
                    renderParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
                    renderParameters.setResourceType(currentTabAnnotation.renderConditionResourceType());
                    Map<String, String> additionalPropertiesMap = new HashMap<String, String>();
                    for (Property property : currentTabAnnotation.renderConditionProperties()) {
                        additionalPropertiesMap.put(property.name(), property.value());
                    }
                    renderParameters.setAdditionalProperties(additionalPropertiesMap);
                    DefaultTouchUIDialogElement renderConditionElement =
                        new DefaultTouchUIDialogElement(renderParameters);
                    currentTabParameters.setRenderCondition(renderConditionElement);
                }
                currentTabParameters.setShowOnCreate(currentTabAnnotation.showOnCreate());
                currentTabParameters.setHideOnEdit(currentTabAnnotation.hideOnEdit());
                if (StringUtils.isNotEmpty(currentTabAnnotation.orderBefore())) {
                    currentTabParameters.setOrderBefore(currentTabAnnotation.orderBefore());
                }
                tabParametersList.add(currentTabParameters);
            } else {
                tabContentParametersList.add(null);
                tabParametersList.add(null);
            }
        }

        try {
            // Populate the content for each tab
            List<TouchUIWidgetMakerParameters> widgetMakerParameters =
                TouchUIDialogUtil.getWidgetMakerParametersForComponentClass(parameters.getComponentClass(),
                    parameters.getClassLoader(), parameters.getClassPool(), parameters.getWidgetRegistry());

            for (TouchUIWidgetMakerParameters currentWidgetMakerParameters : widgetMakerParameters) {

                TouchUIDialogElement currentElement = TouchUIWidgetFactory.make(currentWidgetMakerParameters, -1);
                if (currentElement != null) {
                    currentElement.setRanking(currentWidgetMakerParameters.getDialogFieldConfig().getRanking());

                    if (currentWidgetMakerParameters.getDialogFieldConfig().getTab() <= tabParametersList.size()) {
                        tabContentParametersList.get(currentWidgetMakerParameters.getDialogFieldConfig().getTab() - 1)
                            .addItem(currentElement);
                    } else {
                        throw new LayoutMakerException("Field "
                            + currentWidgetMakerParameters.getDialogFieldConfig().getName() + " of class "
                            + currentWidgetMakerParameters.getClass().getName() + " placed in non-existant tab "
                            + currentWidgetMakerParameters.getDialogFieldConfig().getTab());
                    }
                }

            }
        } catch (Exception e) {
            throw new LayoutMakerException("Exception encountered while constructing widgets for layout", e);
        }

        // Add content to all the tabs
        for (int i = 0; i < tabParametersList.size(); i++) {
            if (tabContentParametersList.get(i) != null) {
                Collections.sort(tabContentParametersList.get(i).getItems(), new TouchUIDialogElementComparator());
                tabParametersList.get(i).addItem(new Column(tabContentParametersList.get(i)));
            }
        }

        // Create all the Tabs
        List<Section> tabs = new ArrayList<Section>();
        for (int i = 0; i < tabParametersList.size(); i++) {
            if (tabParametersList.get(i) != null) {
                SectionParameters currentSectionParameters = tabParametersList.get(i);
                if (StringUtils.isNotEmpty(tabParametersList.get(i).getNodeName())) {
                    currentSectionParameters.setFieldName(tabParametersList.get(i).getNodeName());
                } else if (StringUtils.isNotEmpty(tabParametersList.get(i).getTitle())) {
                    currentSectionParameters.setFieldName(tabParametersList.get(i).getTitle());
                } else {
                    currentSectionParameters.setFieldName("tab_" + i);
                }
                tabs.add(new Section(currentSectionParameters));
            }
        }

        itemsParameters.setContainedElements(tabs);

        return new Items(itemsParameters);
    }
}
