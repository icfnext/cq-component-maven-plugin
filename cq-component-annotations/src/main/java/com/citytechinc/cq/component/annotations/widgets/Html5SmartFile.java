/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.html5.form.SmartFile in Classic UI
 *
 * Represents a Widget of type granite/ui/components/foundation/form/fileupload
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Html5SmartFile {

	/**
	 * Used for Classic UI only
	 *
	 * The field's HTML name attribute
	 *
	 * @return String
	 */
	public String name() default "";

	/**
	 * Used for Classic UI only
	 *
	 * True if the name of an uploaded file is editable
	 *
	 * @return boolean
	 */
	public boolean allowFileNameEditing() default true;

	/**
	 * Used for Classic UI only
	 *
	 * Flag if referencing a file is allowed
	 *
	 * @return boolean
	 */
	public boolean allowFileReference() default true;

	/**
	 * Used for Classic UI only
	 *
	 * Flag if uploading a file is allowed
	 *
	 * @return boolean
	 */
	public boolean allowUpload() default true;

	/**
	 * Used for Classic UI only
	 *
	 * MIME type definition of files that are allowed for referencing using drag
	 * & drop
	 *
	 * @return String
	 */
	public String ddAccept() default "*";

	/**
	 * Used for Classic UI only
	 *
	 * Groups involved in drag & drop
	 *
	 * @return String[]
	 */
	public String[] ddGroups() default {};

	/**
	 * Name of the form field used for posting the file name. Be aware that you
	 * will have to specify a suitable value here, as there is no sensible
	 * default value available. Suitable values are dependant on their
	 * serverside counterpart and must be "./fileName" for CQ foundation's image
	 * and download components; use "./image/fileName" for the textimage
	 * component.
	 *
	 * @return String
	 */
	public String fileNameParameter();

	/**
	 * Name of the form field used for posting the file reference. Be aware that
	 * you will have to specify a suitable value here, as there is no sensible
	 * default value available. Suitable values are dependant on their
	 * serverside counterpart and must be "./fileReference" for CQ foundation's
	 * image and download components; use "./image/fileReference" for the
	 * textimage component.
	 *
	 * @return String
	 */
	public String fileReferenceParameter();

	// TODO: Consider implementing footPanel if we move forward with panel
	// support

	// TODO: Consider implementing headPanel if we move forward with panel
	// support

	/**
	 * Used for Classic UI only
	 *
	 * MIME types allowed for uploading (each separated by a semicolon; wildcard
	 * * is allowed; for example: "*.*" or "*.jpg;*.gif;*.png" (defaults to
	 * "*.*".) Also support MIME type syntax; for example (image/jpg or image/*)
	 *
	 * @return String
	 */
	public String mimeTypes() default "*.*";

	/**
	 * Used for Touch UI only
	 *
	 * The list of mime types allowed for upload. This must be defined uniquely
	 * for Touch UI as the ExtJS allows different extension and wild card
	 * semantics than the Touch UI variant.
	 *
	 * @return String[]
	 */
	public String[] touchUIMimeTypes() default {};

	/**
	 * Used for Classic UI only
	 *
	 * A String that describes the allowed MIME types
	 *
	 * @return String
	 */
	public String mimeTypesDescription() default "All files";

	/**
	 * Maximum size of a file to be uploaded in Megabytes
	 *
	 * @return int
	 */
	public int sizeLimit() default 0;

	/**
	 * Used for Touch UI Only
	 *
	 * Indicates that multiple files may be uploaded
	 *
	 * @return boolean
	 */
	public boolean multiple() default false;

	/**
	 * Used for Touch UI Only
	 *
	 * URL where to upload the file, you can use <code>${suffix.path}</code>
	 *
	 * @return String
	 */
	public String uploadUrl() default "${suffix.path}";

	/**
	 * Used for Touch UI Only
	 *
	 * The AEM documentation indicates that this should be an upload URL builder
	 *
	 * @return String
	 */
	public String uploadUrlBuilder() default "";

	/**
	 * Used for Touch UI Only
	 *
	 * If <code>true</code>, upload starts automatically once the file is
	 * selected
	 *
	 * @return boolean
	 */
	public boolean autoStart() default false;

	/**
	 * Used for Touch UI Only
	 *
	 * Prefer HTML5 to upload files (if browser allows it)
	 *
	 * @return boolean
	 */
	public boolean useHtml5() default true;

	/**
	 * Used for Touch UI Only
	 *
	 * Drop zone selector to upload files from file system directly (if browser
	 * allows it)
	 *
	 * @return String
	 */
	public String dropZone() default "";

	/**
	 * Used for Touch UI Only
	 *
	 * The title associated with the upload button
	 *
	 * @return String
	 */
	public String title() default "";

	/**
	 * Used for Touch UI Only
	 *
	 * The text of the button
	 *
	 * @return String
	 */
	public String text() default "";

	/**
	 * Used for Touch UI Only
	 *
	 * The icon of the button
	 *
	 * @return String
	 */
	public String icon() default "";
}
