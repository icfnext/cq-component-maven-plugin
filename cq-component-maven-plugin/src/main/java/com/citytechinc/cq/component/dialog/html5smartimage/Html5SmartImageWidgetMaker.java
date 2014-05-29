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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.AspectRatio;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class Html5SmartImageWidgetMaker extends AbstractWidgetMaker {
	private static final String ASPECT_RATIO_PREFIX = "aspectRatio";
	private static final String DEFAULT_CROP_PARAMETER = "imageCrop";
	private static final String DEFAULT_ROTATE_PARAMETER = "imageRotate";
	private static final String DEFAULT_MAP_PARAMETER = "imageMap";

	public Html5SmartImageWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		Html5SmartImage smartImageAnnotation = getAnnotation(Html5SmartImage.class);

		Html5SmartImageWidgetParameters parameters = new Html5SmartImageWidgetParameters();

		parameters.setName(getNameForField(smartImageAnnotation));
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());
		parameters.setAdditionalProperties(getAdditionalPropertiesForField());

		parameters.setDisableFlush(getDisableFlushForField(smartImageAnnotation));
		parameters.setDisableInfo(getDisableInfoForField(smartImageAnnotation));
		parameters.setDisableZoom(getDisableZoomForField(smartImageAnnotation));
		parameters.setCropParameter(getCropParameterForField(smartImageAnnotation));
		parameters.setFileNameParameter(getFileNameParameterForField(smartImageAnnotation));
		parameters.setFileReferenceParameter(getFileReferenceParameterForField(smartImageAnnotation));
		parameters.setMapParameter(getMapParameterForField(smartImageAnnotation));
		parameters.setRotateParameter(getRotateParameterForField(smartImageAnnotation));
		parameters.setUploadUrl(getUploadUrlForField(smartImageAnnotation));
		parameters.setDdGroups(getDdGroupsForField(smartImageAnnotation));
		parameters.setAllowUpload(getAllowUploadForField(smartImageAnnotation));
		parameters.setHeight(getHeightForField(smartImageAnnotation));
		parameters.setTab(smartImageAnnotation.tab());

		List<DialogElement> children = new ArrayList<DialogElement>();

		if (smartImageAnnotation.cropAspectRatios().length > 0) {
			children.add(buildCropConfig(smartImageAnnotation.cropAspectRatios()));
		}

		parameters.setContainedElements(children);

		return new Html5SmartImageWidget(parameters);

	}

	protected String getNameForField(Html5SmartImage smartImageAnnotation) {
		String name = smartImageAnnotation.name();

		if (StringUtils.isNotEmpty(name)) {
			return name;
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	protected String getCropParameterForField(Html5SmartImage smartImageAnnotation) {
		String cropParameter = smartImageAnnotation.cropParameter();

		if (StringUtils.isNotEmpty(cropParameter)) {
			return cropParameter;
		}

		if (smartImageAnnotation.allowCrop()) {
			return DEFAULT_CROP_PARAMETER;
		}

		return null;
	}

	protected String getFileNameParameterForField(Html5SmartImage smartImageAnnotation) {
		String fileNameParameter = smartImageAnnotation.fileNameParameter();

		if (StringUtils.isNotEmpty(fileNameParameter)) {
			return fileNameParameter;
		}

		return null;
	}

	protected String getFileReferenceParameterForField(Html5SmartImage smartImageAnnotation) {
		String fileReferenceParameter = smartImageAnnotation.fileReferenceParameter();

		if (StringUtils.isNotEmpty(fileReferenceParameter)) {
			return fileReferenceParameter;
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	protected String getMapParameterForField(Html5SmartImage smartImageAnnotation) {
		String mapParameter = smartImageAnnotation.mapParameter();

		if (StringUtils.isNotEmpty(mapParameter)) {
			return mapParameter;
		}

		if (smartImageAnnotation.allowMap()) {
			return DEFAULT_MAP_PARAMETER;
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	protected String getRotateParameterForField(Html5SmartImage smartImageAnnotation) {
		String rotateParameter = smartImageAnnotation.rotateParameter();
		if (StringUtils.isNotEmpty(rotateParameter)) {
			return rotateParameter;
		}

		if (smartImageAnnotation.allowRotate()) {
			return DEFAULT_ROTATE_PARAMETER;
		}

		return null;
	}

	protected String getUploadUrlForField(Html5SmartImage smartImageAnnotation) {
		String uploadUrl = smartImageAnnotation.uploadUrl();
		if (StringUtils.isNotEmpty(uploadUrl)) {
			return uploadUrl;
		}

		return null;
	}

	protected String getDdGroupsForField(Html5SmartImage smartImageAnnotation) {

		if (smartImageAnnotation.ddGroups().length != 0) {
			return "[" + StringUtils.join(smartImageAnnotation.ddGroups(), ",") + "]";
		}

		return null;

	}

	protected Integer getHeightForField(Html5SmartImage smartImageAnnotation) {
		Integer height = smartImageAnnotation.height();

		if (height != 0) {
			return height;
		}

		return null;
	}

	protected boolean getDisableFlushForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableFlush();
	}

	protected boolean getDisableInfoForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableInfo();
	}

	protected boolean getDisableZoomForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.disableZoom();
	}

	protected boolean getAllowUploadForField(Html5SmartImage smartImageAnnotation) {
		return smartImageAnnotation.allowUpload();
	}

	protected CropConfig buildCropConfig(AspectRatio[] cropAspectRatios) {
		List<com.citytechinc.cq.component.dialog.html5smartimage.AspectRatio> aspectRatioList = new ArrayList<com.citytechinc.cq.component.dialog.html5smartimage.AspectRatio>();
		int count = 0;
		for (AspectRatio cropAspectRatio : cropAspectRatios) {
			AspectRatioParameters arp = new AspectRatioParameters();
			arp.setText(cropAspectRatio.text());
			arp.setWidth(cropAspectRatio.width());
			arp.setHeight(cropAspectRatio.height());
			arp.setFieldName(ASPECT_RATIO_PREFIX + count++);
			com.citytechinc.cq.component.dialog.html5smartimage.AspectRatio aspectRatio = new com.citytechinc.cq.component.dialog.html5smartimage.AspectRatio(
				arp);
			aspectRatioList.add(aspectRatio);
		}

		AspectRatiosParameters arp = new AspectRatiosParameters();
		arp.setContainedElements(aspectRatioList);
		AspectRatios aspectRatios = new AspectRatios(arp);

		CropConfigParameters ccp = new CropConfigParameters();
		ccp.setContainedElements(Arrays.asList(new DialogElement[] { aspectRatios }));

		return new CropConfig(ccp);
	}
}
