package com.caffinc.jetter.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Utility methods for the API system
 *
 * @author Sriram
 * @since 12/10/2016
 */
public class ApiUtils {
    // Provider for Public IP
    private static final String IP_PROVIDER = "https://api.ipify.org/";

    /**
     * Private constructor
     */
    private ApiUtils() {
        // Do nothing
    }

    /**
     * Fetches the port that the server is running on
     *
     * @param server Jetty Server
     * @return Port that server is running on, 0 if server is stopped or null
     */
    public static int getPort(Server server) {
        if (server == null || !server.isStarted()) {
            return 0;
        }
        return ((ServerConnector) server.getConnectors()[0]).getLocalPort();
    }

    /**
     * Returns the public IP if one isn't specified in the <i>machine.ip</i> System property
     *
     * @return IP address if found, null otherwise
     * @throws IOException thrown if connection to third party service failed
     */
    public static String getIp() throws IOException {
        if (System.getProperty("machine.ip") != null) {
            return System.getProperty("machine.ip");
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new URL(IP_PROVIDER).openConnection().getInputStream()))) {
            String ip;
            if ((ip = br.readLine()) != null) {
                return ip;
            }
        }
        return null;
    }
}
