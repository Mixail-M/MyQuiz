package com.example.myquiz.PlayByCodeTest;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myquiz.Network.GetByIdModel;
import com.example.myquiz.R;

import java.util.ArrayList;
import java.util.Objects;

public class Play_by_code_fragment extends Fragment implements PlayByCodeMethods.PlayByCodeView, View.OnClickListener {
    Button SearchBtn;
    EditText code;
    PlayByCodePresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.play_by_code_fragment,
                container,
                false);
        SearchBtn = rootView.findViewById(R.id.button_go);
        code = rootView.findViewById(R.id.editTextCode);
        SearchBtn.setOnClickListener(this);
        presenter = new PlayByCodePresenter(new GetByIdModel(), this);
        return rootView;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_go){
            if(!code.getText().toString().equals("")){
                int i = Integer.parseInt(code.getText().toString());
                presenter.setToServer(i);
            }
        }
    }

    @Override
    public void showTest(GetTestClass getTestClass) {
        Toast.makeText(getContext(),"Найдено",Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        test_describe_fragment Test= new test_describe_fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id_quiz",getTestClass.id);
        bundle.putString("Title", getTestClass.title);
        bundle.putString("description", getTestClass.description);
        bundle.putParcelableArrayList("Array_questions", (ArrayList<? extends Parcelable>) getTestClass.questionArrayList);
        Test.setArguments(bundle);
        fragmentTransaction.replace(R.id.container_fragment, Test);
        fragmentTransaction.commit();
        //перейти к тесту
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(),"Ошибка загрузки теста",Toast.LENGTH_LONG).show();
    }
}
