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
package com.citytechinc.cq.component.touchuidialog.widget.numberfield;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class NumberFieldWidgetMaker extends AbstractTouchUIWidgetMaker {

    public NumberFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make() throws ClassNotFoundException {
        NumberFieldWidgetParameters widgetParameters = new NumberFieldWidgetParameters();

        widgetParameters.setFieldName(getFieldNameForField());
        widgetParameters.setName(getNameForField());
        widgetParameters.setFieldLabel(getFieldLabelForField());
        widgetParameters.setFieldDescription(getFieldDescriptionForField());
        widgetParameters.setRequired(getRequiredForField());
        widgetParameters.setDefaultValue(getDefaultValueForField());
        widgetParameters.setValue(getValueForField());
        widgetParameters.setDisabled(getDisabledForField());
        widgetParameters.setCssClass(getCssClassForField());
        
        //Number field specific stuff
        NumberField numberField = getAnnotation(NumberField.class);

        widgetParameters.setMin(getMinForField(numberField));
        widgetParameters.setMax(getMaxForField(numberField));
        widgetParameters.setStep(getStepForField(numberField));

        widgetParameters.setResourceType(NumberFieldWidget.RESOURCE_TYPE);

        return new NumberFieldWidget(widgetParameters);
    }

    protected Double getMinForField(NumberField annotation) {
        String potentialMin = annotation.min();

        if (StringUtils.isNotBlank(potentialMin)) {
            return Double.valueOf(potentialMin);
        }

        if (!annotation.allowNegative()) {
            return 0.0;
        }

        return null;
    }

    protected Double getMaxForField(NumberField annotation) {
        String potentialMax = annotation.max();

        if (StringUtils.isNotBlank(potentialMax)) {
            return Double.valueOf(potentialMax);
        }

        return null;
    }

    protected Double getStepForField(NumberField annotation) {
        Double step = annotation.step();

        if (Math.floor(annotation.step()) != annotation.step() && !Double.isInfinite(annotation.step())) {
            LogSingleton.getInstance().warn("A step of " + annotation.step() + " was defined for field " + getFieldNameForField() + " of class " + parameters.getContainingClass().getName() + ". Non-integer steps will cause the increment and decrement buttons of the number field to misbehave.");
        }
        return annotation.step();
    }

}
