package com.example.myquiz.Network;


import com.example.myquiz.PlayByCodeTest.GetTestClass;
import com.example.myquiz.PlayByCodeTest.PlayByCodeMethods;
import com.example.myquiz.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetByIdModel implements PlayByCodeMethods.PlayByCodeModel {

    @Override
    public void getTestById(final OnFinishedListener onFinishedListener, long id) {
        NetworkService.getInstance().getJSONApi().getTestById(id, Singleton.getInstance().GetToken())
                .enqueue(new Callback<GetTestClass>() {
                    @Override
                    public void onResponse(Call<GetTestClass> call, Response<GetTestClass> response) {
                        if(response.isSuccessful()){
                            onFinishedListener.onFinish(response.body());
                        }
                        else{
                            onFinishedListener.onFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTestClass> call, Throwable t) {
                        onFinishedListener.onFailure();
                    }
                });
    }

}
