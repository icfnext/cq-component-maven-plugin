package com.citytechinc.cq.component.dialog.maker;

import java.util.HashMap;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.FieldProperty;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;

public abstract class AbstractWidgetMaker implements WidgetMaker {

	/*
	 * This default implementation simply defaults the useDotSlashInName parameter of the
	 * like method.
	 *
	 * (non-Javadoc)
	 * @see com.citytechinc.cq.component.dialog.maker.WidgetMaker#make(com.citytechinc.cq.component.dialog.field.DialogFieldMember, java.lang.String)
	 */
	public DialogElement make(DialogFieldMember field, String xtype) throws ClassNotFoundException, InvalidComponentFieldException, NotFoundException, SecurityException, CannotCompileException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		return this.make(field, xtype, true);
	}

	protected String getNameForField(DialogFieldMember field, boolean useDotSlashInName) {
		String overrideName = field.getAnnotation().name();

		if (StringUtils.isNotEmpty(overrideName)) {
			return overrideName;
		}
		if (useDotSlashInName) {
			return "./" + field.getName();
		}
		return field.getName();
	}

	protected String getFieldNameForField(DialogFieldMember field) {
		String overrideFieldName = field.getAnnotation().fieldName();

		if (StringUtils.isNotEmpty(overrideFieldName)) {
			return overrideFieldName;
		}

		return field.getName();
	}

	protected String getFieldLabelForField(DialogFieldMember field) {
		String overrideLabel = field.getAnnotation().fieldLabel();

		if (StringUtils.isNotEmpty(overrideLabel)) {
			return overrideLabel;
		}

		return null;
	}

	protected String getFieldDescriptionForField(DialogFieldMember field) {
		String overrideFieldDescription = field.getAnnotation().fieldDescription();

		if (StringUtils.isNotEmpty(overrideFieldDescription)) {
			return overrideFieldDescription;
		}

		return null;
	}

	protected Boolean getIsRequiredForField(DialogFieldMember field) {
		return field.getAnnotation().required();
	}

	protected Map<String, String> getAdditionalPropertiesForField(DialogFieldMember field) {
		if (field.getAnnotation().additionalProperties().length > 0) {
			Map<String, String> properties = new HashMap<String, String>();

			for (FieldProperty curProperty : field.getAnnotation().additionalProperties()) {
				properties.put(curProperty.name(), curProperty.value());
			}

			return properties;
		}

		return null;
	}

	protected String getDefaultValueForField(DialogFieldMember field) {
		String defaultValue = field.getAnnotation().defaultValue();

		if (StringUtils.isNotEmpty(defaultValue)) {
			return defaultValue;
		}

		return null;
	}

	protected boolean getHideLabelForField(DialogFieldMember field) {
		return field.getAnnotation().hideLabel();
	}

	//TODO: figure out how this is being used
	protected void setListeners(AbstractWidget widget,Listener[] listeners){
		if(listeners.length>0){
			widget.setListeners(new Listeners(listeners));
		}
	}
}
