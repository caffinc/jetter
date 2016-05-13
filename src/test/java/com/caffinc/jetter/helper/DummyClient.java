package com.caffinc.jetter.helper;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Sriram
 * @since 5/13/2016
 */
public class DummyClient {
    private interface DummyApi {
        @GET("/dummy")
        DummyResponse get(@Query("msg") String msg);
    }

    private DummyApi client;

    public DummyClient(int port) {
        client = new RestAdapter.Builder().setEndpoint("http://localhost:" + port).build().create(DummyApi.class);
    }

    public DummyResponse get(String msg) {
        return client.get(msg);
    }
}
