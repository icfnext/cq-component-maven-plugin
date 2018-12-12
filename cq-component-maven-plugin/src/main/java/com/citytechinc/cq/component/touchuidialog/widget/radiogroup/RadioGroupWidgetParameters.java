package com.citytechinc.cq.component.touchuidialog.widget.radiogroup;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.Options;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.OptionsParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class RadioGroupWidgetParameters extends DefaultTouchUIWidgetParameters {

	private List<Option> options;
	private DataSource dataSource;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Options getOptions() {
		if (options != null) {
			OptionsParameters optionsParameters = new OptionsParameters();
			optionsParameters.setOptions(options);
			return new Options(optionsParameters);
		}

		return null;
	}

	public void addOption(Option option) {
		if (options == null) {
			options = new ArrayList<Option>();
		}

		options.add(option);
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {
		List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

		if (getOptions() != null) {
			allContainedElements.add(getOptions());
		}

		if (getDataSource() != null) {
			allContainedElements.add(getDataSource());
		}

		if (containedElements != null) {
			allContainedElements.addAll(containedElements);
		}

		return allContainedElements;
	}

	@Override
	public String getResourceType() {
		if(TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
			return RadioGroupCoral3Widget.RESOURCE_TYPE;
		}
		return RadioGroupWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for RadioGroupWidget");
	}
}
