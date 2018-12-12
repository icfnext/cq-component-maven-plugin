package com.citytechinc.cq.component.touchuidialog.widget.numberfield;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class NumberFieldWidgetMaker extends AbstractTouchUIWidgetMaker<NumberFieldWidgetParameters> {

    public NumberFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(NumberFieldWidgetParameters widgetParameters) throws ClassNotFoundException {
        // Number field specific stuff
        NumberField numberField = getAnnotation(NumberField.class);

        widgetParameters.setMin(getMinForField(numberField));
        widgetParameters.setMax(getMaxForField(numberField));
        widgetParameters.setStep(getStepForField(numberField));
        widgetParameters.setTypeHint(getTypeHintForField(numberField));
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new NumberFieldCoral3Widget(widgetParameters);
        }
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

        if (Math.floor(step) != step && !Double.isInfinite(step)) {
            LogSingleton
                .getInstance()
                .warn(
                    "A step of "
                        + annotation.step()
                        + " was defined for field "
                        + getFieldNameForField()
                        + " of class "
                        + parameters.getContainingClass().getName()
                        + ". Non-integer steps will cause the increment and decrement buttons of the number field to misbehave.");
        }
        return step;
    }

    protected String getTypeHintForField(NumberField annotation) {
        if (StringUtils.isNotBlank(annotation.typeHint())) {
            return annotation.typeHint();
        }

        return null;
    }
}
