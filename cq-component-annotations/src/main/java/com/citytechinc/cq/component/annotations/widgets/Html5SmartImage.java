package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.AspectRatio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.html5.form.SmartImage in Classic UI
 * <p>
 * The Smart Image is not supported in Touch UI and will render as a
 * granite/ui/components/foundation/form/fileupload Widget
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Html5SmartImage {

    /**
     * Used for Classic UI only
     * <p>
     * True to not render the flush button.
     *
     * @return boolean
     */
    boolean disableFlush() default false;

    /**
     * Used for Classic UI only
     * <p>
     * True to hide the "information" tool
     *
     * @return boolean
     */
    boolean disableInfo() default false;

    /**
     * Used for Classic UI only
     * <p>
     * True to not render the zoom slider
     *
     * @return boolean
     */
    boolean disableZoom() default false;

    /**
     * Indicates that this component represents the image itself and as such the
     * image properties and file assets should be stored on the component's
     * resource instead of a child resource
     */
    boolean isSelf() default false;

    /**
     * Path to which files will be uploaded.
     *
     * @return String
     */
    String uploadUrl() default "/tmp/upload/*";

    /**
     * Used for Classic UI only
     * <p>
     * Groups involved in drag & drop
     *
     * @return String[]
     */
    String[] ddGroups() default { "media" };

    /**
     * Used for Classic UI only
     * <p>
     * Indication of whether the HTML5 Smart Image Widget should be rendered as
     * a stand alone tab.
     *
     * @return boolean
     */
    boolean tab() default true;

    /**
     * Used for Classic UI only
     * <p>
     * Height of the SmartImage component. Note: You must explicitly specify
     * height if you intend to render the HTML5 Smart Image Widget outside the
     * context of its own tab.
     *
     * @return int
     */
    int height() default 0;

    /**
     * Used for Classic UI only
     * <p>
     * Flag if uploading a file is allowed
     *
     * @return boolean
     */
    boolean allowUpload() default true;

    /**
     * Used for Classic UI only
     * <p>
     * Crop Config Aspect Ratios
     *
     * @return AspectRatio[]
     */
    AspectRatio[] cropAspectRatios() default {};

    /**
     * Used for Classic UI only
     * <p>
     * Method to turn on cropping and automatically set the cropParameter; this
     * simplifies {@link #cropParameter()}
     *
     * @return boolean
     */
    boolean allowCrop() default false;

    /**
     * Used for Classic UI only
     * <p>
     * Method to turn on rotating and automatically set the rotateParameter;
     * this simplifies {@link #rotateParameter()}
     *
     * @return boolean
     */
    boolean allowRotate() default false;

    /**
     * Used for Classic UI only
     * <p>
     * Method to turn on mapping and automatically set the mapParameter; this
     * simplifies {@link #mapParameter()}
     *
     * @return boolean
     */
    boolean allowMap() default false;

    /**
     * Used for Touch UI Only
     * <p>
     * Indicates that multiple files may be uploaded
     *
     * @return boolean
     */
    boolean multiple() default false;

    /**
     * Used for Touch UI Only
     * <p>
     * URL where to upload the file, you can use <code>${suffix.path}</code>.
     * Upload semantics are different between the legacy ExtJS API and the new
     * Touch UI API.
     *
     * @return String
     */
    String touchUIUploadUrl() default "${suffix.path}";

    /**
     * Used for Touch UI Only
     * <p>
     * The AEM documentation indicates that this should be an upload URL builder
     *
     * @return String
     */
    String uploadUrlBuilder() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * If <code>true</code>, upload starts automatically once the file is
     * selected
     *
     * @return boolean
     */
    boolean autoStart() default false;

    /**
     * Used for Touch UI Only
     * <p>
     * Prefer HTML5 to upload files (if browser allows it)
     *
     * @return boolean
     */
    boolean useHtml5() default true;

    /**
     * Used for Touch UI Only
     * <p>
     * Drop zone selector to upload files from file system directly (if browser
     * allows it)
     *
     * @return String
     */
    String dropZone() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * The title associated with the upload button
     *
     * @return String
     */
    String title() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * The text of the button
     *
     * @return String
     */
    String text() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * The icon of the button
     *
     * @return String
     */
    String icon() default "";

    /**
     * Used for Touch UI Only
     * <p>
     * Maximum size of a file to be uploaded in Megabytes
     *
     * @return int
     */
    int sizeLimit() default 0;

}
