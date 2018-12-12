package com.citytechinc.cq.component.touchuidialog.widget.smartimage;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.touchuidialog.widget.fileupload.FileUploadWidget;

@TouchUIWidget(annotationClass = Html5SmartImage.class, makerClass = SmartImageWidgetMaker.class,
	resourceType = FileUploadWidget.RESOURCE_TYPE)
public class SmartImageWidget extends FileUploadWidget {

	public SmartImageWidget(SmartImageWidgetParameters parameters) {
		super(parameters);
	}

}
