package com.citytechinc.cq.component.touchuidialog.widget;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class AbstractTouchUIWidget extends AbstractTouchUIDialogElement {

	private String name;
	private final String title;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean required;
	private final String value;
	private final String defaultValue;
	private final boolean disabled;
	private final String cssClass;
	private final boolean renderReadOnly;
	private final NameSpacedAttribute<Boolean> showOnCreate;
    private final NameSpacedAttribute<Boolean> hideOnEdit;

	public AbstractTouchUIWidget(DefaultTouchUIWidgetParameters parameters) {
		super(parameters);

		this.name = parameters.getName();
		this.title = parameters.getTitle();
		this.fieldLabel = parameters.getFieldLabel();
		this.fieldDescription = parameters.getFieldDescription();
		this.required = parameters.isRequired();
		this.value = parameters.getValue();
		this.defaultValue = parameters.getDefaultValue();
		this.disabled = parameters.isDisabled();
		this.cssClass = parameters.getCssClass();
		this.renderReadOnly = parameters.isRenderReadOnly();
		this.showOnCreate =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isShowOnCreate());
        this.hideOnEdit =
            new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isHideOnEdit());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public String getValue() {
		return value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public String getTitle() {
		return title;
	}

	public Map<String, String> getCssClass() {

		Map<String, String> retMap = new HashMap<String, String>();

		if (StringUtils.isNotBlank(cssClass)) {
			retMap.put("class", cssClass);
		}

		return retMap;

	}

	public boolean isRenderReadOnly() {
		return renderReadOnly;
	}

	public NameSpacedAttribute<Boolean> getShowOnCreate() {
		return showOnCreate;
	}

    public NameSpacedAttribute<Boolean> getHideOnEdit() {
        return hideOnEdit;
    }
}
