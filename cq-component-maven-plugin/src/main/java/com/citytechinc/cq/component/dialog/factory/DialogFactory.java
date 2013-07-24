package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.DialogParameters;
import com.citytechinc.cq.component.dialog.TabbableDialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.tab.Tab;
import com.citytechinc.cq.component.dialog.tab.TabParameters;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;

public class DialogFactory {

	private DialogFactory() {
	}

	public static Dialog make(CtClass componentClass, WidgetRegistry widgetRegistry, ClassLoader classLoader,
		ClassPool classPool) throws InvalidComponentClassException, InvalidComponentFieldException,
		ClassNotFoundException, CannotCompileException, NotFoundException, SecurityException, NoSuchFieldException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		Map<String, List<DialogElement>> tabMap = new LinkedHashMap<String, List<DialogElement>>();

		/*
		 * Get dialog title
		 */
		String dialogTitle = componentAnnotation.value();

		/*
		 * Setup Tabs from Component tab list
		 */
		List<String> tabsList = Arrays.asList(componentAnnotation.tabs());

		if (tabsList.isEmpty()) {
		    tabsList = Arrays.asList( new String[] {dialogTitle});
		}

		for (String curTab : tabsList) {
			tabMap.put(curTab, new ArrayList<DialogElement>());
		}

		List<CtMember> fieldsAndMethods = new ArrayList<CtMember>();
		fieldsAndMethods.addAll(ComponentMojoUtil.collectFields(componentClass));
		fieldsAndMethods.addAll(ComponentMojoUtil.collectMethods(componentClass));

		// Load the true class
		Class<?> trueComponentClass = classLoader.loadClass(componentClass.getName());

		/*
		 * Iterate through all fields establishing proper widgets for each
		 */
		for (CtMember member : fieldsAndMethods) {

			DialogField dialogProperty = (DialogField) member.getAnnotation(DialogField.class);

			if (dialogProperty != null) {

				WidgetMakerParameters parameters = new WidgetMakerParameters(dialogProperty, member,
					trueComponentClass, classLoader, classPool, widgetRegistry, null, true);

				DialogElement builtFieldWidget = WidgetFactory.make(parameters, -1);

				builtFieldWidget.setRanking(dialogProperty.ranking());

				int tabIndex = dialogProperty.tab();

				if (tabIndex < 1 || tabIndex > tabsList.size()) {
				    throw new InvalidComponentFieldException("Invalid tab index " + tabIndex + " for field " + dialogProperty.fieldName());
				}

				tabMap.get(tabsList.get(tabIndex - 1)).add(builtFieldWidget);

			}
		}

	    List<DialogElement> tabList = new ArrayList<DialogElement>();

		for (String curMapKey : tabMap.keySet()) {
		    tabList.add(buildTabForDialogElementSet(curMapKey, tabMap.get(curMapKey)));
		}

		Integer width = null;
		Integer height = null;
		if (componentAnnotation.dialogWidth() > 0) {
			width = componentAnnotation.dialogWidth();
		}
		if (componentAnnotation.dialogHeight() > 0) {
			height = componentAnnotation.dialogHeight();
		}
		DialogParameters dialogParams = new DialogParameters();
		dialogParams.setContainedElements(Dialog.buildTabPanel(tabList));
		dialogParams.setTitle(dialogTitle);
		dialogParams.setFileName(componentAnnotation.fileName());
		dialogParams.setWidth(width);
		dialogParams.setHeight(height);
		return new Dialog(dialogParams);
	}

	private static final DialogElement buildTabForDialogElementSet(String tabName, List<DialogElement> elements) throws InvalidComponentFieldException {
	    /*
	     * Verify that, if elements contains tabbable elements, that it only contains one element.
	     * If the elements set contains a single tab element, return it as the tab.
	     */
	    for(int i=0; i<elements.size(); i++) {
	        DialogElement curElement = elements.get(i);
	        if (curElement instanceof TabbableDialogElement) {
	            LogSingleton.getInstance().error("Tabbable widget found " + curElement.getFieldName());
	            TabbableDialogElement curTabbableElement = (TabbableDialogElement) curElement;

	            LogSingleton.getInstance().error("Is Tab? " + curTabbableElement.isTab());

	            if (curTabbableElement.isTab()) {
    	            if (i != 0 || elements.size() != 1) {
    	                throw new InvalidComponentFieldException("A Tab dialog element can not be placed inside another tab.");
    	            }

    	            //TODO: When this happens - the name from the element gets used as the tab name and not the name specified in the component annotation
    	            return curElement;
	            }
	        }
	    }

	    /*
	     * Construct a new Tab object containing all provided elements
	     */
        WidgetCollectionParameters wcp = new WidgetCollectionParameters();
        wcp.setContainedElements(elements);
        WidgetCollection widgetCollection = new WidgetCollection(wcp);
        TabParameters tabParams = new TabParameters();
        tabParams.setTitle(tabName);
        tabParams.setContainedElements(Arrays.asList(new DialogElement[] { widgetCollection }));
        return new Tab(tabParams);
	}

}
