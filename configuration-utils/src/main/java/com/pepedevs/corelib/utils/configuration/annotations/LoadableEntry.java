package com.pepedevs.corelib.utils.configuration.annotations;

import org.apache.commons.lang.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LoadableEntry {

    String key();

    /**
     * Gets the name of the sub-section where this is located.
     *
     * <p>
     *
     * @return Name of the sub-section where this is located.
     */
    String subsection() default StringUtils.EMPTY;
}
