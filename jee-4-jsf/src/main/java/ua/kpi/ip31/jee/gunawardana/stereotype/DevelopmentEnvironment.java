package ua.kpi.ip31.jee.gunawardana.stereotype;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * CDI stereotype used to mark the beans alternatives for development stage.
 */
@Alternative
@Stereotype
@Documented
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface DevelopmentEnvironment {
}
