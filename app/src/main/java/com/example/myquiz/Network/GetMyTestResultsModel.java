package com.example.myquiz.Network;


import com.example.myquiz.Profile.QuizProfile;
import com.example.myquiz.Profile.Result.GetMyTestResultsMethods;
import com.example.myquiz.Profile.Result.ProfileRezClass;
import com.example.myquiz.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyTestResultsModel implements GetMyTestResultsMethods.GetMyTestResultsModel {

    @Override
    public void getMyTestResults(final OnFinishedListener onFinishedListener) {
        NetworkService.getInstance().getJSONApi().getMyTestResults(Singleton.getInstance().GetToken())
                .enqueue(new Callback<ArrayList<ProfileRezClass>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ProfileRezClass>> call, Response<ArrayList<ProfileRezClass> > response) {
                        if(response.isSuccessful()){
                            onFinishedListener.onFinish(response.body());
                        }
                        else{
                            onFinishedListener.onFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ProfileRezClass>> call, Throwable t) {
                        onFinishedListener.onFailure();
                    }
                });
    }

}
