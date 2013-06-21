package com.citytechinc.cq.component.content;

import java.util.Map;

public interface Content {

	public Boolean isContainer();

	public String getPrimaryType();

	public String getTitle();

	public String getGroup();

	public String getResourceSuperType();

	public Map<String, String> getAdditionalProperties();

}
