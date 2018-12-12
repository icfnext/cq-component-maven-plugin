package com.citytechinc.cq.component.dialog.transformer;

import com.citytechinc.cq.component.annotations.transformer.Transformer;
import com.citytechinc.cq.component.dialog.ComponentNameTransformer;

@Transformer("lower-case-dash")
public class LowerCaseDashTransformer implements ComponentNameTransformer {

	public String transform(String className) {
		return className
			.replaceAll(
				String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
					"(?<=[A-Za-z])(?=[^A-Za-z])"), "-").toLowerCase();
	}

}
