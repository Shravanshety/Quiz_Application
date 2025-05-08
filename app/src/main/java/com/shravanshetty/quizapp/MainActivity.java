package com.shravanshetty.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
    }

    public void java(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivity.class));
        finish();
    }

    public void kotlin(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityKotlin.class));
        finish();
    }

    public void python(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityPython.class));
        finish();
    }

    public void cpp(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityCpp.class));
        finish();
    }

    public void dart(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityDart.class));
        finish();

    }

    public void sql(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivitySql.class));
        finish();
    }

    public void xml(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityXml.class));
        finish();
    }

    public void c(View view) {
        startActivity(new Intent(MainActivity.this,QuestionActivityC.class));
        finish();
    }
}