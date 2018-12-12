package com.citytechinc.cq.component.touchuidialog.widget.factory;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.hidedialogfield.HideDialogFieldWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.DefaultTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.util.TouchUIWidgetConfigHolder;
import org.codehaus.plexus.util.StringUtils;

import javax.annotation.Nullable;

public class TouchUIWidgetFactory {

    public static TouchUIDialogElement make(TouchUIWidgetMakerParameters widgetMakerParameters, int rankingCeiling)
        throws InvalidComponentFieldException, TouchUIDialogGenerationException {
        TouchUIWidgetMakerContext context = getWidgetMakerForMemberParameters(widgetMakerParameters, rankingCeiling);

        if (context != null) {
            widgetMakerParameters.setResourceType(context.getResourceType());

            try {
                TouchUIWidgetMaker widgetMaker =
                    context.getMakerClass().getConstructor(TouchUIWidgetMakerParameters.class)
                        .newInstance(widgetMakerParameters);
                return widgetMaker.make();
            } catch (Exception e) {
                throw new TouchUIDialogGenerationException("Exception encountered generating widget for "
                    + widgetMakerParameters.getCtMember().getName() + " of "
                    + widgetMakerParameters.getClass().getName(), e);
            }
        }
        return null;
    }

    public static TouchUIWidgetMakerContext getWidgetMakerForMemberParameters(TouchUIWidgetMakerParameters parameters,
        int rankingCeiling) throws InvalidComponentFieldException {
        TouchUIWidgetConfigHolder widgetConfig = getWidgetConfigForParameters(parameters, rankingCeiling);

        // If the widget is intended to be hidden by an inheriting component dialog
        // use the HideDialogFieldWidgetMaker
        if (widgetConfig != null && parameters.getDialogFieldConfig().isHideDialogField()) {
            return new TouchUIWidgetMakerContext(HideDialogFieldWidgetMaker.class, "");
        }

        // If we were able to lookup a widget configuration with a valid maker
        // class return it
        if (widgetConfig != null && widgetConfig.getMakerClass() != null) {
            return new TouchUIWidgetMakerContext(widgetConfig.getMakerClass(), widgetConfig.getResourceType());
        }

        // If a widget configuration was found but no maker class was associated
        // with it, use the default class
        if (widgetConfig != null && widgetConfig.getResourceType() != null) {
            return new TouchUIWidgetMakerContext(DefaultTouchUIWidgetMaker.class, widgetConfig.getResourceType());
        }

        String resourceType = parameters.getDialogFieldConfig().getResourceType();

        if (StringUtils.isNotEmpty(resourceType)) {
            TouchUIWidgetConfigHolder widget = getWidgetConfigForResourceType(resourceType, parameters, rankingCeiling);
            if (widget != null && widget.getMakerClass() != null) {
                return new TouchUIWidgetMakerContext(widget.getMakerClass(), widget.getResourceType());
            }
            return new TouchUIWidgetMakerContext(DefaultTouchUIWidgetMaker.class, resourceType);
        }

        return null;
    }

    /**
     * Looks up the highest ranked TouchUIWidget based annotation tied to the
     * member in question and returns the configuration associated with the
     * annotation if found.
     *
     * @param parameters The parameters identifying the Component member being
     *            processed
     * @param rankingCeiling Widget configurations with rankings higher than the
     *            ceiling will not be considered
     * @return The determined widget configuration or null
     */
    @Nullable
    private static TouchUIWidgetConfigHolder getWidgetConfigForParameters(TouchUIWidgetMakerParameters parameters,
        int rankingCeiling) {
        TouchUIWidgetConfigHolder highestRankedConfig = null;

        for (Class<?> currentWidgetAnnotation : parameters.getWidgetRegistry().getRegisteredAnnotations()) {
            if (parameters.getCtMember().hasAnnotation(currentWidgetAnnotation)) {
                TouchUIWidgetConfigHolder potentialWidgetConfig =
                    parameters.getWidgetRegistry().getWidgetForAnnotation(currentWidgetAnnotation);
                if (rankingCeiling < 0 || potentialWidgetConfig.getRanking() < rankingCeiling) {
                    if (highestRankedConfig == null
                        || highestRankedConfig.getRanking() < potentialWidgetConfig.getRanking()) {
                        highestRankedConfig = potentialWidgetConfig;
                    }
                }
            }
        }

        return highestRankedConfig;
    }

    @Nullable
    private static TouchUIWidgetConfigHolder getWidgetConfigForResourceType(String resourceType,
        TouchUIWidgetMakerParameters parameters, int rankingCeiling) {
        TouchUIWidgetConfigHolder highestRankedConfig = null;

        for (Class<?> currentWidgetAnnotation : parameters.getWidgetRegistry().getRegisteredAnnotations()) {
            TouchUIWidgetConfigHolder currentConfig =
                parameters.getWidgetRegistry().getWidgetForAnnotation(currentWidgetAnnotation);

            if (resourceType.equals(currentConfig.getResourceType())
                && (rankingCeiling < 0 || currentConfig.getRanking() < rankingCeiling)) {
                if (highestRankedConfig == null || highestRankedConfig.getRanking() < currentConfig.getRanking()) {
                    highestRankedConfig = currentConfig;
                }
            }
        }

        return highestRankedConfig;
    }
}
