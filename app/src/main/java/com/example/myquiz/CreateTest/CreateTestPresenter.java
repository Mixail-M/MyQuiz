package com.example.myquiz.CreateTest;





import com.example.myquiz.Network.PostCheckTestModel;
import com.example.myquiz.PlayByCodeTest.activity_test;

import java.util.Map;

public class CreateTestPresenter implements CreateTestMethods.Presenter, CreateTestMethods.Model.OnFinishedListener {
    private CreateTestMethods.View view;
    private CreateTestMethods.Model model;

    public CreateTestPresenter(CreateTestMethods.View view, CreateTestMethods.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void sendPostToSaveTest(QuizDto quizDto_class, String token) {
        model.SaveTest(this, quizDto_class, token);
    }



    @Override
    public void onFinished(Map<String, String> map) {
        if(map!=null){
            view.save_test(map);
        }
    }

    @Override
    public void onFailure(String t) {
        view.onResponseFailRule(t);
    }
}
