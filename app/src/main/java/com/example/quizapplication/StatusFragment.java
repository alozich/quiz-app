package com.example.quizapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class StatusFragment extends Fragment {

    public StatusFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setStatus(String numQ, String time, String score) {
        View v = getView();
        TextView tv = (TextView) v.findViewById(R.id.tvQuestionNum);
        tv.setText(numQ);
        tv = (TextView) v.findViewById(R.id.tvTime);
        tv.setText(time);
        tv = (TextView) v.findViewById(R.id.tvScore);
        tv.setText(score);
    }

    public void bringFocus(boolean correct)
    {
        View v = getView();
        TextView tv = (TextView) v.findViewById(R.id.tvScore);
        if(correct)
            tv.setBackgroundColor(Color.rgb(0, 230, 0));
        else
            tv.setBackgroundColor(Color.RED);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

}
