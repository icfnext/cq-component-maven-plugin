package com.citytechinc.cq.component.annotations.widgets.rte;

import com.citytechinc.cq.component.annotations.widgets.ToolbarConfig;

public @interface UISettings {

    /**
     * For Touch-UI Only
     * <p>
     * Configures in inline toolbar
     */
    ToolbarConfig[] inline() default {};

    /**
     * For Touch-UI Only
     * <p>
     * Configures in fullscreen toolbar
     */
    ToolbarConfig[] fullscreen() default {};
}
