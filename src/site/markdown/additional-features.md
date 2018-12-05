## Additional Features
In order to maintain compatibility across AEM releases, new features and fixes released for the Touch UI in AEM are 
not exposed by this Plugin by default.  In order to enable them the appropriate Feature Flags must be enabled 
via the `additionalFeatures` Plugin POM configuration.

## Available Features
The following are the Feature Flags currently respected by the Plugin out of the box.

### rte-touchui
Allows for RTEs as in-dialog widgets in the Touch UI.  

### tagspickerwidget
Uses the tags picker widget type for Tag Input Fields as opposed to the autocomplete widget type.

### tagfieldwidget
Uses the tag field widget type for Tag Input Fields as opposed to either the autocomplete or Tag Picker widget types.

### hiddenfieldwidget
Allows the creation of hidden fields in Touch UI.

## Using Feature Flags in Custom Widgets
The `@TouchUIWidget` annotation exposes a `featureFlag` property which allows for the enablement of a given 
widget contingent on the configuration of the matching flag in the executing project's POM.  If your custom widget 
should only be available 

### Overlaying Features
When two `@TouchUIWidget` annotations are associated with the same `annotationClass`, the following rules are 
followed concerning which to use:

* Custom widgets are chosen over core widgets
* Widgets with enabled feature flags are chosen over those without

Feature flags can thus be used in custom widgets to overlay existing or custom widgets based on the configuration 
of a feature flag in the executing project's POM.