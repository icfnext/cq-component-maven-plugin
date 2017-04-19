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

import com.citytechinc.cq.component.annotations.widgets.Static;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.google.common.base.Strings;

public class StaticWidgetMaker extends AbstractWidgetMaker<StaticWidgetParameters> {
    public StaticWidgetMaker(final WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public final DialogElement make(final StaticWidgetParameters parameters) throws ClassNotFoundException {
        final Static staticAnnotation = getAnnotation(Static.class);

        parameters.setBold(staticAnnotation.bold());
        parameters.setBottommargin(staticAnnotation.bottommargin());
        parameters.setHref(Strings.emptyToNull(staticAnnotation.href()));
        parameters.setHtml(Strings.emptyToNull(staticAnnotation.html()));
        parameters.setItalic(staticAnnotation.italic());
        parameters.setNoWrap(staticAnnotation.noWrap());
        parameters.setSmall(staticAnnotation.small());
        parameters.setTag(Strings.emptyToNull(staticAnnotation.tag()));
        parameters.setTarget(Strings.emptyToNull(staticAnnotation.target()));
        parameters.setText(Strings.emptyToNull(staticAnnotation.text()));
        parameters.setTopmargin(staticAnnotation.topmargin());

        return new StaticWidget(parameters);
    }
}
