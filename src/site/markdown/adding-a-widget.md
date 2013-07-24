## Adding a Widget
If you have a custom xtype that you would like to add as a annotation (or an out of box one that wasn't included by default), the component
plugin allows for this via the addition of a few classes and an annotation. 

### Extending WidgetParameters
The class [WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) is used to pass all the parameters needed to build the widget
from the WidgetMaker to the implementing widget class.  If the widget requires no extra configuration, this class can be used directly, otherwise it must be
extended and the additional fields and accessors for the widget properties must be added.

#### Example (For CQ.Ext.form.Checkbox):

	import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
	import com.citytechinc.cq.component.util.Constants;
	
	public class CheckBoxWidgetParameters extends WidgetParameters {
		private String inputValue;
		private boolean checked;
	
		public String getInputValue() {
			return inputValue;
		}
	
		public void setInputValue(String inputValue) {
			this.inputValue = inputValue;
		}
	
		public boolean isChecked() {
			return checked;
		}
	
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
	}
	
### Adding the Widget Class
The abstract class [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) is used as a superclass for all widgets and builds the
base component properties based on the WidgetParameters passed in.  This class must be extended and all fields that will end up in the dialog.xml need to have
getters created.

#### Example (For CQ.Ext.form.Checkbox):

	import com.citytechinc.cq.component.dialog.AbstractWidget;
	
	public class CheckBoxWidget extends AbstractWidget {
	
		private final String inputValue;
		private final boolean checked;
	
		public CheckBoxWidget(CheckBoxWidgetParameters parameters) {
			super(parameters);
			this.inputValue = parameters.getInputValue();
			this.checked = parameters.isChecked();
		}
	
		public String getInputValue() {
			return inputValue;
		}
	
		public boolean isChecked() {
			return checked;
		}
	}
	
### Adding a Maker
The next step is to add a maker class that extends [AbstractWidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/AbstractWidgetMaker.html).
This class provides some utility methods to get the field name, field label, and other commonly used parameters.  It also provides access to all the annotations
that are on the field/method by using the getAnnotation method.

When extending AbstractWidgetMaker, the make class must be implemented and must return the build widget class.

#### Example (For CQ.Ext.form.Checkbox):

	import java.util.Map;
	
	import com.citytechinc.cq.component.annotations.widgets.CheckBox;
	import com.citytechinc.cq.component.dialog.DialogElement;
	import com.citytechinc.cq.component.dialog.Listeners;
	import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
	import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
	
	public class CheckBoxWidgetMaker extends AbstractWidgetMaker {
	
		public CheckBoxWidgetMaker(WidgetMakerParameters parameters) {
			super(parameters);
		}
	
		public DialogElement make() throws ClassNotFoundException {
	
			CheckBox checkBoxAnnotation = getAnnotation(CheckBox.class);
	
			String name = getNameForField();
			String fieldName = getFieldNameForField();
			String fieldLabel = getFieldLabelForField();
			String fieldDescription = getFieldDescriptionForField();
			Boolean isRequired = getIsRequiredForField();
			Map<String, String> additionalProperties = getAdditionalPropertiesForField();
			String defaultValue = getDefaultValueForField();
			boolean hideLabel = getHideLabelForField();
	
			String inputValue = getInputValueForField(checkBoxAnnotation);
			boolean checked = getCheckedForField(checkBoxAnnotation);
	
			Listeners listeners = getListeners();
	
			CheckBoxWidgetParameters widgetParameters = new CheckBoxWidgetParameters();
			widgetParameters.setInputValue(inputValue);
			widgetParameters.setChecked(checked);
			widgetParameters.setFieldLabel(fieldLabel);
			widgetParameters.setFieldDescription(fieldDescription);
			widgetParameters.setAllowBlank(!isRequired);
			widgetParameters.setHideLabel(hideLabel);
			widgetParameters.setDefaultValue(defaultValue);
			widgetParameters.setName(name);
			widgetParameters.setFieldName(fieldName);
			widgetParameters.setAdditionalProperties(additionalProperties);
			widgetParameters.setListeners(listeners);
	
			return new CheckBoxWidget(widgetParameters);
	
		}
	
		protected String getInputValueForField(CheckBox annotation) {
			return annotation.inputValue();
		}
	
		protected boolean getCheckedForField(CheckBox annotation) {
			return annotation.checked();
		}
	
	}
	
### Adding the Widget Annotation
All widgets are found by the plugin by scanning all dependencies for the [Widget](apidocs/com/citytechinc/cq/component/annotations/config/Widget.html) annotations, so we must add this to the widget class.  The annotation requires that you 
configure the widget annotation class, the widget maker, and the xtype for the widget.

#### Example (For CQ.Ext.form.Checkbox):
	...
	import com.citytechinc.cq.component.dialog.AbstractWidget;
	
	@Widget(annotationClass = CheckBox.class, makerClass = CheckBoxWidgetMaker.class, xtype = "checkbox")
	public class CheckBoxWidget extends AbstractWidget {
	
		private final String inputValue;
		...
	}