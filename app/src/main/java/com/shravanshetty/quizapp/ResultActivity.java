package com.shravanshetty.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    TextView correct,wrong,total,result;
    Button home;
    CircularProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        correct =findViewById(R.id.correct);
        wrong =findViewById(R.id.wrong);
        total =findViewById(R.id.total);
        home =findViewById(R.id.home);
        result =findViewById(R.id.result);
        progressBar=findViewById(R.id.circularProgressbar);

        progressBar.setProgress(QuestionActivity.correct);
        Intent intent=getIntent();
        int attempted1 = intent.getIntExtra("attempted",0);
        int correct1 = intent.getIntExtra("correct",0);
        int wrong1 = intent.getIntExtra("wrong",0);
        correct.setText("Correct :"+correct1);
        wrong.setText("Wrong :"+wrong1);
        total.setText("Attempted:"+attempted1);
        result.setText(correct1+"/"+attempted1);


        QuestionActivity.correct = 0;
        QuestionActivity.wrong = 0;

        //too switch from one page to another
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,MainActivity.class));
                finishAffinity();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finishAffinity();
            }
        });

}}