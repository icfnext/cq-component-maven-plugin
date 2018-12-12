package com.citytechinc.cq.component.dialog;

/**
 * During execution of the CQ Component Plugin, a Component Name Transformer
 * will transform the Java Class name of a Component into a name applicable for
 * a component node in the content repository. This name ends up being the
 * terminal part of the content path to the Component definition.
 *
 */
public interface ComponentNameTransformer {

	/**
	 *
	 * @param className The unqualified, or simple, name of the Java Class to
	 *            transform
	 * @return The transformed name
	 */
	public String transform(String className);
}
