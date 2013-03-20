package com.citytechinc.cq.component.content.factory;

import javassist.CtClass;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.content.Content;
import com.citytechinc.cq.component.content.impl.ContentImpl;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentClassException;

public class ContentFactory {

	public static final Content make (CtClass componentClass, String defaultGroup) throws InvalidComponentClassException, ClassNotFoundException {

		Component componentAnnotation = (Component) componentClass.getAnnotation(Component.class);

		if (componentAnnotation == null) {
			throw new InvalidComponentClassException();
		}

		Boolean isContainer = getIsContainerForComponent(componentClass, componentAnnotation);
		String title = getTitleForComponent(componentClass, componentAnnotation);
		String group = getGroupForComponent(componentClass, componentAnnotation, defaultGroup);

		return new ContentImpl(title, group, isContainer);
	}

	private static final Boolean getIsContainerForComponent(CtClass componentClass, Component componentAnnotation) {
		return componentAnnotation.isContainer();
	}

	private static final String getTitleForComponent(CtClass componentClass, Component componentAnnotation) {
		String overrideTitle = componentAnnotation.title();

		if (StringUtils.isNotEmpty(overrideTitle)) {
			return overrideTitle;
		}

		return componentClass.getSimpleName();
	}

	private static final String getGroupForComponent(CtClass componentClass, Component componentAnnotation, String defaultGroup) {
		String overrideGroup = componentAnnotation.group();

		if (StringUtils.isNotEmpty(overrideGroup)) {
			return overrideGroup;
		}

		return defaultGroup;
	}
}
