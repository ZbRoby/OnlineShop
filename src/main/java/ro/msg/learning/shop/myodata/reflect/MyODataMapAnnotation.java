package ro.msg.learning.shop.myodata.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * <p>
 * Marks the class as a mappable.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyODataMapAnnotation {

    /**
     * Any fields listed here will not be mapped .
     * Mutually exclusive with {@link #of()}.
     *
     * @return A list of fields to exclude (<em>default</em>: none of them).
     */

    String[] exclude() default {};

    /**
     * If present, explicitly lists the fields that are to be mapped.
     * Normally, all non-static fields are mapped.
     * <p>
     * Mutually exclusive with {@link #exclude()}.
     *
     * @return A list of fields to map (<em>default</em>: all of them).
     */
    String[] of() default {};
}
