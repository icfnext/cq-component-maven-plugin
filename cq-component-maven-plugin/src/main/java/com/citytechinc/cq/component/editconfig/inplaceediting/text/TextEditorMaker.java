package com.citytechinc.cq.component.editconfig.inplaceediting.text;

import com.citytechinc.cq.component.builder.RtePluginBuilder;
import com.citytechinc.cq.component.builder.RtePluginBuilderParameters;
import com.citytechinc.cq.component.editconfig.ConfigElement;
import com.citytechinc.cq.component.editconfig.DefaultInPlaceEditorParameters;
import com.citytechinc.cq.component.editconfig.InPlaceEditorElement;
import com.citytechinc.cq.component.editconfig.annotations.inplaceeditors.TextEditor;
import com.citytechinc.cq.component.editconfig.maker.AbstractInPlaceEditorMaker;
import com.citytechinc.cq.component.editconfig.maker.InPlaceEditorMakerParameters;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TextEditorMaker extends AbstractInPlaceEditorMaker<DefaultInPlaceEditorParameters> {

    public TextEditorMaker(InPlaceEditorMakerParameters parameters) {
        super(parameters);
    }

    @Override
    protected InPlaceEditorElement make(DefaultInPlaceEditorParameters parameters) throws ClassNotFoundException,
        IllegalAccessException, InstantiationException {
        TextEditor textEditor = (TextEditor) this.parameters.getInPlaceEditorConfig().getInPlaceEditorAnnotation();
        parameters.setTitle(textEditor.title());
        parameters.setEditorType("text");

        // Builds config node
        DefaultXmlElementParameters configParameters = new DefaultXmlElementParameters();
        configParameters.setPrimaryType(Constants.NT_UNSTRUCTURED);
        configParameters.setContainedElements(Arrays.asList(new RtePluginBuilder(new RtePluginBuilderParameters(
            textEditor)).build()));
        Map<String, Object> additionalProperties = new HashMap<String, Object>();
        additionalProperties.put("name", "./" + getName());
        additionalProperties.put("propertyName", "./" + getName());
        additionalProperties.put("textPropertyName", "./" + getName());
        configParameters.setAdditionalProperties(additionalProperties);

        ConfigElement config = new ConfigElement(configParameters);
        parameters.setConfigElement(config);

        return new com.citytechinc.cq.component.editconfig.inplaceediting.text.TextEditor(parameters);
    }
}
