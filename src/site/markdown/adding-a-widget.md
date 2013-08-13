## Adding a Widget
Custom xtypes and custom construction of dialog widgets for existing xtypes is supported via the Custom Widget extension mechanism.
When the CQ Component Plugin determines a particular field or element is to be output as a particular type of Widget, a
[WidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/WidgetMaker.html)
for the Widget type is invoked and it's `make` method is passed an instance of the
[WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) class.  The object which is the result
of this method call is an instance of a subclass of [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html)
and represents the widget which will be written to the dialog.xml during output.  Using the Custom Widget extension mechanism involves:

* Creating an extension of [WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) if parameters not included in this base class need to be represented during the construction of your widget.
* Creating a Widget Maker implementing the [WidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/WidgetMaker.html) interface which will construct your widget.
* Defining a stackable Annotation which will be used to indicate that a field or method of a component Class is to be represented using your custom widget.
* Defining a subclass of [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) which will represent your custom widget.
* Annotating your custom widget class with the [Widget](apidocs/com/citytechinc/cq/component/annotations/config/Widget.html) Annotation.

### Extending WidgetParameters
The class [WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) is used to pass all the parameters needed to build the widget
from the WidgetMaker to the implementing widget class.  If the widget requires no extra configuration, this class can be used directly, otherwise it must be
extended and the additional fields and accessors for the widget properties must be added.

#### Widget Parameters Example
*(For CQ.Ext.form.Checkbox):*

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
core component properties based on the WidgetParameters object passed in.  This class must be extended and all additional fields which are needed in the dialog.xml
must be exposed via getter methods on the extending class.

#### Widget Class Example
*(For CQ.Ext.form.Checkbox):*

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

### Adding the Stackable Annotation
The widget associated with a field or method annotated with the [@DialogField](apidocs/com/citytechinc/cq/component/annotations/DialogField.html) annotation
may be further classified as using the custom Widget by applying a stacked annotation alongside the @DialogField annotation.  When defining
a custom Widget a new stackable annotation is needed in order to indicate to the plugin when to use the custom Widget.

#### Stackable Annotation Example
*(For CQ.Ext.form.Checkbox):*

    public @interface CheckBox {
        String inputValue() default "on";

        boolean checked() default false;
    }

### Adding a Maker
The next step is to add a maker class that extends [AbstractWidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/AbstractWidgetMaker.html).
This class provides some utility methods to get the field name, field label, and other commonly used parameters.  It also provides access to all the annotations
that are on the field/method by using the getAnnotation method.

When extending AbstractWidgetMaker, the `make` method must be implemented and must return the build widget class.

#### Widget Maker Example
*(For CQ.Ext.form.Checkbox):*

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
Widget definitions are found by the plugin by scanning all dependencies for the [Widget](apidocs/com/citytechinc/cq/component/annotations/config/Widget.html) annotation.
As such, this annotation must be added to any custom Widget to make the Widget definition accessible to the plugin.
This annotation explicitly defines the link between the custom Widget annotation, the custom Widget Maker, and the output xtype, associating
all three with the annotated Widget.

#### Widget Annotation Example
*(For CQ.Ext.form.Checkbox):*

	...
	import com.citytechinc.cq.component.dialog.AbstractWidget;

	@Widget(annotationClass = CheckBox.class, makerClass = CheckBoxWidgetMaker.class, xtype = "checkbox")
	public class CheckBoxWidget extends AbstractWidget {

		private final String inputValue;
		...
	}