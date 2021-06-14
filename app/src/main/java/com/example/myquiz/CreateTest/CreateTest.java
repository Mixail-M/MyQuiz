package com.example.myquiz.CreateTest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myquiz.Network.PostCreateTestModel;
import com.example.myquiz.R;
import com.example.myquiz.Singleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CreateTest extends Fragment implements CreateTestMethods.View{
    private Toolbar toolbar;
    private EditText title;
    private EditText description;
    public QuizDto quizDto;
    private ArrayList<QuestionDto> list_quest= new ArrayList<>();
    private RecyclerView recyclerTest;
    private Toast toast;
    private CreateTestPresenter crPresenter;
    ProgressDialog pd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.create_test,
                container,
                false);
        toast =  Toast.makeText(getContext(),"Заполните все варианты ответов",Toast.LENGTH_SHORT);
        recyclerTest= (RecyclerView)rootView.findViewById(R.id.create_test_recycler);
        toolbar = rootView.findViewById(R.id.toolbar);
        title=rootView.findViewById(R.id.editTextName_Test);
        description = rootView.findViewById(R.id.editTextDescription);
        quizDto =new QuizDto();
        crPresenter=new CreateTestPresenter(this,new PostCreateTestModel());
        //title.setText("123");
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            quizDto.title =bundle.getString("Title","");
            quizDto.description=bundle.getString("description","");
            int number=bundle.getInt("number_question",-1);
            if(number!=-1){
                quizDto.question =bundle.getParcelableArrayList("Question_List");
                /*Question q= new Question();
                q.Quest=bundle.getString("Question");
                q.Answer1=bundle.getString("Answer1");
                q.Answer2=bundle.getString("Answer2");
                q.Answer3=bundle.getString("Answer3");
                q.Answer4=bundle.getString("Answer4");
                q.CorrectAnswer=bundle.getBooleanArray("CorrectAnswer");
                if(test.question_list.size()<=number){
                    test.question_list.add(q);
                }else{
                    test.question_list.set(number,q);
                }*/
            }
            fill_object();
        }
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_save_test:{
                        if(!quizDto.title.equals("")&&!quizDto.description.equals("")&& quizDto.question.size()>0){
                            String token = Singleton.getInstance().GetToken();
                            toolbar.setClickable(false);
                            String str = title.getText().toString();
                            toast.cancel();
                            toast =  Toast.makeText(getContext(),str,Toast.LENGTH_SHORT);
                            toast.show();
                            showPd();
                            crPresenter.sendPostToSaveTest(quizDto,token);
                        }else{
                            toast.cancel();
                            toast =  Toast.makeText(getContext(),"Не достаточно данных для создания теста, заполните все поля и создайте хотя бы один вопрос",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        //сохранить тест, закрыть фрагмент
                        break;
                    }
                    default:{
                        break;
                    }
                }
                return true;
            }
        });
        FloatingActionButton fab = rootView.findViewById(R.id.fab_create_question);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_create_question(quizDto.question.size());
            }
        });

        return rootView;
    }
    public void to_create_question(int number){
        get_data();
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CreateQuestion crQuest= new CreateQuestion();
        Bundle bundle = new Bundle();
        bundle.putString("Title", quizDto.title);
        bundle.putString("description", quizDto.description);
        bundle.putParcelableArrayList("Array_questions", (ArrayList<? extends Parcelable>) quizDto.question);
        bundle.putInt("number_question",number);
        bundle.putParcelableArrayList("Question_List", quizDto.question);
                /*bundle.putString("Question","");
                bundle.putString("Answer1","");
                bundle.putString("Answer2","");
                bundle.putString("Answer3","");
                bundle.putString("Answer4","");
                boolean [] bol= new boolean[]{false,false,false,false};
                bundle.putBooleanArray("CorrectAnswer",bol);*/
        crQuest.setArguments(bundle);
        fragmentTransaction.replace(R.id.container_fragment, crQuest);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void fill_object(){
        title.setText(quizDto.title);
        description.setText(quizDto.description);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerTest.setLayoutManager(layoutManager);
        CreateTestAdapter crTestAdapter = new CreateTestAdapter(quizDto.question,this);
        recyclerTest.setAdapter(crTestAdapter);
    }
    public void get_data(){
        EditText ed = (EditText) Objects.requireNonNull(getView()).findViewById(R.id.editTextName_Test);
        String t=ed.getText().toString();
        String r=title.getText().toString();
        quizDto.title =title.getText().toString();
        quizDto.description=description.getText().toString();
    }
    @Override
    public void save_test(Map<String, String> map) {
        toast.cancel();
        toast =  Toast.makeText(getContext(),map.get("answer"),Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showPd() {
        pd = new ProgressDialog(getContext());
        pd.setTitle("Подождите");
        pd.setMessage("Вход...");
        pd.show();
    }

    @Override
    public void hidePd() {
        pd.dismiss();
    }

    @Override
    public void onResponseFailRule(String s) {
        hidePd();
        toast.cancel();
        toast =  Toast.makeText(getContext(),"Ошибка сервера: " + s,Toast.LENGTH_SHORT);
        toast.show();
        toolbar.setClickable(true);

    }
    @Override
    public void onPause() {
        super.onPause();

        //Log.d(TAG, "MainActivity: onPause()");
    }

}
