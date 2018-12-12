package com.citytechinc.cq.component.touchuidialog.container;

import org.codehaus.plexus.util.StringUtils;

public class Section extends Container {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/section";

    public static final String INCLUDE_RESOURCE_TYPE = "granite/ui/components/foundation/include";

    private String path;

    private String resourceType;

    public Section(SectionParameters parameters) {
        super(parameters);

        if (StringUtils.isNotEmpty(parameters.getPath())) {
            this.resourceType = RESOURCE_TYPE;
            this.path = parameters.getPath();
        }
    }

    public String getPath() {
        return path;
    }

    public String getResourceType() {
        return resourceType;
    }
}
