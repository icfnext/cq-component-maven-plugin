## Adding a Widget
Custom xtypes or resourceTypes and custom construction of dialog widgets for existing xtypes or resourceTypes is supported 
via the Custom Widget extension mechanism.  Mirror versions of this mechanism exist for the Touch UI and Classic UI.  
When the CQ Component Plugin determines a particular field or element is to be output as a particular type of Widget, a
[WidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/WidgetMaker.html) for the Classic UI or a 
[TouchUIWidgetMaker](apidocs/com/citytechinc/cq/component/touchuidialog/widget/maker/TouchUIWidgetMaker.html) for the Touch UI  
is invoked and its `make` method is passed an instance of the 
[WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) class for the Classic UI or a 
[TouchUIWidgetParameters](apidocs/com/citytechinc/cq/component/touchuidialog/widget/TouchUIWidgetParameters.html) class for the Touch UI.  
The object which is the result of this method call is an instance of a subclass of [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) 
for the Classic UI or [AbstractTouchUIWidget](apidocs/com/citytechinc/cq/component/touchuidialog/widget/AbstractTouchUIWidget.html) for the Touch UI  
and represents the widget which will be written to the dialog.xml for the Classic UI or _cq_dialog.xml for the Touch UI during output.  
Using the Custom Widget extension mechanism involves:

* Determining whether Touch UI, Classic UI, or both are to be supported by your widget.  There is no systematic requirement to support both so this will be solely driven by your authoring requirements.
* Creating an extension of [WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) for the Classic UI or [TouchUIWidgetParameters](apidocs/com/citytechinc/cq/component/touchuidialog/widget/TouchUIWidgetParameters.html) for the Touch UI if parameters not included in this base class need to be represented during the construction of your widget.
* Creating a Widget Maker implementing the [WidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/WidgetMaker.html) for the Classic UI or [TouchUIWidgetMaker](apidocs/com/citytechinc/cq/component/touchuidialog/widget/maker/TouchUIWidgetMaker.html) for the Touch UI interface which will construct your widget.
* Defining a stackable Annotation which will be used to indicate that a field or method of a component Class is to be represented using your custom widget.
* Defining a subclass of [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) for the Classic UI or [AbstractTouchUIWidget](apidocs/com/citytechinc/cq/component/touchuidialog/widget/AbstractTouchUIWidget.html) for the Touch UI which will represent your custom widget.
* Annotating your custom widget class with the [Widget](apidocs/com/citytechinc/cq/component/annotations/config/Widget.html) Annotation for the Classic UI or [TouchUIWidget](apidocs/com/citytechinc/cq/component/annotations/config/TouchUIWidget.html) Annotation for the Touch UI.

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

### Extending TouchUIWidgetParameters
The purpose of [TouchUIWidgetParameters](apidocs/com/citytechinc/cq/component/touchuidialog/widget/TouchUIWidgetParameters.html) 
is the same as that of [WidgetParameters](apidocs/com/citytechinc/cq/component/dialog/widget/WidgetParameters.html) but for the 
rendering of Touch UI widgets.

#### TouchUIWidgetParameters Example
*(For CQ.Ext.form.Checkbox):*

	import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
	
	public class CheckboxWidgetParameters extends DefaultTouchUIWidgetParameters {
	
		private String text;
		private String title;
		private boolean checked;
	
		public String getText() {
			return text;
		}
	
		public void setText(String text) {
			this.text = text;
		}
	
		@Override
		public String getTitle() {
			return title;
		}
	
		@Override
		public void setTitle(String title) {
			this.title = title;
		}
	
		public boolean isChecked() {
			return checked;
		}
	
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
	
		@Override
		public String getResourceType() {
			return CheckboxWidget.RESOURCE_TYPE;
		}
	
		@Override
		public void setResourceType(String resourceType) {
			throw new UnsupportedOperationException("resourceType is Static for CheckboxWidget");
		}
	
	}

### Adding the Classic UI Widget Class
The abstract class [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) is used as a superclass for all widgets and builds the
core component properties based on the WidgetParameters object passed in.  This class must be extended and all additional fields which are needed in the dialog.xml
must be exposed via getter methods on the extending class.

#### Classic UI Widget Class Example
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
	
### Adding the Touch UI Widget Class
Similar to [AbstractWidget](apidocs/com/citytechinc/cq/component/dialog/AbstractWidget.html) the [AbstractTouchUIWidget](apidocs/com/citytechinc/cq/component/touchuidialog/widget/AbstractTouchUIWidget.html)  
class is used as a superclass for all Touch UI widgets.  This class must be extended and all additional fields which are needed in the _cq_dialog.xml
must be exposed via getter methods on the extending class.

#### Touch UI Widget Class Example
*(For CQ.Ext.form.Checkbox):*

	import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
	import com.citytechinc.cq.component.annotations.widgets.CheckBox;
	import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
	
	public class CheckboxWidget extends AbstractTouchUIWidget {
	
		public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/checkbox";
	
		private final String text;
		private final String title;
		private final boolean checked;
	
		public CheckboxWidget(CheckboxWidgetParameters parameters) {
			super(parameters);
	
			text = parameters.getText();
			title = parameters.getTitle();
			checked = parameters.isChecked();
	
		}
	
		public String getText() {
			return text;
		}
	
		public String getTitle() {
			return title;
		}
	
		public boolean isChecked() {
			return checked;
		}
	
	}

