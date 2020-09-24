package com.ayata.mypackagesjava.retrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.ayata.mypackagesjava.retrofit.exception.NoConnectivityException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


public class NetworkConnectionInterceptor implements Interceptor {

    private Context mContext;

    public NetworkConnectionInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isConnected()) {
            throw new NoConnectivityException();
//             Throwing our custom exception 'NoConnectivityException'
        }

//        Request.Builder builder = chain.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
//        Request.Builder builder = chain.request().newBuilder();
        Response response=chain.proceed(chain.request());
        if (response.networkResponse() != null) {
            Log.d("responsefromsource", "Response from networkResponse(): " + response.networkResponse());
        } else if (response.cacheResponse() != null) {
            Log.d("responsefromsource", "Response from cacheControl(): " + response.cacheResponse());
        }

        return response;
//        return chain.proceed(builder.build());
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

}