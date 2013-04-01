package com.citytechinc.cq.component.dialog;

import java.util.List;
import java.util.Map;

public abstract class AbstractWidget extends AbstractDialogElement{
	private String xtype;
	private String fieldLabel;
	private String fieldDescription;
	private boolean allowBlank;
	private String defaultValue;
	private String name;
	
	public AbstractWidget(String xtype,String fieldLabel,String fieldDescription,boolean allowBlank,String defaultValue,String name,String primaryType, String nameSpace, String fieldName,Map<String, String> additionalProperties,List<? extends DialogElement> containedElements) {
		super(primaryType, nameSpace, fieldName, additionalProperties,containedElements);
		this.xtype=xtype;
		this.fieldLabel=fieldLabel;
		this.fieldDescription=fieldDescription;
		this.allowBlank=allowBlank;
		this.defaultValue=defaultValue;
		this.name=name;
	}

	public final String getXtype(){
		return xtype;
	}

	public final String getName(){
		return name;
	}
	public final String getFieldLabel(){
		return fieldLabel;
	}

	public final String getFieldDescription(){
		return fieldDescription;
	}

	public final boolean isAllowBlank(){
		return allowBlank;
	}
	
	public final String getDefaultValue(){
		return defaultValue;
	}

}
