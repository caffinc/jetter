package com.caffinc.jetter.utils;

/**
 * @author Sriram
 * @since 4/26/2016
 */
public class Utils {

    /**
     * Returns property if it is non-null, else returns the default val
     *
     * @param property   the property
     * @param defaultVal the default val
     * @return either property or default value
     */
    public static <T> T getOrDefault(T property, T defaultVal) {
        return property != null ? property : defaultVal;
    }


}
