package com.example.myquiz.Network;


import com.example.myquiz.Profile.GetMyTestMethods;
import com.example.myquiz.Profile.QuizProfile;
import com.example.myquiz.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyTestModel implements GetMyTestMethods.GetMyTestModel {

    @Override
    public void getMyTest(final OnFinishedListener onFinishedListener) {
        NetworkService.getInstance().getJSONApi().getMyTest(Singleton.getInstance().GetToken())
                .enqueue(new Callback<ArrayList<QuizProfile>>() {
                    @Override
                    public void onResponse(Call<ArrayList<QuizProfile>> call, Response<ArrayList<QuizProfile> > response) {
                        if(response.isSuccessful()){
                            onFinishedListener.onFinish(response.body());
                        }
                        else{
                            onFinishedListener.onFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<QuizProfile>> call, Throwable t) {
                        onFinishedListener.onFailure();
                    }
                });
    }

}
