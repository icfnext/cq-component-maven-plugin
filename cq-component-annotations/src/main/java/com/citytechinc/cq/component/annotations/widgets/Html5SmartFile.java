package com.citytechinc.cq.component.annotations.widgets;

public @interface Html5SmartFile {

	public String name() default "";

	public boolean allowFileNameEditing() default true;

	public boolean allowFileReference() default true;

	public boolean allowUpload() default true;

	public String ddAccept() default "*";

	public String[] ddGroups() default {};

	public String fileNameParameter();

	public String fileReferenceParameter();

	//TODO: Consider implementing footPanel if we move forward with panel support

	//TODO: Consider implementing headPanel if we move forward with panel support

	public String mimeTypes() default "*.*";

	public String mimeTypesDescription() default "All files";

	public int sizeLimit() default 0;
}
