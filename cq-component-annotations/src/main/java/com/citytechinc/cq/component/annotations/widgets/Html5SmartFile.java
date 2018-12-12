package com.citytechinc.cq.component.annotations.widgets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents a Widget of type CQ.html5.form.SmartFile in Classic UI
 * <p>
 * Represents a Widget of type granite/ui/components/foundation/form/fileupload
 * in Touch UI
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Html5SmartFile {

    /**
     * Used for Classic UI only
     * <p>
     * The field's HTML name attribute
     *
     * @return String
     */
    String name() default "";

    /**
     * Used for Classic UI only
     * <p>
     * True if the name of an uploaded file is editable
     *
     * @return boolean
     */
    boolean allowFileNameEditing() default true;

    /**
     * Used for Classic UI only
     * <p>
     * Flag if referencing a file is allowed
     *
     * @return boolean
     */
    boolean allowFileReference() default true;

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
     * MIME type definition of files that are allowed for referencing using drag
     * & drop
     *
     * @return String
     */
    String ddAccept() default "*";

    /**
     * Used for Classic UI only
     * <p>
     * Groups involved in drag & drop
     *
     * @return String[]
     */
    String[] ddGroups() default {};

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
    String fileNameParameter();

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
    String fileReferenceParameter();

    // TODO: Consider implementing footPanel if we move forward with panel
    // support

    // TODO: Consider implementing headPanel if we move forward with panel
    // support

    /**
     * Used for Classic UI only
     * <p>
     * MIME types allowed for uploading (each separated by a semicolon; wildcard
     * * is allowed; for example: "*.*" or "*.jpg;*.gif;*.png" (defaults to
     * "*.*".) Also support MIME type syntax; for example (image/jpg or image/*)
     *
     * @return String
     */
    String mimeTypes() default "*.*";

    /**
     * Used for Touch UI only
     * <p>
     * The list of mime types allowed for upload. This must be defined uniquely
     * for Touch UI as the ExtJS allows different extension and wild card
     * semantics than the Touch UI variant.
     *
     * @return String[]
     */
    String[] touchUIMimeTypes() default {};

    /**
     * Used for Classic UI only
     * <p>
     * A String that describes the allowed MIME types
     *
     * @return String
     */
    String mimeTypesDescription() default "All files";

    /**
     * Maximum size of a file to be uploaded in Megabytes
     *
     * @return int
     */
    int sizeLimit() default 0;

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
     * URL where to upload the file, you can use <code>${suffix.path}</code>
     *
     * @return String
     */
    String uploadUrl() default "${suffix.path}";

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
     * Used for Touch UI Coral3 Only
     * <p>
     * Set true to upload the files asynchronously
     *
     * @return boolean
     */
    boolean async() default false;

    /**
     * Used for Touch UI Coral3 Only
     * <p>
     * Visually hide the text. It is RECOMMENDED that every button has a text for a11y purpose.
     * Use this property to hide it visually, while still making it available for a11y.
     *
     * @return boolean
     */
    boolean hideText() default false;

    /**
     * Used for Touch UI Coral3 Only
     * <p>
     * The size of the icon - XS, S, M, L
     *
     * @return String
     */
    String iconSize() default "S";

    /**
     * Used for Touch UI Coral3 Only
     * <p>
     * The size of the button - M, L
     *
     * @return String
     */
    String size() default "M";
}