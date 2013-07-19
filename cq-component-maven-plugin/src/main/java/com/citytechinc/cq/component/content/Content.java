package com.citytechinc.cq.component.content;

import com.citytechinc.cq.component.annotations.ContentProperty;

import java.util.List;

public interface Content {

    public Boolean isContainer();

    public String getPrimaryType();

    public String getTitle();

    public String getGroup();

    public String getResourceSuperType();

    public List<ContentProperty> getAdditionalProperties();
}
