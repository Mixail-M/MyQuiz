package com.example.myquiz.Network;



import com.example.myquiz.Registration.RegisterMethods;
import com.example.myquiz.Registration.RegistrationClass;

import java.util.Map;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRegisterModel implements RegisterMethods.Model {
    @Override
    public void registerUser(final RegisterMethods.Model.OnFinishedListener onFinishedListener, RegistrationClass registrationClass) {
        NetworkService.getInstance()
                .getJSONApi()
                .reg(registrationClass)
                .enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Map<String,String> map;
                    map = response.body();
                    assert map != null;
                    int l = Objects.requireNonNull(map.get("answer")).toString().length();
                    if(l<=27) {
                        onFinishedListener.onFinished(map);
                    } else{
                        onFinishedListener.onFailure("fail");
                    }

                } else {
                   onFinishedListener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                onFinishedListener.onFailure(t.getMessage());
            }

        });

    }
}
