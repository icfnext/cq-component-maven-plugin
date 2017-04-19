/**
 * Copyright 2013 CITYTECH, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citytechinc.cq.component.dialog.statik;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Static;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Static.class, makerClass = StaticWidgetMaker.class, xtype = StaticWidget.XTYPE)
public class StaticWidget extends AbstractWidget {
    public static final String XTYPE = "static";

    private boolean bold;
    private boolean bottommargin;
    private String href;
    private String html;
    private boolean italic;
    private boolean noWrap;
    private boolean small;
    private String tag;
    private String target;
    private String text;
    private boolean topmargin;

    public StaticWidget(final StaticWidgetParameters parameters) {
        super(parameters);
        bold = parameters.isBold();
        bottommargin = parameters.isBottommargin();
        href = parameters.getHref();
        html = parameters.getHtml();
        italic = parameters.isItalic();
        noWrap = parameters.isNoWrap();
        small = parameters.isSmall();
        tag = parameters.getTag();
        target = parameters.getTarget();
        text = parameters.getText();
        topmargin = parameters.isTopmargin();
    }

    public final boolean isBold() {
        return bold;
    }

    public final boolean isBottommargin() {
        return bottommargin;
    }

    public final String getHref() {
        return href;
    }

    public final String getHtml() {
        return html;
    }

    public final boolean isItalic() {
        return italic;
    }

    public final boolean isNoWrap() {
        return noWrap;
    }

    public final boolean isSmall() {
        return small;
    }

    public final String getTag() {
        return tag;
    }

    public final String getTarget() {
        return target;
    }

    public final String getText() {
        return text;
    }

    public final boolean isTopmargin() {
        return topmargin;
    }
}
