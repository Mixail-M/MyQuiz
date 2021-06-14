package com.example.myquiz.PlayByCodeTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquiz.R;
import com.example.myquiz.act_main;

public class Activity_rez_test extends AppCompatActivity implements View.OnClickListener{
    TextView textRez;
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rez_test);
        Bundle arguments = getIntent().getExtras();
        textRez = findViewById(R.id.textViewResult);
        btnMain = findViewById(R.id.button_go_to_main_act);
        btnMain.setOnClickListener(this);
        if(arguments!=null){
            textRez.setText(arguments.getString("Answer","null"));
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_go_to_main_act){
            btnMain.setClickable(false);
            Intent intent = new Intent(this, act_main.class);
            startActivity(intent);
            //открыть главную страницу
        }
    }
}
