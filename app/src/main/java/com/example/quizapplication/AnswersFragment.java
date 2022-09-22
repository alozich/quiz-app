package com.example.quizapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class AnswersFragment extends Fragment {

    public AnswersFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View myView = inflater.inflate(R.layout.fragment_answers, container, false);
        return myView;
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setAnswers(String[] currentAnswers) {
        View v = getView();
        TextView tvAnswer = (TextView) v.findViewById(R.id.rbAnswer1);
        tvAnswer.setText(currentAnswers[0]);
        tvAnswer = (TextView) v.findViewById(R.id.rbAnswer2);
        tvAnswer.setText(currentAnswers[1]);
        tvAnswer = (TextView) v.findViewById(R.id.rbAnswer3);
        tvAnswer.setText(currentAnswers[2]);
        tvAnswer = (TextView) v.findViewById(R.id.rbAnswer4);
        tvAnswer.setText(currentAnswers[3]);
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    public String getClickedAnswer()
    {
        View v = getView();
        RadioGroup rg = v.findViewById(R.id.rgAnswers);
        RadioButton rb = v.findViewById(rg.getCheckedRadioButtonId());
        return rb.getText().toString();
    }

}
