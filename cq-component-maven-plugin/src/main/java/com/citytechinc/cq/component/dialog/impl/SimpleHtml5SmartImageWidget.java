package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Html5SmartImageWidget;

public class SimpleHtml5SmartImageWidget implements Html5SmartImageWidget {
	private String name;
	private String title;
	private final boolean disableFlush;
	private final boolean disableInfo;
	private final boolean disableZoom;
	private final String cropParameter;
	private final String fileNameParameter;
	private final String fileReferenceParameter;
	private final String mapParameter;
	private final String rotateParameter;
	private final String uploadUrl;
	private final String ddGroups;
	private final boolean allowUpload;
	private final boolean required;
	private final String fieldLabel;
	private final String fieldName;
	private final String fieldDescription;
	private Integer height;
	private boolean tab;

	public SimpleHtml5SmartImageWidget(String name, String title,boolean disableFlush,
			boolean disableInfo, boolean disableZoom, String cropParameter,
			String fileNameParameter, String fileReferenceParameter,
			String mapParameter, String rotateParameter, String uploadUrl,
			String ddGroups, boolean allowUpload,boolean required,String fieldLabel,
			String fieldName,
			String fieldDescription,
			Integer height,
			boolean tab) {
		this.disableFlush = disableFlush;
		this.disableInfo = disableInfo;
		this.disableZoom = disableZoom;
		this.cropParameter = cropParameter;
		this.fileNameParameter = fileNameParameter;
		this.fileReferenceParameter = fileReferenceParameter;
		this.mapParameter = mapParameter;
		this.rotateParameter = rotateParameter;
		this.uploadUrl = uploadUrl;
		this.ddGroups = ddGroups;
		this.allowUpload = allowUpload;
		this.required=required;
		this.fieldLabel=fieldLabel;
		this.fieldName=fieldName;
		this.fieldDescription=fieldDescription;
		this.name = name;
		this.title=title;
		this.height=height;
		this.tab=tab;
	}

	public String getXtype() {
		return "html5smartimage";
	}

	public String getPrimaryType() {
		return "cq:Widget";
	}

	public String getDefaultValue() {
		return null;
	}
	
	public Map<String, String> getAdditionalProperties() {
		return null;
	}

	public String getName() {
		return name+"/file";
	}

	public String getTitle() {
		return title;
	}

	public boolean isDisableFlush() {
		return disableFlush;
	}

	public boolean isDisableInfo() {
		return disableInfo;
	}

	public boolean isDisableZoom() {
		return disableZoom;
	}

	public String getCropParameter() {
		return cropParameter;
	}

	public String getFileNameParameter() {
		if(!StringUtils.isEmpty(fileNameParameter)){
			return name+"/"+fileNameParameter;
		}
		return fileNameParameter;
	}

	public String getFileReferenceParameter() {
		if(!StringUtils.isEmpty(fileReferenceParameter)){
			return name+"/"+fileReferenceParameter;
		}
		return fileReferenceParameter;
	}

	public String getMapParameter() {
		if(!StringUtils.isEmpty(mapParameter)){
			return name+"/"+mapParameter;
		}
		return mapParameter;
	}

	public String getRotateParameter() {
		if(!StringUtils.isEmpty(rotateParameter)){
			return name+"/"+rotateParameter;
		}
		return rotateParameter;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public String getDdGroups() {
		return ddGroups;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public boolean isTab() {
		return tab;
	}
	
	public Boolean isRequired(){
		return required;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public String getLabel() {
		return fieldLabel;
	}

	public Boolean hasFieldDescription() {
		return StringUtils.isNotEmpty(fieldDescription);
	}

	public Integer getHeight() {
		return height;
	}

	public String getRequestSuffix() {
		return name.substring(1)+".img.png";
	}
	
	public List<? extends DialogElement> getContainedElements() {
		return null;
	}

	public String getNameSpace() {
		return null;
	}
}
