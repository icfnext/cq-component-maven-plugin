package com.citytechinc.cq.component.dialog;

public interface Html5SmartImageWidget extends Widget,Tab{
	public boolean isDisableFlush();
	public boolean isDisableInfo();
	public boolean isDisableZoom();
	public String getCropParameter();
	public String getFileNameParameter();
	public String getFileReferenceParameter();
	public String getMapParameter();
	public String getRotateParameter();
	public String getUploadUrl();
	public String getDdGroups();
	public boolean isTab();
	public boolean isAllowUpload();
	public int getHeight();
}
