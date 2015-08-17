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
import com.citytechinc.cq.component.touchuidialog.widget.fileupload.FileUploadWidget;
import org.codehaus.plexus.util.StringUtils;

@TouchUIWidget(annotationClass = Html5SmartImage.class, makerClass = SmartImageWidgetMaker.class,
	resourceType = FileUploadWidget.RESOURCE_TYPE)
public class SmartImageWidget extends FileUploadWidget {

    private final boolean isSelf;
    private final String originalName;
    private final String fileName;

	public SmartImageWidget(SmartImageWidgetParameters parameters) {
		super(parameters);

        originalName = parameters.getName();
        fileName = parameters.getFileName();
        isSelf = parameters.isSelf();
	}

	@Override
	public String getName() {
		return getNamePrefix() + getFileName();
	}

    @Override
    public String getFileNameParameter() {
        if (!StringUtils.isEmpty(super.getFileNameParameter())) {
            return getNamePrefix() + super.getFileNameParameter();
        }
        return super.getFileNameParameter();
    }

    @Override
    public String getFilereferenceparameter() {
        if (!StringUtils.isEmpty(super.getFilereferenceparameter())) {
            return getNamePrefix() + super.getFilereferenceparameter();
        }
        return super.getFilereferenceparameter();
    }

    private String getFileName() {
        if (fileName != null) {
            return fileName;
        }

        return "";
    }

    private String getNamePrefix() {
        if (StringUtils.isEmpty(originalName) || isSelf) {
            return "./";
        } else {
            return "./" + originalName + "/";
        }
    }

}
