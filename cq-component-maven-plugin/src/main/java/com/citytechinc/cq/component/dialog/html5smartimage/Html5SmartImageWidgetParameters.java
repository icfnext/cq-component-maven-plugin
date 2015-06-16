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
package com.citytechinc.cq.component.dialog.html5smartimage;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class Html5SmartImageWidgetParameters extends DefaultWidgetParameters {
	private String originalName;
	private boolean disableFlush;
	private boolean disableInfo;
	private boolean disableZoom;
	private String cropParameter;
	private String fileNameParameter;
	private String fileReferenceParameter;
	private String mapParameter;
	private String rotateParameter;
	private String uploadUrl;
	private String ddGroups;
	private boolean allowUpload;
	private Integer height;
	private boolean tab;

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public boolean isDisableFlush() {
		return disableFlush;
	}

	public void setDisableFlush(boolean disableFlush) {
		this.disableFlush = disableFlush;
	}

	public boolean isDisableInfo() {
		return disableInfo;
	}

	public void setDisableInfo(boolean disableInfo) {
		this.disableInfo = disableInfo;
	}

	public boolean isDisableZoom() {
		return disableZoom;
	}

	public void setDisableZoom(boolean disableZoom) {
		this.disableZoom = disableZoom;
	}

	public String getCropParameter() {
		return cropParameter;
	}

	public void setCropParameter(String cropParameter) {
		this.cropParameter = cropParameter;
	}

	public String getFileNameParameter() {
		return fileNameParameter;
	}

	public void setFileNameParameter(String fileNameParameter) {
		this.fileNameParameter = fileNameParameter;
	}

	public String getFileReferenceParameter() {
		return fileReferenceParameter;
	}

	public void setFileReferenceParameter(String fileReferenceParameter) {
		this.fileReferenceParameter = fileReferenceParameter;
	}

	public String getMapParameter() {
		return mapParameter;
	}

	public void setMapParameter(String mapParameter) {
		this.mapParameter = mapParameter;
	}

	public String getRotateParameter() {
		return rotateParameter;
	}

	public void setRotateParameter(String rotateParameter) {
		this.rotateParameter = rotateParameter;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getDdGroups() {
		return ddGroups;
	}

	public void setDdGroups(String ddGroups) {
		this.ddGroups = ddGroups;
	}

	public boolean isAllowUpload() {
		return allowUpload;
	}

	public void setAllowUpload(boolean allowUpload) {
		this.allowUpload = allowUpload;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public boolean isTab() {
		return tab;
	}

	public void setTab(boolean tab) {
		this.tab = tab;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for Html5SmartImageWidget");
	}

	@Override
	public String getXtype() {
		return Html5SmartImageWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for Html5SmartImageWidget");
	}
}
