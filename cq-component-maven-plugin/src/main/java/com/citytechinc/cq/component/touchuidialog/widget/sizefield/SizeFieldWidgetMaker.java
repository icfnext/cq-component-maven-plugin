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
package com.citytechinc.cq.component.touchuidialog.widget.sizefield;

import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.numberfield.NumberFieldWidget;
import com.citytechinc.cq.component.touchuidialog.widget.numberfield.NumberFieldWidgetParameters;

public class SizeFieldWidgetMaker extends AbstractTouchUIWidgetMaker<SizeFieldWidgetParameters> {

    public SizeFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected TouchUIDialogElement make(SizeFieldWidgetParameters parameters) throws ClassNotFoundException, InvalidComponentFieldException, TouchUIDialogGenerationException, IllegalAccessException, InstantiationException {

        SizeField sizeField = getAnnotation(SizeField.class);

        NumberFieldWidgetParameters heightParameters = new NumberFieldWidgetParameters();
        NumberFieldWidgetParameters widthParameters = new NumberFieldWidgetParameters();

        if (this.parameters.isUseDotSlashInName()) {
            if (sizeField.heightParameter().startsWith("./")) {
                heightParameters.setName(sizeField.heightParameter());
            }
            else {
                heightParameters.setName("./" + sizeField.heightParameter());
            }

            if (sizeField.widthParameter().startsWith("./")) {
                widthParameters.setName(sizeField.widthParameter());
            }
            else {
                widthParameters.setName("./" + sizeField.widthParameter());
            }
        }
        else {
            if (sizeField.heightParameter().startsWith("./")) {
                heightParameters.setName(sizeField.heightParameter().substring(2));
            }
            else {
                heightParameters.setName(sizeField.heightParameter());
            }

            if (sizeField.widthParameter().startsWith("./")) {
                widthParameters.setName(sizeField.widthParameter().substring(2));
            }
            else {
                widthParameters.setName(sizeField.widthParameter());
            }
        }

        parameters.setHeight(new NumberFieldWidget(heightParameters));
        parameters.setWidth(new NumberFieldWidget(widthParameters));

        return new SizeFieldWidget(parameters);
    }

}
