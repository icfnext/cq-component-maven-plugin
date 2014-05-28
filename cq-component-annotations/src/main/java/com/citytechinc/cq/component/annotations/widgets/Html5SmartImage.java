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
 * CQ.html5.form.SmartImage
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Html5SmartImage {

	/**
	 * True to not render the flush button.
	 *
	 * @return boolean
	 */
	public boolean disableFlush() default false;

	/**
	 * True to hide the "information" tool
	 *
	 * @return boolean
	 */
	public boolean disableInfo() default false;

	/**
	 * True to not render the zoom slider
	 *
	 * @return boolean
	 */
	public boolean disableZoom() default false;

	/**
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
	 * Groups involved in drag & drop
	 *
	 * @return String[]
	 */
	public String[] ddGroups() default { "media" };

	/**
	 * Indication of whether the HTML5 Smart Image Widget should be rendered as
	 * a stand alone tab.
	 *
	 * @return boolean
	 */
	public boolean tab() default true;

	/**
	 * Height of the SmartImage component. Note: You must explicitly specify
	 * height if you intend to render the HTML5 Smart Image Widget outside the
	 * context of its own tab.
	 *
	 * @return int
	 */
	public int height() default 0;

	/**
	 * Flag if uploading a file is allowed
	 *
	 * @return boolean
	 */
	public boolean allowUpload() default true;

	/**
	 * Crop Config Aspect Ratios
	 * 
	 * @return AspectRatio[]
	 */
	public AspectRatio[] cropAspectRatios() default {};

	/**
	 * Method to turn on cropping and automatically set the cropParameter; this
	 * simplifies {@link #cropParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowCrop() default false;

	/**
	 * Method to turn on rotating and automatically set the rotateParameter;
	 * this simplifies {@link #rotateParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowRotate() default false;

	/**
	 * Method to turn on mapping and automatically set the mapParameter; this
	 * simplifies {@link #mapParameter()}
	 * 
	 * @return boolean
	 */
	public boolean allowMap() default false;

}
