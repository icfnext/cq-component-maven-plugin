package com.citytechinc.cq.component.dialog.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.RichTextEditor;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.impl.RichTextEditorMaker;

@Widget(annotationClass = RichTextEditor.class, makerClass = RichTextEditorMaker.class, xtype = "richtext")
public class RichTextEditorWidget extends AbstractWidget {

	public static final String XTYPE = "richtext";
	private static final String PRIMARY_TYPE = "cq:Widget";

	public RichTextEditorWidget(String fieldLabel, String fieldDescription, boolean allowBlank, boolean hideLabel,
		String defaultValue, String name, String fieldName, Map<String, String> additionalProperties,
		List<DialogElement> rtePlugins) {

		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, Arrays.asList(new DialogElement[] { new RtePlugins(rtePlugins) }));

	}

}
