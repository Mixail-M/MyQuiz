package com.example.myquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myquiz.CreateTest.CreateTest;
import com.example.myquiz.PlayByCodeTest.Play_by_code_fragment;
import com.example.myquiz.Profile.Profile_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class act_main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private BottomNavigationView bnv;
    private FragmentTransaction fTrans;
    int frg=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        bnv=findViewById(R.id.navigation);
        bnv = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        fTrans = fragmentManager.beginTransaction();
        Lenta_fragment lfTest=new Lenta_fragment();
        fTrans.add(R.id.container_fragment, lfTest);
        fTrans.commit();
//Attach the listener
        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_one:{
                                if(frg!=1){
                                    frg=1;
                                    fragmentManager = getSupportFragmentManager();
                                    fTrans = fragmentManager.beginTransaction();
                                    Lenta_fragment lfTest=new Lenta_fragment();
                                    fTrans.replace(R.id.container_fragment, lfTest);
                                    fTrans.commit();
                                }
                                break;
                            }
                            case R.id.action_two:{
                                if(frg!=2){
                                    frg=2;
                                    fragmentManager = getSupportFragmentManager();
                                    fTrans = fragmentManager.beginTransaction();
                                    Search_fragment sfTest=new Search_fragment();
                                    fTrans.replace(R.id.container_fragment, sfTest);
                                    fTrans.commit();
                                }
                                break;
                            }
                            case R.id.action_three:{
                                if(frg!=3){
                                    frg=3;
                                    fragmentManager = getSupportFragmentManager();
                                    fTrans = fragmentManager.beginTransaction();
                                    CreateTest crTest=new CreateTest();

                                    fTrans.replace(R.id.container_fragment, crTest);
                                    fTrans.commit();

                                }
                                break;
                            }
                            case R.id.action_four:{
                                if(frg!=4){
                                    frg=4;
                                    fragmentManager = getSupportFragmentManager();
                                    fTrans = fragmentManager.beginTransaction();
                                    Profile_fragment pfTest=new Profile_fragment();
                                    fTrans.replace(R.id.container_fragment, pfTest);
                                    fTrans.commit();

                                }
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                        return true;//returning false disables the Navigation bar animations
                    }
                });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frg!=5){
                    frg=5;
                    fragmentManager = getSupportFragmentManager();
                    fTrans = fragmentManager.beginTransaction();
                    Play_by_code_fragment pbcTest=new Play_by_code_fragment();
                    fTrans.replace(R.id.container_fragment, pbcTest);
                    fTrans.commit();
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return false;
    }
}