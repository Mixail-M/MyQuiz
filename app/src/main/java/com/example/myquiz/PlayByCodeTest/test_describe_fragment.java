package com.example.myquiz.PlayByCodeTest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myquiz.R;

import java.util.ArrayList;

public class test_describe_fragment extends Fragment implements View.OnClickListener{
    TextView textTitle;
    TextView textDescribe;
    Button btn_start;
    GetTestClass gtClass;
    Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.test_fragment,
                container,
                false);
        textTitle = rootView.findViewById(R.id.textViewTitle);
        textDescribe = rootView.findViewById(R.id.textViewDescription);
        gtClass = new GetTestClass();
        bundle = this.getArguments();
        if(bundle!=null){
            gtClass.id=bundle.getString("id_quiz", "");
            gtClass.title=bundle.getString("Title", "");
            gtClass.description=bundle.getString("description", "");
            gtClass.questionArrayList = bundle.getParcelableArrayList("Array_questions");
        }
        fill_object();
        btn_start=rootView.findViewById(R.id.button_go_test);
        btn_start.setOnClickListener(this);
        return rootView;
    }
    public void fill_object(){
        textTitle.setText(gtClass.title);
        textDescribe.setText(gtClass.description);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_go_test){
            Bundle extra = new Bundle();
            extra.putString("id_quiz", gtClass.id);
            extra.putString("Title", gtClass.title);
            extra.putString("description",gtClass.description);
            ArrayList<Question> q=gtClass.questionArrayList;
            extra.putParcelableArrayList("Array_questions", q);
            Intent intent = new Intent(getActivity(), activity_test.class);
            intent.putExtra("extra",extra);
            /*intent.putExtra("id_quiz",gtClass.id);
            intent.putExtra("Title",gtClass.title);
            intent.putExtra("description",gtClass.description);
            intent.putExtra("Array_questions",gtClass.questionArrayList);*/
            startActivity(intent);
        }
    }
}
