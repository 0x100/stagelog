/*
 * MIT License
 *
 * Copyright (c) 2020 Ilya Lysenko
 *
 * A short and simple permissive license with conditions only requiring preservation of copyright and license notices.
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */
package com.ilysenko.stagelog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Stage {
    /**
     * Alias for the {@link Stage#startMessage()} param
     */
    String value() default "";

    /**
     * The message logged before start of the method
     */
    String startMessage() default "";

    /**
     * The message logged after finish of the method
     */
    String finishMessage() default "";

    /**
     * Track time spent by the method
     */
    boolean trackTime() default false;
}
