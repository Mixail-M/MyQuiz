package com.example.myquiz.PlayByCodeTest;





import com.example.myquiz.CreateTest.CreateTestMethods;
import com.example.myquiz.CreateTest.QuizDto;

import java.util.Map;

public class CheckTestPresenter implements CheckTestMethods.Presenter, CheckTestMethods.Model.OnFinishedListener {
    private CheckTestMethods.View view;
    private CheckTestMethods.Model model;

    public CheckTestPresenter(CheckTestMethods.View view, CheckTestMethods.Model model) {
        this.view = view;
        this.model = model;
    }



    @Override
    public void sendPostToCheckTest(QuizResultDto quizResultDto, String token) {
        model.CheckTest(this, quizResultDto, token);
    }



    @Override
    public void onFinished(Map<String, String> map) {
        if(map!=null){
            view.check_test(map);
        }
    }

    @Override
    public void onFailure(String t) {
        view.onResponseFailRule(t);
    }
}
