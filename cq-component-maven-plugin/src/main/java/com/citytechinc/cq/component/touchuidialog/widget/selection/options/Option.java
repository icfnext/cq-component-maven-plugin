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
package com.citytechinc.cq.component.touchuidialog.widget.selection.options;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;

import java.util.List;

public class Option extends AbstractTouchUIDialogElement {

    private final String text;
    private final String value;
    private final boolean selected;
    private DataSource dataSource;
    private List<Option> options;

    public Option(OptionParameters parameters) {
        super(parameters);

        text = parameters.getText();
        value = parameters.getValue();
        selected = parameters.isSelected();
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelected() {
        return selected;
    }

}
