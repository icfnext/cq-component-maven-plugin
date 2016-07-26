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
package com.citytechinc.cq.component.util;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMaker;

public class TouchUIWidgetConfigHolder {

	private final Class<?> annotationClass;
	private final Class<? extends TouchUIDialogElement> widgetClass;
	private final Class<? extends TouchUIWidgetMaker> makerClass;
	private final String resourceType;
	private final int ranking;
	private final String featureFlag;

	public TouchUIWidgetConfigHolder(Class<?> annotationClass, Class<? extends TouchUIDialogElement> widgetClass,
		Class<? extends TouchUIWidgetMaker> makerClass, String resourceType, int ranking, String featureFlag) {
		this.annotationClass = annotationClass;
		this.widgetClass = widgetClass;
		this.makerClass = makerClass;
		this.resourceType = resourceType;
		this.ranking = ranking;
		this.featureFlag = featureFlag;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}

	public Class<? extends TouchUIDialogElement> getWidgetClass() {
		return widgetClass;
	}

	public Class<? extends TouchUIWidgetMaker> getMakerClass() {
		return makerClass;
	}

	public String getResourceType() {
		return resourceType;
	}

	public int getRanking() {
		return ranking;
	}

	public String getFeatureFlag() {
		return featureFlag;
	}

}
