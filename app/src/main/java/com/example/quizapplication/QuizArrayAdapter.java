package com.example.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizArrayAdapter extends RecyclerView.Adapter<QuizArrayAdapter.ViewHolder> {

    private int listQuizLayout;
    private ArrayList<Quiz> quizList;
    private Resources res;

    public QuizArrayAdapter(int layoutID, ArrayList<Quiz> quizzes)
    {
        listQuizLayout = layoutID;
        quizList = quizzes;
    }

    @NonNull
    @Override
    public QuizArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listQuizLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        res = parent.getResources();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizArrayAdapter.ViewHolder holder, int position) {
        TextView name = holder.tvName;
        TextView author = holder.tvAuthor;
        TextView date = holder.tvDate;
        TextView type = holder.tvType;
        Context context = holder.context;

        Quiz q = quizList.get(position);
        holder.tvName.setText(q.getQuizTitle());
        holder.tvAuthor.setText(q.getWhoDidThis());
        holder.tvDate.setText(q.getDateMade());
        holder.tvType.setText(q.getQuizType());
    }

    @Override
    public int getItemCount() {
        int s = 0;
        if (quizList!= null)
            s = quizList.size();
        return s;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public TextView tvAuthor;
        public TextView tvDate;
        public TextView tvType;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            context = itemView.getContext();
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra(MainActivity.PKG_QUIZ_NAME, tvName.getText());
            intent.putExtra(MainActivity.PKG_QUIZ_AUTHOR, tvAuthor.getText());
            intent.putExtra(MainActivity.PKG_QUIZ_DATE, tvDate.getText());
            intent.putExtra(MainActivity.PKG_QUIZ_TYPE, tvType.getText());
            context.startActivity(intent);
        }
    }
}
