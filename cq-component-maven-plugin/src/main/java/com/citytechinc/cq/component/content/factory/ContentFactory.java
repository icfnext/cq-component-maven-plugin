/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.content.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.CtClass;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.ContentParameters;
import com.citytechinc.cq.component.content.htmltag.HtmlTag;
import com.citytechinc.cq.component.content.htmltag.HtmlTagParameters;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import com.citytechinc.cq.component.xml.XmlElement;
import com.google.common.collect.Lists;

public class ContentFactory {

	private ContentFactory() {
	}

	public static Content make(CtClass componentClass, String defaultGroup) throws InvalidComponentClassException,
		ClassNotFoundException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation == null) {
			throw new InvalidComponentClassException();
		}

		ContentParameters parameters = new ContentParameters();

		parameters.setAllowedChildren(getAllowedChildrenForComponent(componentAnnotation));
		parameters.setAllowedParents(getAllowedParentsForComponent(componentAnnotation));
		parameters.setComponentGroup(getGroupForComponent(componentClass, componentAnnotation, defaultGroup));
		parameters.setCellName(getCellNameForComponent(componentAnnotation));
		parameters.setIsContainer(getIsContainerForComponent(componentClass, componentAnnotation));
		parameters.setNoDecoration(getNoDecorationForComponent(componentAnnotation));
		parameters.setTemplatePath(getTemplatePathForComponent(componentAnnotation));
		parameters.setDialogPath(getDialogPathForComponent(componentAnnotation));
		parameters.setCreated(getCreatedForComponent(componentAnnotation));
		parameters.setDescription(getDescriptionForComponent(componentAnnotation));
		parameters.setTitle(getTitleForComponent(componentClass, componentAnnotation));
		parameters.setResourceSuperType(getResourceSuperTypeForComponent(componentAnnotation));
		parameters.setAdditionalProperties(getAdditionalPropertiesForComponent(componentAnnotation));
		parameters.setClassName(componentClass.getName());

		if (componentAnnotation.htmlTag().length > 0) {
			List<XmlElement> containedElements = Lists.newArrayList();

			containedElements.add(getHtmlTagForComponent(componentAnnotation));

			parameters.setContainedElements(containedElements);
		}

		return new Content(parameters);
	}

	private static Map<String, ?> getAdditionalPropertiesForComponent(Component componentAnnotation) {
		if (componentAnnotation.contentAdditionalProperties().length > 0) {
			Map<String, Object> additionalProperties = new HashMap<String, Object>();
			for (ContentProperty contentProp : componentAnnotation.contentAdditionalProperties()) {
				if (contentProp.namespace().isEmpty()) {
					additionalProperties.put(contentProp.name(), contentProp.value());
				} else {
					NameSpacedAttribute<String> nsa =
						new NameSpacedAttribute<String>(contentProp.namespace(), null, contentProp.value());
					additionalProperties.put(contentProp.name(), nsa);
				}
			}
			return additionalProperties;
		}
		return null;
	}

	private static String getDescriptionForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.description())) {
			return componentAnnotation.description();
		}
		return null;
	}

	private static String getCreatedForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.created())) {
			return componentAnnotation.created();
		}
		return null;
	}

	private static String getResourceSuperTypeForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.resourceSuperType())) {
			return componentAnnotation.resourceSuperType();
		}
		return null;
	}

	private static String getDialogPathForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.dialogPath())) {
			return componentAnnotation.dialogPath();
		}
		return null;
	}

	private static String getTemplatePathForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.templatePath())) {
			return componentAnnotation.templatePath();
		}
		return null;
	}

	private static Boolean getNoDecorationForComponent(Component componentAnnotation) {
		return componentAnnotation.noDecoration();
	}

	private static String getCellNameForComponent(Component componentAnnotation) {
		if (StringUtils.isNotEmpty(componentAnnotation.cellName())) {
			return componentAnnotation.cellName();
		}
		return null;
	}

	private static List<String> getAllowedParentsForComponent(Component componentAnnotation) {
		if (componentAnnotation.allowedParents().length > 0) {
			return Arrays.asList(componentAnnotation.allowedParents());
		}
		return null;
	}

	private static List<String> getAllowedChildrenForComponent(Component componentAnnotation) {
		if (componentAnnotation.allowedChildren().length > 0) {
			return Arrays.asList(componentAnnotation.allowedChildren());
		}
		return null;
	}

	private static Boolean getIsContainerForComponent(CtClass componentClass, Component componentAnnotation) {
		return componentAnnotation.isContainer();
	}

	private static String getTitleForComponent(CtClass componentClass, Component componentAnnotation) {
		String overrideTitle = componentAnnotation.value();

		if (StringUtils.isNotEmpty(overrideTitle)) {
			return overrideTitle;
		}

		return componentClass.getSimpleName();
	}

	private static String getGroupForComponent(CtClass componentClass, Component componentAnnotation,
		String defaultGroup) {
		String overrideGroup = componentAnnotation.group();

		if (StringUtils.isNotEmpty(overrideGroup)) {
			return overrideGroup;
		}

		return defaultGroup;
	}

	private static HtmlTag getHtmlTagForComponent(Component componentAnnotation) {
		HtmlTagParameters htmlTagParameters = new HtmlTagParameters();
		com.citytechinc.cq.component.annotations.HtmlTag htmlTag = componentAnnotation.htmlTag()[0];

		htmlTagParameters.setTagName(htmlTag.tagName());

		if (StringUtils.isNotBlank(htmlTag.cssClass())) {
			htmlTagParameters.setCssClass(htmlTag.cssClass());
		}
		if (StringUtils.isNotBlank(htmlTag.id())) {
			htmlTagParameters.setId(htmlTag.id());
		}

		return new HtmlTag(htmlTagParameters);
	}
}
