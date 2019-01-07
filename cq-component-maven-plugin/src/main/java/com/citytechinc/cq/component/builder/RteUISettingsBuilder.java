package com.citytechinc.cq.component.builder;

import com.citytechinc.cq.component.annotations.widgets.ToolbarConfig;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.CUI;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.CUIParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Popover;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.PopoverParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Popovers;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.PopoversParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.Toolbar;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.ToolbarParameters;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.UISettings;
import com.citytechinc.cq.component.touchuidialog.widget.richtexteditor.UISettingsParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RteUISettingsBuilder {

    private final com.citytechinc.cq.component.annotations.widgets.rte.UISettings uiSettingsAnnotation;

    public RteUISettingsBuilder(com.citytechinc.cq.component.annotations.widgets.rte.UISettings uiSettingsAnnotation) {
        this.uiSettingsAnnotation = uiSettingsAnnotation;
    }

    public UISettings build() {
        List<Toolbar> toolbars = new ArrayList<Toolbar>();
        if (uiSettingsAnnotation.inline().length > 0) {
            toolbars.add(buildToolbar(uiSettingsAnnotation.inline()[0], "inline"));
        }

        if (uiSettingsAnnotation.fullscreen().length > 0) {
            toolbars.add(buildToolbar(uiSettingsAnnotation.fullscreen()[0], "dialogFullScreen"));
        }

        CUIParameters cuiParamers = new CUIParameters();
        cuiParamers.setContainedElements(toolbars);
        CUI cui = new CUI(cuiParamers);

        UISettingsParameters uiSettingsParameters = new UISettingsParameters();
        uiSettingsParameters.setContainedElements(Collections.singletonList(cui));
        return new UISettings(uiSettingsParameters);
    }

    private Toolbar buildToolbar(ToolbarConfig toolbarConfig, String fieldName) {
        ToolbarParameters toolbarParameters = new ToolbarParameters();
        toolbarParameters.setFieldName(fieldName);
        toolbarParameters.setToolbar(toolbarConfig.toolbars());
        if (toolbarConfig.popovers().length > 0) {
            PopoversParameters popoversParameters = new PopoversParameters();
            List<Popover> popovers = new ArrayList<Popover>();
            for (com.citytechinc.cq.component.annotations.widgets.Popover popover : toolbarConfig.popovers()) {
                PopoverParameters popoverParameters = new PopoverParameters();
                popoverParameters.setFieldName(popover.ref());
                popoverParameters.setRef(popover.ref());
                popoverParameters.setItems(popover.items());
                popovers.add(new Popover(popoverParameters));
            }
            popoversParameters.setContainedElements(popovers);
            toolbarParameters.setContainedElements(Collections.singletonList(new Popovers(popoversParameters)));
        }

        return new Toolbar(toolbarParameters);
    }
}
