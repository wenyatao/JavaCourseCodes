package com.wyt.week02;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OKHttp {

    public static void main(String[] args) throws Exception {
        asyncGet();
    }


    public static void run() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8808/test")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        System.out.println(response.body().string());
    }

    /**
     * 异步请求
     * @throws Exception
     */
    public static void asyncGet() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8808/test")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().string());
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }


        });
    }



}
