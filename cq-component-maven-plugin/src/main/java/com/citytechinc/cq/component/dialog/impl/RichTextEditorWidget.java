package com.citytechinc.cq.component.dialog.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

@Widget(annotationClass="com.citytechinc.cq.component.annotations.widgets.RichTextEditor", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.RichTextEditorMaker", xtypes = "richtext")
public class RichTextEditorWidget extends AbstractWidget {

	public static final String XTYPE = "richtext";
	private static final String PRIMARY_TYPE = "cq:Widget";

	public RichTextEditorWidget(
			String fieldLabel,
			String fieldDescription,
			boolean allowBlank,
			String defaultValue,
			String name,
			String fieldName,
			Map<String, String> additionalProperties,
			List<DialogElement> rtePlugins) {

		super(
				XTYPE,
				fieldLabel,
				fieldDescription,
				allowBlank,
				defaultValue,
				name,
				PRIMARY_TYPE,
				null,
				fieldName,
				additionalProperties,
				Arrays.asList(new DialogElement[]{new WidgetCollection(rtePlugins,"rtePlugins")}));

	}

}
