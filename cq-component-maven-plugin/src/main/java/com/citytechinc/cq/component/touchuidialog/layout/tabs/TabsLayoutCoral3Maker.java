package com.citytechinc.cq.component.touchuidialog.layout.tabs;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.touchuidialog.*;
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

import java.util.*;

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
		Component componentAnnotation = null;
		try {
			componentAnnotation = (Component) parameters.getComponentClass().getAnnotation(Component.class);
		} catch (ClassNotFoundException e) {
			throw new LayoutMakerException("Class Not Found Exception encountered looking up Component annotation", e);
		}

		// Determine the Tabs to create
		List<FixedColumnsLayoutElementParameters> tabParametersList = new ArrayList<FixedColumnsLayoutElementParameters>();
		List<ColumnParameters> tabContentParametersList = new ArrayList<ColumnParameters>();

		for (Tab currentTabAnnotation : componentAnnotation.tabs()) {
			if (StringUtils.isNotEmpty(currentTabAnnotation.title())
				&& StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
				throw new LayoutMakerException("Tabs can have only a path or a title");
			}

			if (StringUtils.isNotEmpty(currentTabAnnotation.title())
				|| StringUtils.isNotEmpty(currentTabAnnotation.touchUIPath())) {
				FixedColumnsLayoutElementParameters currentTabParameters = new FixedColumnsLayoutElementParameters();

				if (StringUtils.isNotEmpty(currentTabAnnotation.title())) {
					String fieldName = StringUtils.isNotBlank(currentTabAnnotation.touchUINodeName()) ? currentTabAnnotation.touchUINodeName() : currentTabAnnotation.title();
					currentTabParameters.setFieldName(fieldName);
					currentTabParameters.setTitle(currentTabAnnotation.title());
					ColumnParameters tabContentParameters = new ColumnParameters();
					tabContentParameters.setFieldName("column");
					tabContentParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
					tabContentParametersList.add(tabContentParameters);
				} else {
					String path = currentTabAnnotation.touchUIPath();
					currentTabParameters.setFieldName(StringUtils.substring(path, StringUtils.lastIndexOfAny(path, new String[]{"/"})+1));
					currentTabParameters.setPath(path);
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
				currentTabParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());
				tabParametersList.add(currentTabParameters);
			} else {
				tabContentParametersList.add(null);
				tabParametersList.add(null);
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

		// Add content to all tabs
		for (int i = 0; i < tabParametersList.size(); i++) {
			if (tabContentParametersList.get(i) != null) {
				Collections.sort(tabContentParametersList.get(i).getItems(), new TouchUIDialogElementComparator());

				Column column = new Column(tabContentParametersList.get(i));

				ItemsParameters itemsParameters = new ItemsParameters();
				itemsParameters.setFieldName("items");
				itemsParameters.setContainedElements(TouchUIDialogUtil.createContainedElements(column));
				Items items = new Items(itemsParameters);

				tabParametersList.get(i).setContainedElements(TouchUIDialogUtil.createContainedElements(items));
			}
		}

		// Create all Tabs
		List<FixedColumnsLayoutElement> tabs = new ArrayList<FixedColumnsLayoutElement>();
		for (FixedColumnsLayoutElementParameters currentLayoutElementParams : tabParametersList) {
			if (currentLayoutElementParams != null) {
				tabs.add(new FixedColumnsLayoutElement(currentLayoutElementParams));
			}
		}

		ItemsParameters itemsParameters = new ItemsParameters();
		itemsParameters.setFieldName("items");
		itemsParameters.setContainedElements(tabs);

		return new Items(itemsParameters);
	}
}
