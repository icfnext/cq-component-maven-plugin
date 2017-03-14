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
 * Represents a Widget of type CQ.html5.form.SmartImage in Classic UI
 *
 * The Smart Image is not supported in Touch UI and will render as a
 * granite/ui/components/foundation/form/fileupload Widget
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
	 * Indicates that this component represents the image itself and as such the
	 * image properties and file assets should be stored on the component's
	 * resource instead of a child resource
	 */
	public boolean isSelf() default false;

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
	 * URL where to upload the file, you can use <code>${suffix.path}</code>.
	 * Upload semantics are different between the legacy ExtJS API and the new
	 * Touch UI API.
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

	/**
	 * Used for Touch UI Only
	 *
	 * Maximum size of a file to be uploaded in Megabytes
	 *
	 * @return int
	 */
	public int sizeLimit() default 0;

}
