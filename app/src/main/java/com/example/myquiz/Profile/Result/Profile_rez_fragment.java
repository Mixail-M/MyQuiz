package com.example.myquiz.Profile.Result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myquiz.CreateTest.CreateQuestion;
import com.example.myquiz.Network.GetMyTestModel;
import com.example.myquiz.Network.GetMyTestResultsModel;
import com.example.myquiz.Profile.GetMyTestMethods;
import com.example.myquiz.Profile.GetMyTestPresenter;
import com.example.myquiz.Profile.MyTestAdapter;
import com.example.myquiz.Profile.Profile_fragment;
import com.example.myquiz.Profile.QuizProfile;
import com.example.myquiz.R;

import java.util.ArrayList;
import java.util.Objects;

public class Profile_rez_fragment extends Fragment implements GetMyTestResultsMethods.GetMyTestResultsView{
    RecyclerView recyclerMyTest_rez;
    //ArrayList<QuizProfile> qProfile = new ArrayList<>();
    GetMyTestResultsPresenter presenter;

    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.rez_test_fragment,
                container,
                false);
        recyclerMyTest_rez = rootView.findViewById(R.id.rec_mytest_rez);
        presenter = new GetMyTestResultsPresenter(new GetMyTestResultsModel(),  this);
        presenter.setToServer();
        toolbar = rootView.findViewById(R.id.toolbar_profile_rez);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_my_test:{

                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Profile_fragment pfQuest= new Profile_fragment();

                        fragmentTransaction.replace(R.id.container_fragment, pfQuest);
                        fragmentTransaction.commit();
                        break;
                    }
                    default:{
                        break;
                    }
                }
                return true;
            }
        });
        return rootView;
    }

    @Override
    public void showTest(ArrayList<ProfileRezClass> profileRezClasses) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMyTest_rez.setLayoutManager(layoutManager);
        ProfileResultAdapter prTestAdapter = new ProfileResultAdapter(profileRezClasses);
        recyclerMyTest_rez.setAdapter(prTestAdapter);
    }
    @Override
    public void showError() {
        Toast.makeText(getContext(),"Ошибка",Toast.LENGTH_LONG).show();
    }
}
