package com.example.quizapplication;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {

    public QuestionFragment() { }
    String quizType = QuizActivity.quizType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(quizType.equals("image"))
        {
            return inflater.inflate(R.layout.fragment_image_question, container, false);
        }
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setQuestion(String currentQuestion, String drawable) {
        View v = getView();

        TextView tvQuestion = null;
        if(quizType.equals("image"))
        {
            tvQuestion = (TextView) v.findViewById(R.id.tvImageQuestion);
            ImageView iv = (ImageView) v.findViewById(R.id.ivQuestion);
            Resources res = getResources();
            int id = res.getIdentifier(drawable, "drawable", this.getActivity().getPackageName());
            iv.setImageResource(id);
        }
        else if(quizType.equals("text"))
        {
            tvQuestion = (TextView) v.findViewById(R.id.tvQuestion);
        }
        tvQuestion.setText(currentQuestion);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

}
