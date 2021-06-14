package com.example.myquiz.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myquiz.Network.PostRegisterModel;
import com.example.myquiz.R;
import com.example.myquiz.login.MainActivity;

import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener, RegisterMethods.View{
    EditText email, pass,r_pass,  fio;
    Button registerButton;
    ProgressDialog pd;
    Toast toast;
    RegisterPresenter presenter;
    Integer error_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        email = findViewById(R.id.editTextTextPersonName2);
        pass = findViewById(R.id.editTextTextPassword);
        r_pass=findViewById(R.id.editTextTextPassword2);
        toast = Toast.makeText(this,"Пароль имеет неверный формат",Toast.LENGTH_LONG);
        fio = findViewById(R.id.editTextTextPersonName);
        registerButton = findViewById(R.id.button_reg);
        registerButton.setOnClickListener(this);
        presenter = new RegisterPresenter(this,new PostRegisterModel());
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_reg){
            error_code=0;
            if(check_fill_field()){
                if(check_password()){
                    registerButton.setClickable(false);
                    RegistrationClass user = new RegistrationClass(
                            email.getText().toString(), pass.getText().toString(), fio.getText().toString());
                    showPd();
                    presenter.sendPostToRegisterUser(user);
                }else{
                    write_error();
                }
            }else{
                write_error();
            }
        }
    }
    void write_error(){
        switch(error_code){
            case 1:{
                toast.cancel();
                toast =  Toast.makeText(this,"Заполните все поля!",Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case 2:{
                toast.cancel();
                toast =  Toast.makeText(this,"Пароли не совпадают",Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            default:{
                toast.cancel();
                toast =  Toast.makeText(this,"Ошибка",Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
    }
    boolean check_password(){
        String rpassS, passS;
        passS=pass.getText().toString();
        rpassS=r_pass.getText().toString();
        if(!passS.equals(rpassS)){
            error_code=2;
            return false;
        }
        return true;
    }
    boolean check_fill_field(){
        String loginS, fioS, passS, rpassS;
        loginS=email.getText().toString();
        fioS=fio.getText().toString();
        passS=pass.getText().toString();
        rpassS=r_pass.getText().toString();
        if(loginS.equals("")||fioS.equals("")||passS.equals("")||rpassS.equals("")){
            error_code=1;
            return false;
        }
        return true;
    }

    @Override
    public void toLoginActivity(Map<String, String> map) {
        hidePd();
        toast.cancel();
        toast = Toast.makeText(this,map.get("answer"),Toast.LENGTH_LONG);
        toast.show();
        //Toast.makeText(this,"Вы успешно зарегистрированы ",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void showPd() {
        pd = new ProgressDialog(this);
        pd.setTitle("Подождите");
        pd.setMessage("Регистрация...");
        pd.show();
    }

    @Override
    public void hidePd() {
        pd.dismiss();
    }

    @Override
    public void onResponseFailRule(String t) {
        hidePd();
        if(t.equals("fail")){
            toast.cancel();
            toast = Toast.makeText(this,"Данный Логин уже используется",Toast.LENGTH_LONG);
            toast.show();
            registerButton.setClickable(true);
        }else{
            toast.cancel();
            toast = Toast.makeText(this,t,Toast.LENGTH_LONG);
            toast.show();
            registerButton.setClickable(true);
        }
    }
}