### Adding the Stackable Annotation for the Classic UI and/or the Touch UI
The widget associated with a field or method annotated with the [@DialogField](apidocs/com/citytechinc/cq/component/annotations/DialogField.html) annotation
may be further classified as using the custom Widget by applying a stacked annotation alongside the @DialogField annotation.  When defining
a custom Widget a new stackable annotation is needed in order to indicate to the plugin when to use the custom Widget.

#### Stackable Annotation Example
*(For CQ.Ext.form.Checkbox):*

    public @interface CheckBox {
        String inputValue() default "on";

        boolean checked() default false;
    }


### Adding a Maker for the Classic UI
The next step is to add a maker class that extends [AbstractWidgetMaker](apidocs/com/citytechinc/cq/component/dialog/maker/AbstractWidgetMaker.html).
This class provides some utility methods to get the field name, field label, and other commonly used parameters.  It also provides access to all the annotations
that are on the field/method by using the getAnnotation method.

When extending AbstractWidgetMaker, the `make` method must be implemented and must return the build widget class.

#### Classic UI Widget Maker Example
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
	
### Adding a Maker for the Touch UI
Conceptually similar to the steps for Classic UI the widget maker for the Touch UI will extend 
[AbstractTouchUIWidgetMaker](apidocs/com/citytechinc/cq/component/touchuidialog/widget/maker/AbstractTouchUIWidgetMaker.html).

#### Touch UI Widget Maker Example

	import org.codehaus.plexus.util.StringUtils;
	import com.citytechinc.cq.component.annotations.widgets.CheckBox;
	import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
	import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
	import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
	import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
	import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
	
	public class CheckboxWidgetMaker extends AbstractTouchUIWidgetMaker<CheckboxWidgetParameters> {
	
		public CheckboxWidgetMaker(TouchUIWidgetMakerParameters parameters) {
			super(parameters);
		}
	
		@Override
		public TouchUIDialogElement make(CheckboxWidgetParameters widgetParameters) throws ClassNotFoundException,
			InvalidComponentFieldException, TouchUIDialogGenerationException {
	
			CheckBox checkboxAnnotation = getAnnotation(CheckBox.class);
	
			widgetParameters.setText(getTextForField(checkboxAnnotation));
			widgetParameters.setTitle(getTitleForField(checkboxAnnotation));
			widgetParameters.setChecked(getCheckedForField(checkboxAnnotation));
	
			return new CheckboxWidget(widgetParameters);
		}
	
		public String getTextForField(CheckBox annotation) {
			if (annotation != null && StringUtils.isNotBlank(annotation.text())) {
				return annotation.text();
			}
	
			return null;
		}
	
		public String getTitleForField(CheckBox annotation) {
			if (annotation != null && StringUtils.isNotBlank(annotation.title())) {
				return annotation.title();
			}
	
			return null;
		}
	
		public boolean getCheckedForField(CheckBox annotation) {
			if (annotation != null) {
				return annotation.checked();
			}
	
			return false;
		}
	
	}


### Adding the Widget Annotation for the Classic UI
Classic UI Widget definitions are found by the plugin by scanning all dependencies for the [Widget](apidocs/com/citytechinc/cq/component/annotations/config/Widget.html) annotation.
As such, this annotation must be added to any custom Widget to make the Widget definition accessible to the plugin.
This annotation explicitly defines the link between the custom Widget annotation, the custom Widget Maker, and the output xtype, associating
all three with the annotated Widget.

#### Classic UI Widget Annotation Example
*(For CQ.Ext.form.Checkbox):*

	...
	import com.citytechinc.cq.component.dialog.AbstractWidget;

	@Widget(annotationClass = CheckBox.class, makerClass = CheckBoxWidgetMaker.class, xtype = "checkbox")
	public class CheckBoxWidget extends AbstractWidget {

		private final String inputValue;
		...
	}
	
### Adding the TouchUIWidget Annotation for the Touch UI
Touch UI Widget definitions are found by the plugin by scanning all dependencies for the [TouchUIWidget](apidocs/com/citytechinc/cq/component/annotations/config/TouchUIWidget.html)  
annotation.  This annotation explicitly defines the link between the custom Widget annotation, the custom Widget Maker, and 
the output resourceType, associating all three with the annotated Widget.

#### TouchUIWidget Annotation Example
*(For CQ.Ext.form.Checkbox):*

	import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
	import com.citytechinc.cq.component.annotations.widgets.CheckBox;
	import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;
	
	@TouchUIWidget(annotationClass = CheckBox.class, makerClass = CheckboxWidgetMaker.class,
		resourceType = CheckboxWidget.RESOURCE_TYPE)
	public class CheckboxWidget extends AbstractTouchUIWidget {
	
		...
	
	}
