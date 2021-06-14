package com.example.myquiz.Network;



import com.example.myquiz.CreateTest.QuizDto;
import com.example.myquiz.PlayByCodeTest.GetTestClass;
import com.example.myquiz.PlayByCodeTest.QuizResultDto;
import com.example.myquiz.Profile.QuizProfile;
import com.example.myquiz.Profile.Result.ProfileRezClass;
import com.example.myquiz.Registration.RegistrationClass;
import com.example.myquiz.login.AuthorizationRequestDto;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {




    @GET("/auth/success")
    public Call<ResponseBody> getSuc(@Header("Authorization") String authToken);

    @GET("/quiz/get/{id}")
    public Call<GetTestClass>getTestById(@Path("id") long id, @Header("Authorization") String authToken);
    @GET("/quiz/get/my")
    public Call<ArrayList<QuizProfile>>getMyTest(@Header("Authorization") String authToken);
    @GET("/quiz/get/results")
    public Call<ArrayList<ProfileRezClass>>getMyTestResults(@Header("Authorization") String authToken);
    @POST("/auth/login")
    public  Call<Map<String,String>>login(@Body AuthorizationRequestDto authorizationRequestDto);


    @POST("/auth/registration")
    public  Call<Map<String,String>>reg(@Body RegistrationClass registrationClass);
    @POST("/quiz/create")
    public  Call<Map<String,String>>test_create(@Body QuizDto quizDto_class, @Header("Authorization") String authToken);
    @POST("/quiz/result")
    public  Call<Map<String,String>>test_check(@Body QuizResultDto quizResultDto, @Header("Authorization") String authToken);
}
