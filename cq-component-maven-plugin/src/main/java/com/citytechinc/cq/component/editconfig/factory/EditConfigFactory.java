/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfigProperty;
import com.citytechinc.cq.component.annotations.editconfig.DropTarget;
import com.citytechinc.cq.component.annotations.editconfig.FormParameter;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.editconfig.EditConfig;
import com.citytechinc.cq.component.editconfig.EditConfigParameters;
import com.citytechinc.cq.component.editconfig.actionconfigs.EditConfigActionConfig;
import com.citytechinc.cq.component.editconfig.actionconfigs.EditConfigActionConfigParameters;
import com.citytechinc.cq.component.editconfig.actionconfigs.EditConfigActionConfigs;
import com.citytechinc.cq.component.editconfig.actionconfigs.EditConfigActionConfigsParameters;
import com.citytechinc.cq.component.editconfig.droptargets.EditConfigDropTarget;
import com.citytechinc.cq.component.editconfig.droptargets.EditConfigDropTargetParameters;
import com.citytechinc.cq.component.editconfig.droptargets.EditConfigDropTargets;
import com.citytechinc.cq.component.editconfig.droptargets.EditConfigDropTargetsParameters;
import com.citytechinc.cq.component.editconfig.formparameters.EditConfigFormParameters;
import com.citytechinc.cq.component.editconfig.formparameters.EditConfigFormParametersParameters;
import com.citytechinc.cq.component.editconfig.inplaceediting.EditConfigInPlaceEditing;
import com.citytechinc.cq.component.editconfig.inplaceediting.EditConfigInPlaceEditingParameters;
import com.citytechinc.cq.component.editconfig.listeners.EditConfigListeners;
import com.citytechinc.cq.component.editconfig.listeners.EditConfigListenersParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class EditConfigFactory {
	private static final String ACTION_CONFIG_FIELD_NAME = "actionConfig";

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
		parameters.setDisableTargeting(getDisableTargingForEditConfig(componentAnnotation));

		List<XmlElement> editConfigChildren = new ArrayList<XmlElement>();

		EditConfigListeners ecl = getListenersForEditConfig(componentAnnotation);
		if (ecl != null) {
			editConfigChildren.add(ecl);
		}

		EditConfigActionConfigs ecac = getActionConfigsForEditConfig(componentAnnotation);
		if (ecac != null) {
			editConfigChildren.add(ecac);
		}

		EditConfigInPlaceEditing ecipe = getInPlaceEditingForEditConfig(componentAnnotation);
		if (ecipe != null) {
			editConfigChildren.add(ecipe);
		}

		EditConfigFormParameters ecfp = getFormParametersForEditConfig(componentAnnotation);
		if (ecfp != null) {
			editConfigChildren.add(ecfp);
		}

		EditConfigDropTargets ecdt = getDropTargetsForEditConfig(componentAnnotation);
		if (ecdt != null) {
			editConfigChildren.add(ecdt);
		}

		parameters.setContainedElements(editConfigChildren);
		return new EditConfig(parameters);
	}

	private static Boolean getDisableTargingForEditConfig(Component componentAnnotation) {
		return componentAnnotation.disableTargeting();
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

	private static final EditConfigFormParameters getFormParametersForEditConfig(Component componentAnnotation) {
		if (componentAnnotation.formParameters().length > 0) {
			Map<String, String> formParameters = new HashMap<String, String>();

			for (FormParameter formParameter : componentAnnotation.formParameters()) {
				formParameters.put(formParameter.name(), formParameter.value());
			}

			EditConfigFormParametersParameters parameters = new EditConfigFormParametersParameters();
			parameters.setAdditionalProperties(formParameters);

			return new EditConfigFormParameters(parameters);
		}
		return null;
	}

	private static final EditConfigInPlaceEditing getInPlaceEditingForEditConfig(Component componentAnnotation) {
		if (!StringUtils.isEmpty(componentAnnotation.inPlaceEditingConfigPath())
			|| !StringUtils.isEmpty(componentAnnotation.inPlaceEditingConfigPath())) {
			EditConfigInPlaceEditingParameters parameters = new EditConfigInPlaceEditingParameters();
			if (!StringUtils.isEmpty(componentAnnotation.inPlaceEditingConfigPath())) {
				parameters.setConfigPath(componentAnnotation.inPlaceEditingConfigPath());
			}
			if (!StringUtils.isEmpty(componentAnnotation.inPlaceEditingEditorType())) {
				parameters.setEditorType(componentAnnotation.inPlaceEditingEditorType());
			}
			parameters.setActive(componentAnnotation.inPlaceEditingActive());
			return new EditConfigInPlaceEditing(parameters);
		}
		return null;
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

	private static final EditConfigActionConfigs getActionConfigsForEditConfig(Component componentAnnotation) {
		if (componentAnnotation.actionConfigs().length > 0) {

			List<EditConfigActionConfig> actionConfigs = new ArrayList<EditConfigActionConfig>();

			int counter = 0;
			for (ActionConfig actionConfig : componentAnnotation.actionConfigs()) {
				EditConfigActionConfigParameters params = new EditConfigActionConfigParameters();
				if (!StringUtils.isEmpty(actionConfig.handler())) {
					params.setHandler(actionConfig.handler());
				}
				if (!StringUtils.isEmpty(actionConfig.text())) {
					params.setText(actionConfig.text());
				}
				if (!StringUtils.isEmpty(actionConfig.xtype())) {
					params.setXtype(actionConfig.xtype());
				}
				if (actionConfig.additionalProperties().length > 0) {
					Map<String, String> additionalProperties = new HashMap<String, String>();
					for (ActionConfigProperty acp : actionConfig.additionalProperties()) {
						additionalProperties.put(acp.name(), acp.value());
					}
					params.setAdditionalProperties(additionalProperties);
				}
				params.setFieldName(ACTION_CONFIG_FIELD_NAME + counter);
				actionConfigs.add(new EditConfigActionConfig(params));
				counter++;
			}

			EditConfigActionConfigsParameters parameters = new EditConfigActionConfigsParameters();
			parameters.setContainedElements(actionConfigs);

			return new EditConfigActionConfigs(parameters);
		}
		return null;
	}

	private static final EditConfigDropTargets getDropTargetsForEditConfig(Component componentAnnotation) {
		if (componentAnnotation.actionConfigs().length > 0) {

			List<EditConfigDropTarget> dropTargets = new ArrayList<EditConfigDropTarget>();

			for (DropTarget dropTargetConfig : componentAnnotation.dropTargets()) {
				EditConfigDropTargetParameters params = new EditConfigDropTargetParameters();
				params.setFieldName(dropTargetConfig.nodeName());
				params.setGroups(dropTargetConfig.groups());
				params.setAccept(dropTargetConfig.accept());
				params.setPropertyName(dropTargetConfig.propertyName());
				dropTargets.add(new EditConfigDropTarget(params));
			}

			EditConfigDropTargetsParameters parameters = new EditConfigDropTargetsParameters();
			parameters.setContainedElements(dropTargets);

			return new EditConfigDropTargets(parameters);
		}
		return null;
	}
}
