package com.ayata.mypackagesjava.retrofit;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {
    public static Retrofit retrofit = null;
    static String TAG = "RetrofitBuilder";
    static final String BASE_URL = "https://nepal-weather-api.herokuapp.com/";

    private static Retrofit getRetrofitClient(Context mContext) {
        if (retrofit == null) {

            OkHttpClient oktHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new NetworkConnectionInterceptor(mContext))
//                    .cache(null)
                    .build();

            // Adding NetworkConnectionInterceptor with okHttpClientBuilder.
//            oktHttpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient)
                    .build();

        }
        return retrofit;
    }

    public static myApi getApi(Context context) {
        getRetrofitClient(context);
        return retrofit.create(myApi.class);
    }
}
