package com.shravanshetty.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuestionActivityPython extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    public static int wrong = 0;
    String quizTopic = "Unknown";  // To store topic

    String[] questions = {
            "What is the output of: print(type([])) in Python?",
            "Which keyword is used to define a function in Python?",
            "What is the correct file extension for Python files?",
            "Which of the following data types is immutable in Python?",
            "What will be the output of: print(2 ** 3) in Python?"
    };
    String[] options = {
            "<class 'list'>", "<type 'list'>", "list", "ListType",
            "function", "def", "fun", "define",
            ".pt", ".py", ".python", ".pyt",
            "list", "set", "dict", "tuple",
            "6", "8", "9", "5"
    };
    String[] answers = {
            "<class 'list'>",
            "def",
            ".py",
            "tuple",
            "8"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_python);

        quizTopic = getIntent().getStringExtra("topic");
        if (quizTopic == null) {
            quizTopic = "Unknown";
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quitBtn = findViewById(R.id.quitBtn);
        score = findViewById(R.id.score);
        dispNo = findViewById(R.id.dispNo);
        next = findViewById(R.id.nextBtn);
        radio_g = findViewById(R.id.answerGroup);
        question = findViewById(R.id.question);
        rb1 = findViewById(R.id.radioBtn1);
        rb2 = findViewById(R.id.radioBtn2);
        rb3 = findViewById(R.id.radioBtn3);
        rb4 = findViewById(R.id.radioBtn4);

        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);

        next.setOnClickListener(view -> {
            if (radio_g.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuestionActivityPython.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uAnswer.getText().toString();

            if (ansText.equals(answers[flag])) {
                correct++;
                Toast.makeText(QuestionActivityPython.this, "Hurry!! it was correct", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(QuestionActivityPython.this, "Ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
            }

            flag++;
            score.setText(String.valueOf(correct));

            if (flag < questions.length) {
                question.setText(questions[flag]);
                rb1.setText(options[flag * 4]);
                rb2.setText(options[flag * 4 + 1]);
                rb3.setText(options[flag * 4 + 2]);
                rb4.setText(options[flag * 4 + 3]);
                dispNo.setText((flag + 1) + "/" + questions.length);
            } else {
                marks = correct;
                saveQuizHistory(quizTopic, correct, flag);
                Intent intent = new Intent(QuestionActivityPython.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }
            radio_g.clearCheck();
        });

        quitBtn.setOnClickListener(v -> {
            saveQuizHistory(quizTopic, correct, flag);
            Intent intent = new Intent(QuestionActivityPython.this, ResultActivity.class);
            intent.putExtra("attempted", flag);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            correct = 0;
            wrong = 0;
            startActivity(intent);
            finish();
        });
    }

    private void saveQuizHistory(String topic, int score, int totalQuestions) {
        SharedPreferences prefs = getSharedPreferences("quiz_history", MODE_PRIVATE);
        String oldHistory = prefs.getString("history", "");
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());

        String newEntry = "Topic: " + topic +
                " | Score: " + score + "/" + totalQuestions +
                " | Date: " + timestamp + "\n";

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("history", oldHistory + newEntry);
        editor.apply();
    }
}
