package com.citytechinc.cq.component.editconfig.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.CtClass;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.editconfig.DefaultEditConfig;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.EditConfigParameters;
import com.citytechinc.cq.component.editconfig.listeners.EditConfigListeners;
import com.citytechinc.cq.component.editconfig.listeners.EditConfigListenersParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class EditConfigFactory {

	private EditConfigFactory() {
	}

	public static final EditConfig make(CtClass componentClass) throws InvalidComponentClassException,
		ClassNotFoundException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation == null) {
			throw new InvalidComponentClassException("Class provided is not property annotated");
		}
		EditConfigParameters parameters = new EditConfigParameters();

		String title = getTitleForEditConfig(componentClass, componentAnnotation);
		parameters.setActions(getActionsForEditConfig(componentAnnotation, title));

		parameters.setDialogMode(getDialogModeForEditConfig(componentAnnotation));

		parameters.setLayout(getLayoutForEditConfig(componentAnnotation));
		parameters.setEmptyText(getEmptyTextForEditConfig(componentAnnotation));
		parameters.setInherit(getInheritForEditConfig(componentAnnotation));

		List<XmlElement> editConfigChildren = new ArrayList<XmlElement>();

		EditConfigListeners ecl = getListenersForEditConfig(componentAnnotation);
		if (ecl != null) {
			editConfigChildren.add(ecl);
		}

		parameters.setContainedElements(editConfigChildren);
		return new DefaultEditConfig(parameters);
	}

	private static boolean getInheritForEditConfig(Component componentAnnotation) {
		return componentAnnotation.editConfigInherit();
	}

	private static String getEmptyTextForEditConfig(Component componentAnnotation) {
		return componentAnnotation.emptyText();
	}

	private static final String getTitleForEditConfig(CtClass componentClass, Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.value())) {
			return componentAnnotation.value();
		}

		return componentClass.getSimpleName();
	}

	private static final List<String> getActionsForEditConfig(Component componentAnnotation, String title) {
		List<String> actions = Arrays.asList(componentAnnotation.actions());

		if (!actions.isEmpty()) {
			return actions;
		}

		actions = new ArrayList<String>();

		actions.add("text:" + title);
		actions.add("-");
		actions.add("edit");
		actions.add("copymove");
		actions.add("delete");
		actions.add("-");
		actions.add("insert");

		return actions;
	}

	private static final String getDialogModeForEditConfig(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.dialogMode())) {
			return componentAnnotation.dialogMode();
		}

		return "floating";
	}

	private static final String getLayoutForEditConfig(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.layout())) {
			return componentAnnotation.layout();
		}

		return "editbar";
	}

	private static final EditConfigListeners getListenersForEditConfig(Component componentAnnotation) {
		if (componentAnnotation.listeners().length > 0) {
			Map<String, String> listeners = new HashMap<String, String>();

			for (Listener listener : componentAnnotation.listeners()) {
				listeners.put(listener.name(), listener.value());
			}

			EditConfigListenersParameters parameters = new EditConfigListenersParameters();
			parameters.setListeners(listeners);

			return new EditConfigListeners(parameters);
		}
		return null;
	}
}
