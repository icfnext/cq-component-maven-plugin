package com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource;

import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;

import java.util.List;

public class TagsDataSourceParameters extends DataSourceParameters {

    private List<String> namespaces;

    public List<String> getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(List<String> namespaces) {
        this.namespaces = namespaces;
    }

}
