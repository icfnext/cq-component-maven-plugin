/**
 *    Copyright 2013 CITYTECH, Inc.
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
 * Represents a Widget of type CQ.html5.form.SmartImage in Classic UI
 *
 * The Smart Image is not supported in Touch UI and will render as a granite/ui/components/foundation/form/fileupload
 * Widget
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Html5SmartImage {

	/**
	 * Used for Classic UI only
	 *
	 * True to not render the flush button.
	 *
	 * @return boolean
	 */
	public boolean disableFlush() default false;

	/**
	 * Used for Classic UI only
	 *
	 * True to hide the "information" tool
	 *
	 * @return boolean
	 */
	public boolean disableInfo() default false;

	/**
	 * Used for Classic UI only
	 *
	 * True to not render the zoom slider
	 *
	 * @return boolean
	 */
	public boolean disableZoom() default false;

	/**
	 * Used for Classic UI only
	 *
	 * Name of the form field used for posting the cropping rect; use null or a
	 * zero-length String if the cropping tool should be disabled; the value
	 * should always be "./imageCrop" for CQ
	 ** 
	 * @deprecated use {@link #allowCrop()} to automatically to set the value to
	 *             what CQ expects
	 * @return String
	 */
	@Deprecated
	public String cropParameter() default "";

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
	public String fileNameParameter() default "fileName";

	/**
	 * Used for Classic UI only
	 *
	 * Name of the form field used for posting the file reference. Be aware that
	 * you will have to specify a suitable value here, as there is no sensible
	 * default value available. Suitable values are dependant on their
	 * serverside counterpart and must be "./fileReference" for CQ foundation's
	 * image and download components; use "./image/fileReference" for the
	 * textimage component.
	 *
	 * @return String
	 */
	public String fileReferenceParameter() default "fileReference";

	/**
	 * The field's HTML name attribute
	 *
	 * @return String
	 */
	public String name() default "";

	/**
	 * Used for Classic UI only
	 *
	 * Name of the form field used for posting the image map data; use null or a
	 * zero-length String if the image mapping tool should be disabled; the
	 * value deshould always be "./imageMap" for CQ
	 *
	 * @deprecated use {@link #allowMap()} to automatically to set the value to
	 *             what CQ expects
	 * @return String
	 */
	@Deprecated
	public String mapParameter() default "";

	/**
	 * Used for Classic UI only
	 *
	 * Name of the form field used for posting the rotation angle; use null or a
	 * zero-length String if the rotate tool should be disabled; The value
	 * should always be "imageRotate" for CQ
	 *
	 * @deprecated use {@link #allowRotate()} to automatically to set the value
	 *             to what CQ expects
	 * @return String
	 */
	@Deprecated
	public String rotateParameter() default "";

	/**
	 * Path to which files will be uploaded.
	 *
	 * @return String
	 */
	public String uploadUrl() default "/tmp/upload/*";

	/**
	 * Used for Classic UI only
	 *
	 * Groups involved in drag & drop
	 *
	 * @return String[]
	 */
	public String[] ddGroups() default { "media" };

	/**
	 * Used for Classic UI only
	 *
	 * Indication of whether the HTML5 Smart Image Widget should be rendered as
	 * a stand alone tab.
	 *
	 * @return boolean
	 */
	public boolean tab() default true;

	/**
	 * Used for Classic UI only
	 *
	 * Height of the SmartImage component. Note: You must explicitly specify
	 * height if you intend to render the HTML5 Smart Image Widget outside the
	 * context of its own tab.
	 *
	 * @return int
	 */
	public int height() default 0;

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
	 * Crop Config Aspect Ratios
	 * 
	 * @return AspectRatio[]
	 */
	public AspectRatio[] cropAspectRatios() default {};

	/**
	 * Used for Classic UI only
	 *
	 * Method to turn on cropping and automatically set the cropParameter; this
	 * simplifies {@link #cropParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowCrop() default false;

	/**
	 * Used for Classic UI only
	 *
	 * Method to turn on rotating and automatically set the rotateParameter;
	 * this simplifies {@link #rotateParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowRotate() default false;

	/**
	 * Used for Classic UI only
	 *
	 * Method to turn on mapping and automatically set the mapParameter; this
	 * simplifies {@link #mapParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowMap() default false;

	/**
	 * Used for Touch UI only
	 *
	 * The list of mime types allowed for upload.  This must be defined uniquely for
	 * Touch UI as the ExtJS allows different extension and wild card semantics than
	 * the Touch UI variant.
	 *
	 * @return String[]
	 */
	public String[] touchUIMimeTypes() default {};

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
	 * URL where to upload the file, you can use <code>${suffix.path}</code>.  Upload semantics are different
	 * between the legacy ExtJS API and the new Touch UI API.
	 *
	 * @return String
	 */
	public String touchUIUploadUrl() default "${suffix.path}";

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
	 * If <code>true</code>, upload starts automatically once the file is selected
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
	 * Drop zone selector to upload files from file system directly (if browser allows it)
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

	/**
	 * Used for Touch UI Only
	 *
	 * Maximum size of a file to be uploaded in Megabytes
	 *
	 * @return int
	 */
	public int sizeLimit() default 0;

}
