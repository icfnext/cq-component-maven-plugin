package com.citytechinc.cq.component.dialog.widget;

import com.citytechinc.cq.component.dialog.DialogElementParameters;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public interface WidgetParameters extends DialogElementParameters, XmlElementParameters {

    String getXtype();

    void setXtype(String xtype);

    String getFieldLabel();

    void setFieldLabel(String fieldLabel);

    String getFieldDescription();

    void setFieldDescription(String fieldDescription);

    boolean isAllowBlank();

    void setAllowBlank(boolean allowBlank);

    String getDefaultValue();

    void setDefaultValue(String defaultValue);

    String getName();

    void setName(String name);

    boolean isHideLabel();

    void setHideLabel(boolean hideLabel);

    boolean isDisabled();

    void setDisabled(boolean disabled);

    String getPrimaryType();

}