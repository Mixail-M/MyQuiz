package com.example.myquiz.Network;


import com.example.myquiz.PlayByCodeTest.CheckTestMethods;
import com.example.myquiz.PlayByCodeTest.QuizResultDto;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostCheckTestModel implements CheckTestMethods.Model {

    @Override
    public void CheckTest(final OnFinishedListener onFinishedListener, QuizResultDto quizResultDto, String token) {

        NetworkService.getInstance()
                .getJSONApi()
                .test_check(quizResultDto, token)
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
