package com.citytechinc.cq.component.dialog.html5smartimage;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.TabbableDialogElement;

@Widget(annotationClass = Html5SmartImage.class, makerClass = Html5SmartImageWidgetMaker.class, xtype = Html5SmartImageWidget.XTYPE)
public class Html5SmartImageWidget extends AbstractWidget implements TabbableDialogElement {
	public static final String XTYPE = "html5smartimage";
	private final String originalName;
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
	private final Integer height;
	private final boolean tab;
	private final String title;

	public Html5SmartImageWidget(Html5SmartImageWidgetParameters parameters) {
		super(parameters);
		originalName = parameters.getName();
		this.disableFlush = parameters.isDisableFlush();
		this.disableInfo = parameters.isDisableInfo();
		this.disableZoom = parameters.isDisableZoom();
		this.cropParameter = parameters.getCropParameter();
		this.fileNameParameter = parameters.getFileNameParameter();
		this.fileReferenceParameter = parameters.getFileReferenceParameter();
		this.mapParameter = parameters.getMapParameter();
		this.rotateParameter = parameters.getRotateParameter();
		this.uploadUrl = parameters.getUploadUrl();
		this.ddGroups = parameters.getDdGroups();
		this.allowUpload = parameters.isAllowUpload();
		this.height = parameters.getHeight();
		this.tab = parameters.isTab();

		if (this.tab) {
		    this.title = this.getFieldLabel();
		}
		else {
		    this.title = null;
		}
	}

	private static String getNameAsPrefix(String name) {
		if (StringUtils.isEmpty(name)) {
			return "./";
		} else {
			return "./" + name + "/";
		}
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
		if (!StringUtils.isEmpty(fileNameParameter)) {
			return getNameAsPrefix(originalName) + fileNameParameter;
		}
		return fileNameParameter;
	}

	public String getFileReferenceParameter() {
		if (!StringUtils.isEmpty(fileReferenceParameter)) {
			return getNameAsPrefix(originalName) + fileReferenceParameter;
		}
		return fileReferenceParameter;
	}

	public String getMapParameter() {
		if (!StringUtils.isEmpty(mapParameter)) {
			return getNameAsPrefix(originalName) + mapParameter;
		}
		return mapParameter;
	}

	public String getRotateParameter() {
		if (!StringUtils.isEmpty(rotateParameter)) {
			return getNameAsPrefix(originalName) + rotateParameter;
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

	public Boolean isTab() {
		return tab;
	}

	public String getLabel() {
		return getFieldLabel();
	}

	public Integer getHeight() {
		return height;
	}

	public String getRequestSuffix() {
		if (StringUtils.isEmpty(originalName)) {
			return ".img.png";
		} else {
			return "/" + originalName + ".img.png";
		}
	}
}
