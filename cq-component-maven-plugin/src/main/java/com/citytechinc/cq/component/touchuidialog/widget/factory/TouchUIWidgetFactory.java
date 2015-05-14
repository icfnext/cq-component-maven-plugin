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
package com.citytechinc.cq.component.touchuidialog.widget.factory;

import javax.annotation.Nullable;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.DefaultTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.textfield.TextFieldWidget;
import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;

public class TouchUIWidgetFactory {

	public static TouchUIDialogElement make(TouchUIWidgetMakerParameters widgetMakerParameters, int rankingCeiling)
		throws InvalidComponentFieldException, TouchUIDialogGenerationException {
		TouchUIWidgetMakerContext context = getWidgetMakerForMemberParameters(widgetMakerParameters, rankingCeiling);

		widgetMakerParameters.setResourceType(context.getResourceType());

		try {
			TouchUIWidgetMaker widgetMaker =
				context.getMakerClass().getConstructor(TouchUIWidgetMakerParameters.class)
					.newInstance(widgetMakerParameters);
			return widgetMaker.make();
		} catch (Exception e) {
			throw new TouchUIDialogGenerationException("Exception encountered generating widget for "
				+ widgetMakerParameters.getCtMember().getName() + " of " + widgetMakerParameters.getClass().getName(),
				e);
		}
	}

	public static TouchUIWidgetMakerContext getWidgetMakerForMemberParameters(TouchUIWidgetMakerParameters parameters,
		int rankingCeiling) throws InvalidComponentFieldException {
		TouchUIWidgetConfigHolder widgetConfig = getWidgetConfigForParameters(parameters, rankingCeiling);

		// If we were able to lookup a widget configuration with a valid maker
		// class return it
		if (widgetConfig != null && widgetConfig.getMakerClass() != null) {
			return new TouchUIWidgetMakerContext(widgetConfig.getMakerClass(), widgetConfig.getResourceType());
		}

		// If a widget configuration was found but no maker class was associated
		// with it, use the default class
		if (widgetConfig != null && widgetConfig.getResourceType() != null) {
			return new TouchUIWidgetMakerContext(DefaultTouchUIWidgetMaker.class, widgetConfig.getResourceType());
		}

		// If we have gotten here this means there is probably not a stacked
		// annotation an we should attempt to guess the appropriate field type
		String inferredResourceType = getResourceTypeForMemberParameters(parameters);

		if (StringUtils.isBlank(inferredResourceType)) {
			throw new InvalidComponentFieldException("A resource type could not be determined for "
				+ parameters.getCtMember().getName() + " of " + parameters.getClass().getName());
		}

		TouchUIWidgetConfigHolder inferredWidgetConfig =
			getWidgetConfigForResourceType(inferredResourceType, parameters, rankingCeiling);

		if (inferredWidgetConfig != null && inferredWidgetConfig.getMakerClass() != null) {
			return new TouchUIWidgetMakerContext(inferredWidgetConfig.getMakerClass(), inferredResourceType);
		}

		return new TouchUIWidgetMakerContext(DefaultTouchUIWidgetMaker.class, inferredResourceType);
	}

	private static String getResourceTypeForMemberParameters(TouchUIWidgetMakerParameters parameters) {
		// TODO: Implement
		return TextFieldWidget.RESOURCE_TYPE;
	}

	/**
	 * Looks up the highest ranked TouchUIWidget based annotation tied to the
	 * member in question and returns the configuration associated with the
	 * annotation if found.
	 *
	 * @param parameters The parameters identifying the Component member being
	 *            processed
	 * @param rankingCeiling Widget configurations with rankings higher than the
	 *            ceiling will not be considered
	 * @return The determined widget configuration or null
	 */
	@Nullable
	private static TouchUIWidgetConfigHolder getWidgetConfigForParameters(TouchUIWidgetMakerParameters parameters,
		int rankingCeiling) {
		TouchUIWidgetConfigHolder highestRankedConfig = null;

		for (Class<?> currentWidgetAnnotation : parameters.getWidgetRegistry().getRegisteredAnnotations()) {
			if (parameters.getCtMember().hasAnnotation(currentWidgetAnnotation)) {
				TouchUIWidgetConfigHolder potentialWidgetConfig =
					parameters.getWidgetRegistry().getWidgetForAnnotation(currentWidgetAnnotation);
				if (rankingCeiling < 0 || potentialWidgetConfig.getRanking() < rankingCeiling) {
					if (highestRankedConfig == null
						|| highestRankedConfig.getRanking() < potentialWidgetConfig.getRanking()) {
						highestRankedConfig = potentialWidgetConfig;
					}
				}
			}
		}

		return highestRankedConfig;
	}

	@Nullable
	private static TouchUIWidgetConfigHolder getWidgetConfigForResourceType(String resourceType,
		TouchUIWidgetMakerParameters parameters, int rankingCeiling) {
		TouchUIWidgetConfigHolder highestRankedConfig = null;

		for (Class<?> currentWidgetAnnotation : parameters.getWidgetRegistry().getRegisteredAnnotations()) {
			TouchUIWidgetConfigHolder currentConfig =
				parameters.getWidgetRegistry().getWidgetForAnnotation(currentWidgetAnnotation);

			if (resourceType.equals(currentConfig.getResourceType())
				&& (rankingCeiling < 0 || currentConfig.getRanking() < rankingCeiling)) {
				if (highestRankedConfig == null || highestRankedConfig.getRanking() < currentConfig.getRanking()) {
					highestRankedConfig = currentConfig;
				}
			}
		}

		return highestRankedConfig;
	}

}
