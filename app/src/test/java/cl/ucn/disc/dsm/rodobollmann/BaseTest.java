/*
 * Copyright (c) 2021. Rodolfo Bollmann Checura
 */

package cl.ucn.disc.dsm.rodobollmann;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class.
 * @author Rodolfo Bollmann Checura
 */

public abstract class BaseTest {

    /**
     * @param obj to transform.
     * @return the String view of the object
     */
    protected static String toString(final Object obj) {
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE);
    }
}
