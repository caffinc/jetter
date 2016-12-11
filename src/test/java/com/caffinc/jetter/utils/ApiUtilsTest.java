package com.caffinc.jetter.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ApiUtils class
 *
 * @author Sriram
 * @since 12/10/2016
 */
public class ApiUtilsTest {
    @Test
    public void testGetIp() throws Exception {
        Assert.assertNotNull("IP Address must not be null", ApiUtils.getIp());
    }

    @Test
    public void testGetIpWithPresetMachineIp() throws Exception {
        System.setProperty("machine.ip", "localhost");
        Assert.assertEquals("IP Address must be localhost", "localhost", ApiUtils.getIp());
        System.clearProperty("machine.ip");
    }
}
