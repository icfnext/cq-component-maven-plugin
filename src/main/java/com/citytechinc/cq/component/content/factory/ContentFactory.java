package com.citytechinc.cq.component.content.factory;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.impl.ContentImpl;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;

public class ContentFactory {

	public static final Content make (Class<?> componentClass, String defaultGroup) throws InvalidComponentClassException {

		Component componentAnnotation = componentClass.getAnnotation(Component.class);

		if (!(componentAnnotation instanceof Component)) {
			throw new InvalidComponentClassException();
		}

		Boolean isContainer = getIsContainerForComponent(componentClass, componentAnnotation);
		String title = getTitleForComponent(componentClass, componentAnnotation);
		String group = getGroupForComponent(componentClass, componentAnnotation, defaultGroup);

		return new ContentImpl(title, group, isContainer);
	}

	private static final Boolean getIsContainerForComponent(Class<?> componentClass, Component componentAnnotation) {
		String overrideIsContainer = componentAnnotation.isContainer();

		return !overrideIsContainer.equals("false");
	}

	private static final String getTitleForComponent(Class<?> componentClass, Component componentAnnotation) {
		String overrideTitle = componentAnnotation.title();

		if (StringUtils.isNotEmpty(overrideTitle)) {
			return overrideTitle;
		}

		return componentClass.getSimpleName();
	}

	private static final String getGroupForComponent(Class<?> componentClass, Component componentAnnotation, String defaultGroup) {
		String overrideGroup = componentAnnotation.group();

		if (StringUtils.isNotEmpty(overrideGroup)) {
			return overrideGroup;
		}

		return defaultGroup;
	}
}
