package com.example.myquiz.PlayByCodeTest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquiz.CreateTest.CreateTestPresenter;
import com.example.myquiz.Network.PostCheckTestModel;
import com.example.myquiz.Network.PostCreateTestModel;
import com.example.myquiz.R;
import com.example.myquiz.Registration.Registration;
import com.example.myquiz.Singleton;

import java.util.ArrayList;
import java.util.Map;

public class activity_test extends AppCompatActivity implements View.OnClickListener, CheckTestMethods.View{
    TextView textQuestion;
    TextView textAnswer1;
    TextView textAnswer2;
    TextView textAnswer3;
    TextView textAnswer4;
    boolean[] cor_answ;
    GetTestClass gtClass;
    QuizResultDto qrClass;
    Button btnNext;
    Toast toast;
    int position;
    private CheckTestPresenter ctPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Bundle arguments = getIntent().getBundleExtra("extra");
        cor_answ=new boolean[]{false,false,false,false};
        toast =  Toast.makeText(this,"Заполните все варианты ответов",Toast.LENGTH_SHORT);
        gtClass = new GetTestClass();
        qrClass = new QuizResultDto();
        textQuestion = findViewById(R.id.textViewQuestion);
        textAnswer1=findViewById(R.id.textViewAnswer1);
        textAnswer1.setOnClickListener(this);
        textAnswer2=findViewById(R.id.textViewAnswer2);
        textAnswer2.setOnClickListener(this);
        textAnswer3=findViewById(R.id.textViewAnswer3);
        textAnswer3.setOnClickListener(this);
        textAnswer4=findViewById(R.id.textViewAnswer4);
        textAnswer4.setOnClickListener(this);
        btnNext=findViewById(R.id.button_next_question);
        btnNext.setOnClickListener(this);
        ctPresenter=new CheckTestPresenter(this,new PostCheckTestModel());
        position = 0;
        if(arguments!=null){
            gtClass.id=arguments.getString("id_quiz");
            gtClass.title=arguments.getString("Title");
            gtClass.description=arguments.getString("description");
            gtClass.questionArrayList = arguments.getParcelableArrayList("Array_questions");

        }
        qrClass.id_quiz=gtClass.id;
        assert gtClass.questionArrayList != null;
        if(gtClass.questionArrayList.size()>0){
            fill_object(0);
        }
    }
    public void fill_object(int number_question){
        if(number_question<gtClass.questionArrayList.size()){
            textQuestion.setText(gtClass.questionArrayList.get(number_question).question);
            textAnswer1.setText(gtClass.questionArrayList.get(number_question).answerList.get(0).answer);
            textAnswer1.setBackgroundResource(R.color.colorBackground_answer_false);
            cor_answ[0]=false;
            textAnswer2.setText(gtClass.questionArrayList.get(number_question).answerList.get(1).answer);
            textAnswer2.setBackgroundResource(R.color.colorBackground_answer_false);
            cor_answ[1]=false;
            textAnswer3.setText(gtClass.questionArrayList.get(number_question).answerList.get(2).answer);
            textAnswer3.setBackgroundResource(R.color.colorBackground_answer_false);
            cor_answ[2]=false;
            textAnswer4.setText(gtClass.questionArrayList.get(number_question).answerList.get(3).answer);
            textAnswer4.setBackgroundResource(R.color.colorBackground_answer_false);
            cor_answ[3]=false;
        }
        else{
            send_request();
        }
    }
    private void send_request(){
        String token = Singleton.getInstance().GetToken();
        ctPresenter.sendPostToCheckTest(qrClass,token);
        Toast.makeText(this,"Запрос отправлен",Toast.LENGTH_LONG).show();
    }
    public void fill_qrClass(int number_question){
        QuestionResultDto qustionClass = new QuestionResultDto();
        qustionClass.id_question=gtClass.questionArrayList.get(number_question).id;
        for(int i=0;i<4;i++){
            if(cor_answ[i]){
                qustionClass.answer_id.add(gtClass.questionArrayList.get(number_question).answerList.get(i).id);
            }
        }
        qrClass.questionResultDtoList.add(qustionClass);
    }
    public boolean proverka(){
        for(int i = 0; i<4; i++){
            if(cor_answ[i]){
                return true;
            }
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.textViewAnswer1:{
                if(cor_answ[0]){
                    cor_answ[0]=false;
                    textAnswer1.setBackgroundResource(R.color.colorBackground_answer_false);
                }else{
                    cor_answ[0]=true;
                    textAnswer1.setBackgroundResource(R.color.colorBackground_answer_true);
                }
                break;
            }
            case R.id.textViewAnswer2:{
                if(cor_answ[1]){
                    cor_answ[1]=false;
                    textAnswer2.setBackgroundResource(R.color.colorBackground_answer_false);
                }else{
                    cor_answ[1]=true;
                    textAnswer2.setBackgroundResource(R.color.colorBackground_answer_true);
                }
                break;
            }
            case R.id.textViewAnswer3:{
                if(cor_answ[2]){
                    cor_answ[2]=false;
                    textAnswer3.setBackgroundResource(R.color.colorBackground_answer_false);
                }else{
                    cor_answ[2]=true;
                    textAnswer3.setBackgroundResource(R.color.colorBackground_answer_true);
                }
                break;
            }
            case R.id.textViewAnswer4:{
                if(cor_answ[3]){
                    cor_answ[3]=false;
                    textAnswer4.setBackgroundResource(R.color.colorBackground_answer_false);
                }else{
                    cor_answ[3]=true;
                    textAnswer4.setBackgroundResource(R.color.colorBackground_answer_true);
                }
                break;
            }
            case R.id.button_next_question:{
                int x=position+1;
                if(x<=gtClass.questionArrayList.size()){
                    if(proverka()){
                        fill_qrClass(position);
                        position=position+1;
                        fill_object(position);
                    }else{
                        Toast.makeText(this,"Выберите хотя бы один ответ",Toast.LENGTH_LONG).show();
                    }

                }else{
                    btnNext.setClickable(false);
                    send_request();
                }
                break;
            }
            default:{
                break;
            }
        }

    }

    @Override
    public void check_test(Map<String, String> map) {
        toast.cancel();
        toast =  Toast.makeText(this,map.get("answer"),Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this,Activity_rez_test.class);
        intent.putExtra("Answer",map.get("answer"));
        startActivity(intent);
        //открыть другое активити
    }



    @Override
    public void onResponseFailRule(String s) {
        toast.cancel();
        toast =  Toast.makeText(this,"Ошибка: " + s,Toast.LENGTH_SHORT);
        toast.show();
    }
}
