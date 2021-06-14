package com.example.myquiz.CreateTest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateQuestion extends Fragment {
    private Toolbar toolbar;
    private EditText qEdit;
    private EditText eAnswer1;
    private EditText eAnswer2;
    private EditText eAnswer3;
    private EditText eAnswer4;
    private CheckBox check1;
    private CheckBox check2;
    private CheckBox check3;
    private CheckBox check4;
    private List<QuizDto> list_quizDto;
    private ArrayList<QuestionDto> list_quest= new ArrayList<>();
    private String title="";
    private String description="";
    private Integer number_question=-1;
    private QuestionDto this_qustion=new QuestionDto();
    private Toast toast;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.create_question,
                container,
                false);
        toast =  Toast.makeText(getContext(),"Заполните все варианты ответов",Toast.LENGTH_SHORT);
        qEdit=rootView.findViewById(R.id.editTextQuestion);
        eAnswer1=rootView.findViewById(R.id.editTextAnswer1);
        eAnswer2=rootView.findViewById(R.id.editTextAnswer2);
        eAnswer3=rootView.findViewById(R.id.editTextAnswer3);
        eAnswer4=rootView.findViewById(R.id.editTextAnswer4);
        check1=rootView.findViewById(R.id.checkBox_answer1);
        check2=rootView.findViewById(R.id.checkBox_answer2);
        check3=rootView.findViewById(R.id.checkBox_answer3);
        check4=rootView.findViewById(R.id.checkBox_answer4);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            title=bundle.getString("Title", "");
            description=bundle.getString("description", "");
            number_question =bundle.getInt("number_question", -1);
            /*list_quest=(ArrayList<Question>) bundle.getParcelableArrayList("Array_questions");
            this_qustion.Quest=bundle.getString("Question");
            this_qustion.Answer1=bundle.getString("Answer1");
            this_qustion.Answer2=bundle.getString("Answer2");
            this_qustion.Answer3=bundle.getString("Answer3");
            this_qustion.Answer4=bundle.getString("Answer4");
            this_qustion.CorrectAnswer=bundle.getBooleanArray("CorrectAnswer");*/
            list_quest = bundle.getParcelableArrayList("Question_List");
            assert list_quest != null;
            if(list_quest.size()<=number_question){
                this_qustion=new QuestionDto();
            }else{
                this_qustion=list_quest.get(number_question);
            }
        }
        fill_object();

        toolbar = rootView.findViewById(R.id.toolbar_create_question);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_save_test:{
                        //сохранить вопрос, закрыть фрагмент
                        if(proverka_zapolneniya()){
                            get_data();
                            if(list_quest.size()<=number_question){
                                list_quest.add(this_qustion);
                            }else{
                                list_quest.set(number_question,this_qustion);
                            }
                            FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            CreateTest crTest= new CreateTest();
                            Bundle bundle = new Bundle();
                            bundle.putString("Title",title);
                            bundle.putString("description",description);
                            bundle.putInt("number_question",number_question);
                            bundle.putParcelableArrayList("Question_List",list_quest);
                            /*bundle.putString("Question",this_qustion.Quest);
                            bundle.putString("Answer1",this_qustion.Answer1);
                            bundle.putString("Answer2",this_qustion.Answer2);
                            bundle.putString("Answer3",this_qustion.Answer3);
                            bundle.putString("Answer4",this_qustion.Answer4);
                            bundle.putBooleanArray("CorrectAnswer",this_qustion.CorrectAnswer);*/
                            crTest.setArguments(bundle);
                            fragmentTransaction.replace(R.id.container_fragment, crTest);
                            int count = fragmentManager.getBackStackEntryCount();
                            for(int i = 0; i < count; ++i) {
                                fragmentManager.popBackStack();
                            }
                            fragmentTransaction.commit();
                        }
                        break;
                    }
                    default:{
                        break;
                    }
                }
                return true;
            }
        });


        // переделать
        /*RecyclerView recyclerTest = (RecyclerView)rootView.findViewById(R.id.create_test_recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerTest.setLayoutManager(layoutManager);

        CreateTestAdapter crTestAdapter = new CreateTestAdapter(list_quest);
        recyclerTest.setAdapter(crTestAdapter);*/
        return rootView;
    }
    public boolean proverka_zapolneniya(){
        if(qEdit.getText().toString().equals("")){
            toast.cancel();
            toast =  Toast.makeText(getContext(),"Введите вопрос",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if(eAnswer1.getText().toString().equals("")||eAnswer2.getText().toString().equals("")||eAnswer3.getText().toString().equals("")||eAnswer4.getText().toString().equals("")){
            toast.cancel();
            toast =  Toast.makeText(getContext(),"Заполните все варианты ответов",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if(!check1.isChecked() &&!check2.isChecked()&&!check3.isChecked()&&!check4.isChecked()){
            toast.cancel();
            toast =  Toast.makeText(getContext(),"Выберите правильный вариант",Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }
    public void get_data(){
        this_qustion.title =qEdit.getText().toString();
        this_qustion.answer.set(0,eAnswer1.getText().toString());
        this_qustion.answer.set(1,eAnswer2.getText().toString());
        this_qustion.answer.set(2,eAnswer3.getText().toString());
        this_qustion.answer.set(3,eAnswer4.getText().toString());
        if(check1.isChecked()){
            this_qustion.answer_correct.add(eAnswer1.getText().toString());
        }
        if(check2.isChecked()){
            this_qustion.answer_correct.add(eAnswer2.getText().toString());
        }
        if(check3.isChecked()){
            this_qustion.answer_correct.add(eAnswer3.getText().toString());
        }
        if(check4.isChecked()){
            this_qustion.answer_correct.add(eAnswer4.getText().toString());
        }
        /*this_qustion.Answer1=eAnswer1.getText().toString();
        this_qustion.Answer2=eAnswer2.getText().toString();
        this_qustion.Answer3=eAnswer3.getText().toString();
        this_qustion.Answer4=eAnswer4.getText().toString();
        this_qustion.CorrectAnswer[0]=check1.isChecked();
        this_qustion.CorrectAnswer[1]=check2.isChecked();
        this_qustion.CorrectAnswer[2]=check3.isChecked();
        this_qustion.CorrectAnswer[3]=check4.isChecked();*/
    }
    public boolean find_answer(String ans){
        for(int i = 0; i<this_qustion.answer_correct.size(); i++){
            if(ans.equals(this_qustion.answer_correct.get(i))){
                return true;
            }
        }
        return false;
    }
    public void fill_object(){
        qEdit.setText(this_qustion.title);
        /*String str = Integer. toString(number_question);
        qEdit.setText(str);*/
        eAnswer1.setText(this_qustion.answer.get(0));
        eAnswer2.setText(this_qustion.answer.get(1));
        eAnswer3.setText(this_qustion.answer.get(2));
        eAnswer4.setText(this_qustion.answer.get(3));

        check1.setChecked(find_answer(this_qustion.answer.get(0)));
        check2.setChecked(find_answer(this_qustion.answer.get(1)));
        check3.setChecked(find_answer(this_qustion.answer.get(2)));
        check4.setChecked(find_answer(this_qustion.answer.get(3)));

        this_qustion.answer_correct.clear();
    }

    @Override
    public void onPause() {
        super.onPause();

        //Log.d(TAG, "MainActivity: onPause()");
    }

}
