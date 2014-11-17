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
package com.citytechinc.cq.component.touchuidialog.widget;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.util.Constants;
import org.codehaus.plexus.util.StringUtils;

public class TouchUIWidgetParameters extends TouchUIDialogElementParameters {

    protected String name;
    protected String fieldLabel;

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryType() {
        if (StringUtils.isBlank(super.getPrimaryType())) {
            return Constants.NT_UNSTRUCTURED;
        }

        return super.getPrimaryType();
    }
}
