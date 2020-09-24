package com.ayata.mypackagesjava.retrofit;

import android.os.Bundle;
import android.util.Log;

import com.ayata.mypackagesjava.R;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
Internet Permission :<uses-permission android:name="android.permission.INTERNET" />
add compile option{} in app.gradle
add retrofit and gson dependency
*/

public class RetrofitActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Call<Weather> weatherCall = RetrofitApiClient.getApi(this).getWeatherDetail("kathmandu");
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    Weather weather = response.body();
                    Log.d(TAG, "onResponse: " + response.message());
                    Log.d(TAG, "onResponse: " + weather.getPlace());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "onFailure: api call failed");
            }
        });
    }
}
