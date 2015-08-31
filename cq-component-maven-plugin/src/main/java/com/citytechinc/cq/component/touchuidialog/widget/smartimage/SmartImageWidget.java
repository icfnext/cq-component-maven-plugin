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
package com.citytechinc.cq.component.touchuidialog.widget.smartimage;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.Html5SmartImage;
import com.citytechinc.cq.component.dialog.html5smartimage.Html5SmartImageWidget;
import com.citytechinc.cq.component.touchuidialog.widget.fileupload.FileUploadWidget;

@TouchUIWidget(annotationClass = Html5SmartImage.class, makerClass = SmartImageWidgetMaker.class,
	resourceType = FileUploadWidget.RESOURCE_TYPE)
public class SmartImageWidget extends FileUploadWidget {

	public SmartImageWidget(SmartImageWidgetParameters parameters) {
		super(parameters);
	}

    private static String getNameAsPrefix(String name) {
        if (!name.endsWith("/")) {
            return name + "/";
        }
        return name;
    }

    @Override
	public String getName() {
        return getNameAsPrefix(super.getName()) + Html5SmartImageWidget.NAME_SUFFIX;
    }

    @Override
    public String getFilereferenceparameter() {
        return getNameAsPrefix(super.getName()) + super.getFilereferenceparameter();
    }

    @Override
    public String getFileNameParameter() {
        return getNameAsPrefix(super.getName()) + super.getFileNameParameter();
    }
}
