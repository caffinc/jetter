package com.caffinc.jetter.helper;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;

/**
 * Client for the <code>DummyResource</code>
 *
 * @author Sriram
 * @since 5/13/2016
 */
public class DummyClient {
    private interface DummyApi {
        @GET("/dummy")
        Call<DummyResponse> get(@Query("msg") String msg);
    }

    private DummyApi client;

    public DummyClient(int port) {
        client = new Retrofit.Builder().baseUrl("http://localhost:" + port)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(DummyApi.class);
    }

    public DummyResponse get(String msg) throws IOException {
        return client.get(msg).execute().body();
    }
}
