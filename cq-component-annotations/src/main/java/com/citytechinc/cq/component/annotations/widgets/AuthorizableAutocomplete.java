package com.citytechinc.cq.component.annotations.widgets;

import com.citytechinc.cq.component.annotations.widgets.authorizable.AuthorizableSelector;
import com.citytechinc.cq.component.annotations.widgets.authorizable.AuthorizableUserFilter;
import com.citytechinc.cq.component.annotations.widgets.authorizable.AuthorizableValueType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An autocomplete field that is designed to allow the user to pick the authorizable from suggested list.
 * <p>
 * Represents widget of type granite/ui/components/coral/foundation/authorizable/autocomplete.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface AuthorizableAutocomplete {

    /**
     * A hint to the user of what can be entered in the field.
     */
    String emptyText() default "";

    /**
     * The name of the validator to be applied. E.g. `foundation.jcr.name`.
     */
    String[] validation() default {};

    /**
     * Indicates if the user is able to select multiple selections.
     */
    boolean multiple() default false;

    /**
     * Indicates if the user must only select from the list of given options.  If it is not forced, the user can enter
     * arbitrary value.
     */
    boolean forceSelection() default true;

    /**
     * The type of value to be submitted:
     * <ul>
     * <li>id Use authorizable ID</li>
     * <li>path Use authorizable home path</li>
     * <li>principalname Use principal name</li>
     * </ul>
     */
    AuthorizableValueType valueType() default AuthorizableValueType.ID;

    /**
     * The selector of authorizable:
     * <ul>
     * <li>all Show all authorizables</li>
     * <li>user Show only users</li>
     * <li>group Show only groups</li>
     * </ul>
     */
    AuthorizableSelector selector() default AuthorizableSelector.ALL;

    /**
     * Filter for service user:
     * <ul>
     * <li>off Turn off the filter to show all users</li>
     * <li>includeonly Include only the service users</li>
     * <li>exclude Exclude the service users</li>
     * </ul>
     */
    AuthorizableUserFilter serviceUserFilter() default AuthorizableUserFilter.OFF;

    /**
     * Filter for impersonable user:
     * <ul>
     * <li>off Turn off the filter to show all users</li>
     * <li>includeonly Include only the users that can be impersonated by the current user</li>
     * <li>exclude Exclude the users that can be impersonated by the current user</li>
     * </ul>
     */
    AuthorizableUserFilter impersonableUserFilter() default AuthorizableUserFilter.OFF;
}
