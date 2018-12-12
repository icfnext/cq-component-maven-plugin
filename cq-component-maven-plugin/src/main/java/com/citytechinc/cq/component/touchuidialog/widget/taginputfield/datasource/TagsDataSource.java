package com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource;

import java.util.List;

import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;

public class TagsDataSource extends DataSource {

	public static final String RESOURCE_TYPE = "cq/gui/components/common/datasources/tags";

	private final List<String> namespaces;

	public TagsDataSource(TagsDataSourceParameters parameters) {
		super(parameters);

		namespaces = parameters.getNamespaces();
	}

	public List<String> getNamespaces() {
		return namespaces;
	}

}
