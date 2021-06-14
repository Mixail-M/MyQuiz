package com.example.myquiz.Profile;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.myquiz.Network.GetByIdModel;
import com.example.myquiz.Network.GetMyTestModel;
import com.example.myquiz.PlayByCodeTest.PlayByCodeMethods;
import com.example.myquiz.PlayByCodeTest.PlayByCodePresenter;
import com.example.myquiz.Profile.Result.Profile_rez_fragment;
import com.example.myquiz.R;
import com.example.myquiz.Singleton;

import java.util.ArrayList;
import java.util.Objects;

public class Profile_fragment extends Fragment implements GetMyTestMethods.GetMyTestView{
    RecyclerView recyclerMyTest;
    ArrayList<QuizProfile> qProfile = new ArrayList<>();
    GetMyTestPresenter presenter;
    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.profile_fragment,
                container,
                false);
        recyclerMyTest = rootView.findViewById(R.id.rec_profile);
        presenter = new GetMyTestPresenter(new GetMyTestModel(),  this);
        presenter.setToServer();
        toolbar = rootView.findViewById(R.id.toolbar_profile);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_result_test:{
                        //открыть результаты
                        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Profile_rez_fragment prfQuest= new Profile_rez_fragment();
                        fragmentTransaction.replace(R.id.container_fragment, prfQuest);
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
    public void showTest(ArrayList<QuizProfile> quizProfiles) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMyTest.setLayoutManager(layoutManager);
        qProfile.add(new QuizProfile(1,"один","Описание"));
        MyTestAdapter mTestAdapter = new MyTestAdapter(quizProfiles);
        recyclerMyTest.setAdapter(mTestAdapter);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(),"Ошибка",Toast.LENGTH_LONG).show();
    }
}
