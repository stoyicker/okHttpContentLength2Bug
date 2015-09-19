package com.github.okhttprequestbodylengthbug;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.junit.Test;

import java.io.IOException;

import retrofit.Retrofit;

import static junit.framework.Assert.assertEquals;

public class ContentLengthTest {

    @Test
    public void testContentLength() throws IOException {
        final Retrofit retrofit = DummyClient.getRetrofit();

        retrofit.client().networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request request = chain.request();

                final RequestBody body = request.body();

                assertEquals(BuildConfig.TEST_BODY_CONTENT.length(), body.contentLength());

                return chain.proceed(request);
            }
        });

        retrofit.create(DummyClient.IDummyService.class).dummyRequest(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                BuildConfig.TEST_BODY_CONTENT)).execute();
    }
}
