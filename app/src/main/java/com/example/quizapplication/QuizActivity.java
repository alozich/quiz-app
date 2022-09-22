package com.example.quizapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class QuizActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.S)

    int currentQuestionNum = 1;
    public static String[] theQuiz;
    public static String quizType;
    String[] data;
    String[] answers;
    int numCorrect = 0;
    int score = 0;
    String imageAnswer = null;
    int TOTALTIME = 60000;
    CountDownTimer theTimer;


    @Override
    @RequiresApi(api = Build.VERSION_CODES.S)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        String s = intent.getStringExtra(MainActivity.PKG_QUIZ_NAME);
        quizType = intent.getStringExtra(MainActivity.PKG_QUIZ_TYPE);
        Resources res = getResources();
        int id = res.getIdentifier(s, "array", getPackageName());
        theQuiz = res.getStringArray(id);

        FragmentManager fragmentManager = getSupportFragmentManager( );
        if( fragmentManager.findFragmentById(R.id.currentQuestion) == null ) {
            FragmentTransaction transaction = fragmentManager.beginTransaction( );
            QuestionFragment fragment = new QuestionFragment( );
            transaction.add( R.id.currentQuestion, fragment );
            transaction.commit( );
        }
        if( fragmentManager.findFragmentById(R.id.currentAnswers) == null ) {
            FragmentTransaction transaction = fragmentManager.beginTransaction( );
            AnswersFragment fragment = new AnswersFragment( );
            transaction.add( R.id.currentAnswers, fragment );
            transaction.commit( );
        }
        if( fragmentManager.findFragmentById(R.id.currentStatus) == null ) {
            FragmentTransaction transaction = fragmentManager.beginTransaction( );
            StatusFragment fragment = new StatusFragment( );
            transaction.add( R.id.currentStatus, fragment );
            transaction.commit();
        }

        data = theQuiz[currentQuestionNum - 1].split(":");
        answers = new String[]{data[2], data[3], data[4], data[5]};

        if(quizType.equals("image"))
            imageAnswer = data[6];

        theTimer = new CountDownTimer(TOTALTIME, 500) {
            final int timeIncrements = TOTALTIME / 5;
            long time = TOTALTIME / 1000;
            @Override
            public void onTick(long m) {
                TextView timeLeft = (TextView) findViewById(R.id.tvTime);
                time = m / 1000;
                timeLeft.setText("Time: " + String.valueOf(time));
            }

            @Override
            public void onFinish() {
                score = (int) (numCorrect * (time));
                Log.i("onFinish", "Score: " + score);
                FinishQuiz();
            }
        };
        theTimer.start();
    }

    //sets QuestionFragment
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setQuestion(String q)
    {
        FragmentManager fm = getSupportFragmentManager();
        QuestionFragment qFragment = (QuestionFragment) fm.findFragmentById( R.id.currentQuestion );
        qFragment.setQuestion(q, imageAnswer);
    }

    //sets AnswerFragment
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setAnswers(String[] a)
    {
        FragmentManager fm = getSupportFragmentManager();
        AnswersFragment aFragment = (AnswersFragment) fm.findFragmentById(R.id.currentAnswers);
        aFragment.setAnswers(a);
    }

    //sets StatusFragment
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void setStatus(String numQ, String time, String score)
    {
        FragmentManager fm = getSupportFragmentManager();
        StatusFragment sFragment = (StatusFragment) fm.findFragmentById(R.id.currentStatus);
        sFragment.setStatus(numQ, time, score);
    }

    //checks whether the user answered correctly and handles accordingly
    @RequiresApi(api = Build.VERSION_CODES.S)
    public boolean isCorrect()
    {
        FragmentManager fm = getSupportFragmentManager();
        AnswersFragment aFragment = (AnswersFragment) fm.findFragmentById(R.id.currentAnswers);
        StatusFragment sFragment = (StatusFragment) fm.findFragmentById(R.id.currentStatus);
        String clickedAnswer = aFragment.getClickedAnswer();

        if(clickedAnswer.equals(data[1]))
        {
            numCorrect += 1;
            sFragment.bringFocus(true);
            return true;
        }
        sFragment.bringFocus(false);
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void onStart( ) {
        super.onStart( );
        //can't put in OnCreate, fragment isn't ready yet
        setQuestion(data[0]);
        setAnswers(answers);
        setStatus("Question: " + currentQuestionNum, "Time: ", "Correct: " + score);
    }

    //either when user gets to the end of quiz or when time runs out
    public void FinishQuiz()
    {
        theTimer.cancel();
        setContentView(R.layout.finish_quiz);
        TextView tv = (TextView) findViewById(R.id.tvFinalScore);
        tv.setText("Your score is " + score + "!");
        tv = findViewById(R.id.tvCorrect);
        tv.setText("Correct: " + numCorrect + "/5");
    }

    //Enter button
    @RequiresApi(api = Build.VERSION_CODES.S)
    public void onClickEnter(View v)
    {
        currentQuestionNum++;
        isCorrect();
        if(currentQuestionNum < 6)
        {
            setStatus("Question: " + currentQuestionNum, "0:00", "Score: " + numCorrect + "/5");
            data = theQuiz[currentQuestionNum - 1].split(":");
            answers = new String[]{data[2], data[3], data[4], data[5]};
            if(quizType.equals("image"))
                imageAnswer = data[6];
            setQuestion(data[0]);
            setAnswers(answers);
        }
        else
        {
            theTimer.onFinish();
            Log.i("Timer", "Timer finished");
            theTimer.cancel();
            Log.i("Timer", "Timer cancelled");
            FinishQuiz();
        }

    }

    //takes user back to list of quizzes
    public void onClickQuit(View v)
    {
        finish();
    }
}