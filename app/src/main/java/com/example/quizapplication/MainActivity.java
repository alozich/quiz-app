package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String PKG_QUIZ_NAME = "pkg_quiz_name";
    public static final String PKG_QUIZ_AUTHOR = "pkg_quiz_author";
    public static final String PKG_QUIZ_DATE = "pkg_quiz_date";
    public static final String PKG_QUIZ_TYPE = "pkg_quiz_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Quiz> theQuizzes = new ArrayList<Quiz>();
        QuizArrayAdapter quizArrayAdapter = new QuizArrayAdapter(R.layout.listview_quiz, theQuizzes);
        Resources res = getResources();
        String[] quizzes = res.getStringArray(R.array.allQuizzes);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(quizArrayAdapter);

        for(int i = 0; i < quizzes.length; i++)
        {
            String[] data = quizzes[i].split(":");
            boolean available = false;
            if (data[4].equals("true"))
            {
                available = true;
                Quiz quiz = new Quiz(data[0], data[1], data[2], data[3], available);
                theQuizzes.add(quiz);
            }

        }
        recyclerView = findViewById(R.id.recyclerView);
    }
}

