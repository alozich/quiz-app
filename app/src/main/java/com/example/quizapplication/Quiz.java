package com.example.quizapplication;

public class Quiz {
    private String quizTitle;
    private String whoDidThis;
    private String dateMade;
    private String quizType;
    private boolean available;

    Quiz() {
        quizTitle = "";
        whoDidThis = "";
        dateMade = "";
        quizType = "";
        available = false;
    }

    Quiz(String qt, String who, String date, String type, boolean a) {
        quizTitle = qt;
        whoDidThis = who;
        dateMade = date;
        quizType = type;
        available = a;
    }

    public String getQuizTitle() {return quizTitle;}
    public String getWhoDidThis() {return whoDidThis;}
    public String getDateMade() {return dateMade;}
    public String getQuizType() {return quizType;}
    public boolean isAvailable() {return available;}
}
