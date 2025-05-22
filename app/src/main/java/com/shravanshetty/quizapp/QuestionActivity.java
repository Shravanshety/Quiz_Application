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

public class QuestionActivity extends AppCompatActivity {

    int flag = 0;
    public static int correct = 0;
    public static int wrong = 0;
    String quizTopic = "Unknown";

    String[] questions = {
            "What is the size of an int data type in Java?",
            "Which of the following is the correct way to start the execution of a Java application?",
            "Which keyword is used to create an object in Java?",
            "What will the expression 5 + 2 * 3 evaluate to in Java?",
            "Which of the following is not a Java access modifier?"
    };
    String[] options = {
            "2 bytes", "4 bytes", "8 bytes", " Depends on the system",
            "public static void start(String[] args)", "public static void run(String[] args)", "public static void main(String[] args)", "public static main(String[] args)",
            "build", "new", "create", "make",
            "21", "11", "15", "17",
            "public", "private", "protected", "friendly"
    };
    String[] answers = {
            "4 bytes",
            "public static void main(String[] args)",
            "new",
            "11",
            "friendly"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);

        quizTopic = getIntent().getStringExtra("topic"); // Get topic from intent

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
                Toast.makeText(QuestionActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uAnswer.getText().toString();

            if (ansText.equals(answers[flag])) {
                correct++;
                Toast.makeText(QuestionActivity.this, "Hurry!! it was correct", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(QuestionActivity.this, "ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
            }

            flag++;
            if (score != null) {
                score.setText(String.valueOf(correct));
                if (flag < questions.length) {
                    question.setText(questions[flag]);
                    rb1.setText(options[flag * 4]);
                    rb2.setText(options[flag * 4 + 1]);
                    rb3.setText(options[flag * 4 + 2]);
                    rb4.setText(options[flag * 4 + 3]);
                    dispNo.setText((flag + 1) + "/" + questions.length);
                } else {
                    // Save quiz history only here when the quiz ends
                    saveQuizHistory(quizTopic, correct, flag);
                    Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                    intent.putExtra("attempted", flag);
                    intent.putExtra("correct", correct);
                    intent.putExtra("wrong", wrong);
                    startActivity(intent);
                    finish();
                }
                radio_g.clearCheck();
            }
        });

        quitBtn.setOnClickListener(v -> {
            // Save quiz history only when quitting from the quiz
            saveQuizHistory(quizTopic, correct, flag);
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
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
