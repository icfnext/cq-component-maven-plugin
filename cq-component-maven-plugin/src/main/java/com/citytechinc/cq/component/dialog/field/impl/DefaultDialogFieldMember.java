package com.citytechinc.cq.component.dialog.field.impl;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMember;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.widget.WidgetRegistry;
import com.citytechinc.cq.component.maven.util.ComponentMojoUtil;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class DefaultDialogFieldMember implements DialogFieldMember {

	private final DialogField annotation;
	private final CtMember ctMember;
	private final AccessibleObject member;
	private final Class<?> containingClass;
	private final boolean isField;
	private final ClassLoader classLoader;
	private final ClassPool classPool;
	private final Class<?> type;
	private final ParameterizedType parameterizedType;
	private final String name;
	private final CtClass ctType;
	private final WidgetRegistry widgetRegistry;

	public DefaultDialogFieldMember(DialogField annotation, CtMember ctMember, Class<?> containingClass,
		ClassLoader classLoader, ClassPool classPool, WidgetRegistry widgetRegistry)
		throws InvalidComponentFieldException, NotFoundException {

		this.annotation = annotation;
		this.ctMember = ctMember;
		this.containingClass = containingClass;
		this.classLoader = classLoader;
		this.classPool = classPool;
		this.widgetRegistry = widgetRegistry;

		if (this.ctMember instanceof CtField) {
			this.member = ComponentMojoUtil.getField(this.containingClass, this.ctMember.getName());
			Field memberField = (Field) this.member;
			this.type = memberField.getType();
			Type tempType = memberField.getGenericType();

			if (tempType instanceof ParameterizedType) {
				this.parameterizedType = (ParameterizedType) tempType;
			} else {
				this.parameterizedType = null;
			}

			this.isField = true;
		} else {
			this.member = ComponentMojoUtil.getMethod(this.containingClass, this.ctMember.getName());
			Method memberMethod = (Method) this.member;
			this.type = memberMethod.getReturnType();
			Type tempType = memberMethod.getGenericReturnType();

			if (tempType instanceof ParameterizedType) {
				this.parameterizedType = (ParameterizedType) tempType;
			} else {
				this.parameterizedType = null;
			}

			this.isField = false;
		}

		this.ctType = this.classPool.getCtClass(this.type.getName());

		/*
		 * Only Fields and Methods may represent dialog elements
		 */
		if (!(this.member instanceof Field) && !(this.member instanceof Method)) {
			throw new InvalidComponentFieldException("Only methods and fields can be annotated");
		}

		if (this.isField) {
			this.name = ctMember.getName();
		} else {
			String tempName = ctMember.getName();
			if (tempName.startsWith("is")) {
				this.name = StringUtils.uncapitalise(tempName.substring(2));
			} else if (tempName.startsWith("get")) {
				this.name = StringUtils.uncapitalise(tempName.substring(3));
			} else {
				this.name = StringUtils.uncapitalise(tempName);
			}
		}

	}

	public DialogField getAnnotation() {
		return this.annotation;
	}

	public CtMember getCtMember() {
		return this.ctMember;
	}

	public AccessibleObject getMember() {
		return this.member;
	}

	public Class<?> getContainingClass() {
		return this.containingClass;
	}

	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	public ClassPool getClassPool() {
		return this.classPool;
	}

	public WidgetRegistry getWidgetRegistry() {
		return this.widgetRegistry;
	}

	public boolean isField() {
		return isField;
	}

	public boolean isMethod() {
		return !isField;
	}

	public WidgetConfigHolder getWidgetConfig() throws ClassNotFoundException {
		return this.getWidgetConfig(null);
	}

	public WidgetConfigHolder getWidgetConfig(Integer rankCeiling) throws ClassNotFoundException {

		LogSingleton LOG = LogSingleton.getInstance();

		LOG.debug("Looking up Widget Configuration for " + this.name + " - Rank Ceiling " + rankCeiling.toString());

		WidgetConfigHolder highestRankedWidget = null;

		Set<Class<?>> registeredAnnotations = this.widgetRegistry.getRegisteredAnnotations();

		for (Class<?> curRegisteredAnnotation : registeredAnnotations) {
			LOG.debug("Checking for known annotation " + curRegisteredAnnotation);
			if (this.getCtMember().hasAnnotation(curRegisteredAnnotation)) {
				LOG.debug("Found Annotation " + curRegisteredAnnotation + " on " + this.name);
				WidgetConfigHolder curPotential = this.widgetRegistry.getWidgetForAnnotation(curRegisteredAnnotation);
				if (rankCeiling == null || rankCeiling < 0 || curPotential.getRanking() <= rankCeiling) {
					LOG.debug("Match found in the registry with ranking " + curPotential.getRanking());
					if (highestRankedWidget == null || curPotential.getRanking() > highestRankedWidget.getRanking()) {
						highestRankedWidget = curPotential;
					}
				}
			}
		}

		if (highestRankedWidget != null) {
			return highestRankedWidget;
		}

		return null;

	}

	public String getName() {
		return this.name;
	}

	public Class<?> getType() {
		return this.type;
	}

	public CtClass getCtType() {
		return this.ctType;
	}

	public ParameterizedType getParameterizedType() {
		return this.parameterizedType;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAnnotation(Class<T> annotationClass) throws ClassNotFoundException {
		if (this.ctMember.hasAnnotation(annotationClass)) {
			return (T) this.ctMember.getAnnotation(annotationClass);
		}
		return null;
	}

	public boolean hasAnnotation(Class<?> annotationClass) {
		return this.ctMember.hasAnnotation(annotationClass);
	}

}
