package com.shravanshetty.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_history) {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            return true;
        } else if (id == R.id.action_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        Intent intent = new Intent(MainActivity.this, signIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Updated navigation methods with topic name passed as intent extra

    public void java(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("topic", "Java");
        startActivity(intent);
        finish();
    }

    public void kotlin(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityKotlin.class);
        intent.putExtra("topic", "Kotlin");
        startActivity(intent);
        finish();
    }

    public void python(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityPython.class);
        intent.putExtra("topic", "Python");
        startActivity(intent);
        finish();
    }

    public void cpp(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityCpp.class);
        intent.putExtra("topic", "C++");
        startActivity(intent);
        finish();
    }

    public void dart(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityDart.class);
        intent.putExtra("topic", "Dart");
        startActivity(intent);
        finish();
    }

    public void sql(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivitySql.class);
        intent.putExtra("topic", "SQL");
        startActivity(intent);
        finish();
    }

    public void xml(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityXml.class);
        intent.putExtra("topic", "XML");
        startActivity(intent);
        finish();
    }

    public void c(View view) {
        Intent intent = new Intent(MainActivity.this, QuestionActivityC.class);
        intent.putExtra("topic", "C");
        startActivity(intent);
        finish();
    }
}
