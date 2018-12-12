package com.citytechinc.cq.component.touchuidialog.widget.dialogfieldset;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

@TouchUIWidget(annotationClass = DialogFieldSet.class, makerClass = DialogFieldSetWidgetMaker.class,
    resourceType = DialogFieldSetWidget.RESOURCE_TYPE)
public class DialogFieldSetWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/fieldset";

    private final String text;

    private final NameSpacedAttribute<String> jcrTitle;

    public DialogFieldSetWidget(DialogFieldSetWidgetParameters parameters) {
        super(parameters);

        text = parameters.getText();
        jcrTitle = new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX, "title",
            parameters.getJcrTitle());
    }

    public String getText() {
        return text;
    }

    public NameSpacedAttribute<String> getJcrTitle() {
        return jcrTitle;
    }
}
