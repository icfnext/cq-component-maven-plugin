package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.Namespace;
import com.citytechinc.cq.component.dialog.impl.TagInputFieldWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class TagInputFieldWidgetMaker extends AbstractWidgetMaker{

	@Override
	public DialogElement make(String xtype, Field widgetField,
			CtField ctWidgetField, Class<?> containingClass,
			CtClass ctContainingClass, Map<Class<?>, String> xtypeMap,Map<String, WidgetMaker> xTypeToWidgetMakerMap,ClassLoader classLoader, ClassPool classPool,boolean useDotSlashInName)
			throws ClassNotFoundException, InvalidComponentFieldException,
			CannotCompileException, NotFoundException {
	
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		TagInputField tagAnnotation = (TagInputField) ctWidgetField.getAnnotation(TagInputField.class);
		
		boolean displayTitles=tagAnnotation.displayTitles();
		List<DialogElement> widgetCollectionHolder=null;
		if(tagAnnotation.namespaces().length>0){
			List<DialogElement> namespaces=new ArrayList<DialogElement>();
			for(TagNameSpace ns:tagAnnotation.namespaces()){
				String max=null;
				if(ns.maximum()>-1){
					max=Integer.toString(ns.maximum());
				}
				Namespace n=new Namespace(ns.value(), max);
				namespaces.add(n);
			}
			widgetCollectionHolder=Arrays.asList(new DialogElement[]{new WidgetCollection(namespaces,"namespaces")});
		}
		
		String name = getNameForField(dialogFieldAnnotation, widgetField,useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel=dialogFieldAnnotation.hideLabel();
		
		return new TagInputFieldWidget(displayTitles,fieldLabel, fieldDescription, !isRequired, hideLabel,defaultValue, name, fieldName, additionalProperties,widgetCollectionHolder);
	}

}
