package com.example.myquiz.Network;


import com.example.myquiz.login.AuthorizationRequestDto;
import com.example.myquiz.login.LoginMethods;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostLoginModel implements LoginMethods.Model {

    @Override
    public void LoginUser(final OnFinishedListener onFinishedListener, AuthorizationRequestDto authorizationRequestDto) {

        NetworkService.getInstance()
                .getJSONApi()
                .login(authorizationRequestDto)
                .enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if(response.isSuccessful()){
                            Map<String,String> map;
                            map = response.body();
                            onFinishedListener.onFinished(map);
                        }
                        else{
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
