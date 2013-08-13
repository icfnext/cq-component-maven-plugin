## Adding a Transformer
Transformers are used during the process of determining the path to the folder into which Component artifacts will be placed.
As indicated on the [configuration](configuration.html) page, the terminal node of the folder path is based on the name of
the Component class.  The class name is transformed using the transformer specified in the `transformerName` POM configuration
or the default transformer if one is not specified.

Custom Transformers can be defined by defining a new class implementing [ComponentNameTransformer](apidocs/com/citytechinc/cq/component/dialog/ComponentNameTransformer.html)
annotated by the [Transformer](apidocs/com/citytechinc/cq/component/annotations/transformer/Transformer.html) Annotation.

### Implementing ComponentNameTransformer
The first step is to create a class that implements [com.citytechinc.cq.component.dialog.ComponentNameTransformer](apidocs/com/citytechinc/cq/component/dialog/ComponentNameTransformer.html).
The only method that needs to be implemented is `transform(String className)` which, at project build time, will be passed the name of the Component class to translate.

### Adding Annotation
In order to find all available Transformers, the CQ Component Plugin scans all project dependencies for classes annotated by the
[Transformer](apidocs/com/citytechinc/cq/component/annotations/transformer/Transformer.html) Annotation.  Along with identifying the
class as a Transformer, the annotation exposes the name of the Transformer which will be used in the `transformerName` POM configuration
to indicate that the custom Transformer should be used.

### Example
This example will capitalize the class name.

#### Addition of the Custom Transformer Class

	import com.citytechinc.cq.component.annotations.transformer.Transformer;
	import com.citytechinc.cq.component.dialog.ComponentNameTransformer;

	@Transformer("capitalizer")
	public class CapitalTransformer implements ComponentNameTransformer {
		public String transform(String className) {
			return className.toUpperCase();
		}
	}

#### POM Configuration

	<plugin>
		<groupId>com.citytechinc.cq.cq-component-plugin</groupId>
		<artifactId>cq-component-maven-plugin</artifactId>
	    ...
	    <configuration>
	        ...
	        <transformerName>capitalizer</transformerName>
	        ...
	    </configuration>
	</plugin>