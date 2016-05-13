package com.caffinc.jetter;

import com.caffinc.jetter.helper.DummyClient;
import com.caffinc.jetter.helper.DummyResource;
import org.eclipse.jetty.server.Server;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Tests the API class
 *
 * @author Sriram
 * @since 4/28/2016
 */
public class ApiTest {
    @Test
    public void testApi() throws Exception {
        final int port = 10000 + (new Random()).nextInt(50000);
        final String testMessage = "Everything's alright cap'n!";
        final Server server = new Api(port).setBaseUrl("/").addServiceResource(DummyResource.class).startNonBlocking();
        while (!server.isStarted()) {
            Thread.sleep(100);
        }
        final DummyClient client = new DummyClient(port);
        Assert.assertEquals(testMessage, client.get(testMessage).getMessage());
    }
}
