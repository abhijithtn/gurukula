package com.asr2.gurukula.commons.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by abhijith.nagarajan on 9/16/15.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Searchable {

	SearchOperator operator() default SearchOperator.LIKE;

	enum SearchOperator {EQUALS, LIKE}
}
