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
package com.citytechinc.cq.component.touchuidialog.container;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class Section extends Container {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/section";
	public static final String INCLUDE_RESOURCE_TYPE = "granite/ui/components/foundation/include";

	private NameSpacedAttribute<String> title;
	private String path;
	private String resourceType;
	private final NameSpacedAttribute<Boolean> showOnCreate;
    private final NameSpacedAttribute<Boolean> hideOnEdit;

	public Section(SectionParameters parameters) {
		super(parameters);

		if (StringUtils.isNotEmpty(parameters.getTitle())) {
			this.title =
				new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, parameters.getTitle());
		}

		if (StringUtils.isNotEmpty(parameters.getPath())) {
			this.resourceType = RESOURCE_TYPE;
			this.path = parameters.getPath();
		}

		showOnCreate =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isShowOnCreate());

        hideOnEdit =
            new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isHideOnEdit());
	}

	public NameSpacedAttribute<String> getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}

	public String getResourceType() {
		return resourceType;
	}

	public NameSpacedAttribute<Boolean> getShowOnCreate() {
		return showOnCreate;
	}

    public NameSpacedAttribute<Boolean> getHideOnEdit() {
        return hideOnEdit;
    }

}
