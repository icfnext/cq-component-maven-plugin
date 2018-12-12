package com.citytechinc.cq.component.annotations.widgets.rte;

import com.citytechinc.cq.component.annotations.widgets.ToolbarConfig;

public @interface UISettings {
	/**
	 * For Touch-UI Only
	 * 
	 * Configures in inline toolbar
	 */
	public ToolbarConfig[] inline() default {};

	/**
	 * For Touch-UI Only
	 * 
	 * Configures in fullscreen toolbar
	 * 
	 */
	public ToolbarConfig[] fullscreen() default {};
}
