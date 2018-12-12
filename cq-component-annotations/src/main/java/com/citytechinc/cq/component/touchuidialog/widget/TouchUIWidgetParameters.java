package com.citytechinc.cq.component.touchuidialog.widget;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;

public interface TouchUIWidgetParameters extends TouchUIDialogElementParameters {

    String getFieldLabel();

    void setFieldLabel(String fieldLabel);

    String getName();

    void setName(String name);

    String getTitle();

    void setTitle(String title);

    String getPrimaryType();

    String getFieldDescription();

    void setFieldDescription(String fieldDescription);

    boolean isRequired();

    void setRequired(boolean required);

    String getValue();

    void setValue(String value);

    String getDefaultValue();

    void setDefaultValue(String defaultValue);

    boolean isDisabled();

    void setDisabled(boolean disabled);

    String getCssClass();

    void setCssClass(String cssClass);

    boolean isRenderReadOnly();

    void setRenderReadOnly(boolean renderReadOnly);

    boolean isShowOnCreate();

    void setShowOnCreate(boolean showOnCreate);

    boolean isHideOnEdit();

    void setHideOnEdit(boolean hideOnEdit);

    String getOrderBefore();

    void setOrderBefore(String orderBefore);
}