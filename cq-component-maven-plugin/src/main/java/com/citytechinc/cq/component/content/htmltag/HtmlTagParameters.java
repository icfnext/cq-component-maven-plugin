package com.citytechinc.cq.component.content.htmltag;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class HtmlTagParameters extends DefaultXmlElementParameters {

    private static final String CONTENT_PRIMARY_TYPE = "nt:unstructured";

    private String tagName;

    private String cssClass;

    private String id;

    @Override
    public String getPrimaryType() {
        return CONTENT_PRIMARY_TYPE;
    }

    @Override
    public String getFieldName() {
        return "cq:htmlTag";
    }

    @Override
    public void setFieldName(String fieldName) {
        throw new UnsupportedOperationException("fieldName is Static for Html Tag");
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
