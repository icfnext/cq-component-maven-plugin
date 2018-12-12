package com.citytechinc.cq.component.touchuidialog.widget.pathfield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class PathFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

	private String rootPath;
	private String optionLoader;
	private String optionLoaderRoot;
	private String optionValueReader;
	private String optionTitleReader;
	private boolean forceSelection;
	private String filter;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getOptionLoader() {
		return optionLoader;
	}

	public void setOptionLoader(String optionLoader) {
		this.optionLoader = optionLoader;
	}

	public String getOptionLoaderRoot() {
		return optionLoaderRoot;
	}

	public void setOptionLoaderRoot(String optionLoaderRoot) {
		this.optionLoaderRoot = optionLoaderRoot;
	}

	public String getOptionValueReader() {
		return optionValueReader;
	}

	public void setOptionValueReader(String optionValueReader) {
		this.optionValueReader = optionValueReader;
	}

	public String getOptionTitleReader() {
		return optionTitleReader;
	}

	public void setOptionTitleReader(String optionTitleReader) {
		this.optionTitleReader = optionTitleReader;
	}

	public boolean isForceSelection() {
		return forceSelection;
	}

	public void setForceSelection(boolean forceSelection) {
		this.forceSelection = forceSelection;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public String getResourceType() {
		if(TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
			return PathFieldCoral3Widget.RESOURCE_TYPE;
		}
		return PathFieldWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for PathFieldWidget");
	}
}
