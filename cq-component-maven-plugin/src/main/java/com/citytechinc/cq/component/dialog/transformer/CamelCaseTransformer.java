package com.citytechinc.cq.component.dialog.transformer;

import com.citytechinc.cq.component.annotations.transformer.Transformer;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;
import org.codehaus.plexus.util.StringUtils;

@Transformer("camel-case")
public class CamelCaseTransformer implements ComponentNameTransformer {

    public String transform(String className) {
        return StringUtils.uncapitalise(className);
    }

}
