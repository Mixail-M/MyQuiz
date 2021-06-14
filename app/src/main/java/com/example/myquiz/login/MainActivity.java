package com.example.myquiz.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquiz.Network.PostLoginModel;
import com.example.myquiz.R;
import com.example.myquiz.Registration.Registration;
import com.example.myquiz.Singleton;
import com.example.myquiz.act_main;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginMethods.View {
    EditText email, pass;
    Button loginBtn;
    ProgressDialog pd;
    final String SAVED_TOKEN = "Saved_token";
    final String SAVED_REFRESH_TOKEN = "Saved_refresh";
    SharedPreferences savenToken;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor ed;
    Toast toast;
    TextView registrationView;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.button);
        loginBtn.setOnClickListener(this);
        registrationView = findViewById(R.id.textView2);
        registrationView.setOnClickListener(this);
        toast =  Toast.makeText(this,"Поля должны быть заполнены",Toast.LENGTH_SHORT);
        loginPresenter = new LoginPresenter(this, new PostLoginModel());
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.textView2){
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button){
            /*Intent intent = new Intent(this, act_main.class);
            startActivity(intent);*/
            String emailS, passS;
            emailS = email.getText().toString();
            passS = pass.getText().toString();
            if(!emailS.equals("") && !passS.equals("")) {
                loginBtn.setClickable(false);
                AuthorizationRequestDto authorizationRequestDto = new AuthorizationRequestDto(email.getText().toString(), pass.getText().toString());
                showPd();
                loginPresenter.sendPostToLoginUser(authorizationRequestDto);
            }
            else {
                toast.cancel();
                toast = Toast.makeText(this, "Поля должны быть заполнены", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    void toMainActivity(){
        savenToken = PreferenceManager.getDefaultSharedPreferences(this);
        editor = savenToken.edit();
        Intent intent = new Intent(this, act_main.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void setToSharedPreferences(Map<String, String> map) {
        savenToken = PreferenceManager.getDefaultSharedPreferences(this);
        ed = savenToken.edit();
        ed.putString(SAVED_TOKEN,"Bearer " +map.get("answer"));


        ed.putString(SAVED_REFRESH_TOKEN,map.get("token_refresh"));
        ed.commit();
        hidePd();
        Singleton.getInstance().SetToken(savenToken.getString(SAVED_TOKEN,""));
        toast.cancel();
        toast =  Toast.makeText(this,Singleton.getInstance().GetToken(),Toast.LENGTH_SHORT);
        toast.show();
        toMainActivity();
    }

    @Override
    public void showPd() {
        pd = new ProgressDialog(this);
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
        if(s.equals("")){
            toast.cancel();
            toast =  Toast.makeText(this,"Неверный логин или пароль",Toast.LENGTH_SHORT);
            toast.show();
            loginBtn.setClickable(true);
        }
        else {
            toast.cancel();
            toast =  Toast.makeText(this,"Ошибка сервера: " + s,Toast.LENGTH_SHORT);
            toast.show();
            loginBtn.setClickable(true);
        }
    }
}