package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagInputFieldWidgetMaker extends AbstractWidgetMaker<TagInputFieldWidgetParameters> {

    public TagInputFieldWidgetMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(TagInputFieldWidgetParameters parameters) throws ClassNotFoundException {

        TagInputField tagAnnotation = getAnnotation(TagInputField.class);

        parameters.setContainedElements(getWidgetCollectionHolderForField(tagAnnotation));

        parameters.setDisplayTitles(tagAnnotation.displayTitles());

        return new TagInputFieldWidget(parameters);

    }

    private List<DialogElement> getWidgetCollectionHolderForField(TagInputField tagAnnotation) {

        if (tagAnnotation.namespaces().length == 0) {
            return null;
        }

        List<DialogElement> namespaces = new ArrayList<DialogElement>();
        for (int i = 0; i < tagAnnotation.namespaces().length; i++) {
            TagNameSpace ns = tagAnnotation.namespaces()[i];

            String max = null;
            if (ns.maximum() > -1) {
                max = Integer.toString(ns.maximum());
            }
            NamespaceParameters namespaceParameters = new NamespaceParameters();
            namespaceParameters.setFieldName("namespace" + i);
            namespaceParameters.setName(ns.value());
            namespaceParameters.setMaximum(max);
            Namespace n = new Namespace(namespaceParameters);
            namespaces.add(n);
        }

        WidgetCollectionParameters wcp = new WidgetCollectionParameters();
        wcp.setFieldName("namespaces");
        wcp.setContainedElements(namespaces);
        return Arrays.asList(new DialogElement[]{ new WidgetCollection(wcp) });

    }

}
