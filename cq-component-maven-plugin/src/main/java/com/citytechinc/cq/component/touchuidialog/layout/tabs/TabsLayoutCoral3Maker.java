package com.citytechinc.cq.component.touchuidialog.layout.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementComparator;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.container.Container;
import com.citytechinc.cq.component.touchuidialog.container.ContainerParameters;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
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
import com.google.common.collect.ImmutableMap;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsLayoutCoral3Maker extends AbstractLayoutMaker {

    public TabsLayoutCoral3Maker(LayoutMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public Layout make() throws LayoutMakerException {
        // Create layout element and add items to it
        TabsLayoutElementParameters tabsLayoutElementParameters = new TabsLayoutElementParameters();
        tabsLayoutElementParameters.setContainedElements(TouchUIDialogUtil.createContainedElements(makeItems()));
        tabsLayoutElementParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
        tabsLayoutElementParameters.setAdditionalProperties(ImmutableMap.of("maximized", true));
        TabsLayoutElement layoutElement = new TabsLayoutElement(tabsLayoutElementParameters);
        layoutElement.setFieldName("tabs");

        // Create items element and add layout element to it
        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");
        itemsParameters.setContainedElements(TouchUIDialogUtil.createContainedElements(layoutElement));
        Items items = new Items(itemsParameters);

        // Create content element and add items element to it
        TabsLayoutParameters layoutParameters = new TabsLayoutParameters();
        layoutParameters.setContainedElements(TouchUIDialogUtil.createContainedElements(items));
        layoutParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
        layoutParameters.setFieldName("content");

        return new TabsLayout(layoutParameters);
    }

    protected Items makeItems() throws LayoutMakerException {
        Component componentAnnotation;

        try {
            componentAnnotation = (Component) parameters.getComponentClass().getAnnotation(Component.class);
        } catch (ClassNotFoundException e) {
            throw new LayoutMakerException("Class Not Found Exception encountered looking up Component annotation", e);
        }

        // Determine the Tabs to create
        // List<FixedColumnsLayoutElementParameters> tabParametersList = new ArrayList<FixedColumnsLayoutElementParameters>();

        List<ContainerParameters> tabContainerParametersList = new ArrayList<ContainerParameters>();

        List<ColumnParameters> tabContentParametersList = new ArrayList<ColumnParameters>();

        for (Tab currentTabAnnotation : componentAnnotation.tabs()) {
            if (StringUtils.isNotEmpty(currentTabAnnotation.title())
                && StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
                throw new LayoutMakerException("Tabs can have only a path or a title");
            }

            if (StringUtils.isNotEmpty(currentTabAnnotation.title())
                || StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
                // FixedColumnsLayoutElementParameters currentTabParameters = new FixedColumnsLayoutElementParameters();
                ContainerParameters currentTabParameters = new ContainerParameters();

                if (StringUtils.isNotEmpty(currentTabAnnotation.title())) {
                    String fieldName = StringUtils.isNotBlank(
                        currentTabAnnotation.touchUINodeName()) ? currentTabAnnotation
                        .touchUINodeName() : currentTabAnnotation.title();
                    currentTabParameters.setFieldName(fieldName);
                    currentTabParameters.setTitle(currentTabAnnotation.title());
                    ColumnParameters tabContentParameters = new ColumnParameters();
                    tabContentParameters.setFieldName("column");
                    tabContentParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
                    tabContentParametersList.add(tabContentParameters);
                } else {
                    String path = currentTabAnnotation.touchUIPath();
                    currentTabParameters.setFieldName(
                        StringUtils.substring(path, StringUtils.lastIndexOfAny(path, new String[]{ "/" }) + 1));
                    currentTabParameters.setPath(path);
                    tabContentParametersList.add(null);
                }

                if (StringUtils.isNotEmpty(currentTabAnnotation.renderConditionResourceType())) {
                    TouchUIDialogElementParameters renderParameters = new DefaultTouchUIDialogElementParameters();

                    renderParameters.setNameSpace("granite");
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
                currentTabParameters.setMargin(currentTabAnnotation.margin());

                if (StringUtils.isNotEmpty(currentTabAnnotation.orderBefore())) {
                    currentTabParameters.setOrderBefore(currentTabAnnotation.orderBefore());
                }

                currentTabParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
                tabContainerParametersList.add(currentTabParameters);
            } else {
                tabContentParametersList.add(null);
                tabContainerParametersList.add(null);
            }
        }

        try {
            // Populate the content for each tab
            List<TouchUIWidgetMakerParameters> widgetMakerParameters =
                TouchUIDialogUtil.getWidgetMakerParametersForComponentClass(
                    parameters.getComponentClass(),
                    parameters.getClassLoader(),
                    parameters.getClassPool(),
                    parameters.getWidgetRegistry(),
                    parameters.getTouchUIDialogType());

            for (TouchUIWidgetMakerParameters currentWidgetMakerParameters : widgetMakerParameters) {
                TouchUIDialogElement currentElement = TouchUIWidgetFactory.make(currentWidgetMakerParameters, -1);
                if (currentElement != null) {
                    currentElement.setRanking(currentWidgetMakerParameters.getDialogFieldConfig().getRanking());

                    if (currentWidgetMakerParameters.getDialogFieldConfig().getTab() <= tabContainerParametersList
                        .size()) {
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

        // Add content to all tabs
        for (int i = 0; i < tabContainerParametersList.size(); i++) {
            ColumnParameters tabContentParameters = tabContentParametersList.get(i);

            if (tabContentParameters != null) {
                Collections.sort(tabContentParameters.getItems(), new TouchUIDialogElementComparator());

                // actual dialog fields for tab
                Column column = new Column(tabContentParameters);

                // items within fixed columns
                ItemsParameters fixedColumnsItemsParameters = new ItemsParameters();

                fixedColumnsItemsParameters.setFieldName("items");
                fixedColumnsItemsParameters.setContainedElements(TouchUIDialogUtil.createContainedElements(column));

                Items fixedColumnsItems = new Items(fixedColumnsItemsParameters);

                // add fixed columns wrapper element
                FixedColumnsLayoutElementParameters fixedColumnsLayoutElementParameters = new FixedColumnsLayoutElementParameters();

                fixedColumnsLayoutElementParameters.setContainedElements(
                    TouchUIDialogUtil.createContainedElements(fixedColumnsItems));

                fixedColumnsLayoutElementParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

                FixedColumnsLayoutElement fixedColumnsLayoutElement = new FixedColumnsLayoutElement(
                    fixedColumnsLayoutElementParameters);
                fixedColumnsLayoutElement.setFieldName("columns");

                // add items wrapper around fixed columns
                ItemsParameters itemsParameters = new ItemsParameters();
                itemsParameters.setFieldName("items");
                itemsParameters.setContainedElements(Collections.singletonList(fixedColumnsLayoutElement));
                Items items = new Items(itemsParameters);

                tabContainerParametersList.get(i).setContainedElements(
                    TouchUIDialogUtil.createContainedElements(items));
            }
        }

        // Create all Tabs
        List<Container> tabs = new ArrayList<Container>(tabContainerParametersList.size());

        for (ContainerParameters tabContainerParameters : tabContainerParametersList) {
            if (tabContainerParameters != null) {
                tabs.add(new Container(tabContainerParameters));
            }
        }

        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");
        itemsParameters.setContainedElements(tabs);

        return new Items(itemsParameters);
    }
}
