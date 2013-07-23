## Adding a Transformer
The transformer is what determains where in the repository a components files will be stored.  Given the className it transforms it into the folder name.
Adding a transformer can be done by adding a single class.

### Implementing ComponentNameTransformer
The first step is to create a class that implements [com.citytechinc.cq.component.dialog.ComponentNameTransformer](apidocs/com/citytechinc/cq/component/dialog/ComponentNameTransformer.html).  The only method that needs to be implemented is transform(String className).
This will be passed the name of the class that the component annotation is on.

### Adding Annotation
The created class needs to be annotated with com.citytechinc.cq.component.annotations.transformer.Transformer.  The value of this
annotation will be the value that goes in the transformerName configuration of the plugin.

### Example
This example will captialise the class name

	import com.citytechinc.cq.component.annotations.transformer.Transformer;
	import com.citytechinc.cq.component.dialog.ComponentNameTransformer;

	@Transformer("captialiser")
	public class CapitalTransformer implements ComponentNameTransformer {
		public String transform(String className) {
			return className.toUpperCase();
		}
	}
	
This can then be used like:

	<plugin>
		<groupId>com.citytechinc.cq.cq-component-plugin</groupId>
		<artifactId>cq-component-maven-plugin</artifactId>
	    ...
	    <configuration>
	        ...
	        <transformerName>captialiser</transformerName>
	        ...
	    </configuration>
	</plugin>