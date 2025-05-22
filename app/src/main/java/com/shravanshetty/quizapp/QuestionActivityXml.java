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

public class QuestionActivityXml extends AppCompatActivity {

    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    public static int wrong = 0;

    String quizTopic = "Unknown";  // Added topic variable

    String[] questions = {
            "What does XML stand for?",
            "Which of the following is true about XML tags?",
            "Which character is used to start an XML tag?",
            "Which of the following is a correct XML declaration?",
            "Which statement about XML is false?"
    };
    String[] options = {
            "eXtra Modern Language", "eXtended Markup Language", "eXtensible Markup Language", "Example Markup Language",
            "Tags are case-insensitive", "Tags must be closed", "Tags can contain spaces", "Tags are optional",
            "/", "<", ">", "#",
            "<?xml version=\"1.0\"?>", "<xml version=\"1.0\">", "<!xml version=\"1.0\">", "xml version = \"1.0\"",
            "XML is used to store and transport data", "XML tags must be properly nested", "XML requires a DTD", "XML is case-sensitive"
    };
    String[] answers = {
            "eXtensible Markup Language",
            "Tags must be closed",
            "<",
            "<?xml version=\"1.0\"?>",
            "XML requires a DTD"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_xml);

        // Get quiz topic
        quizTopic = getIntent().getStringExtra("topic");
        if (quizTopic == null) quizTopic = "Unknown";

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
                Toast.makeText(QuestionActivityXml.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uAnswer.getText().toString();

            if (ansText.equals(answers[flag])) {
                correct++;
                Toast.makeText(QuestionActivityXml.this, "Hurry!! it was correct", Toast.LENGTH_SHORT).show();
            } else {
                wrong++;
                Toast.makeText(QuestionActivityXml.this, "ohh!! it was incorrect", Toast.LENGTH_SHORT).show();
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
                    saveQuizHistory(quizTopic, correct, flag);  // Save with topic
                    Intent intent = new Intent(QuestionActivityXml.this, ResultActivity.class);
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
            saveQuizHistory(quizTopic, correct, flag);  // Save with topic
            Intent intent = new Intent(QuestionActivityXml.this, ResultActivity.class);
            intent.putExtra("attempted", flag);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            correct = 0;
            wrong = 0;
            startActivity(intent);
            finish();
        });
    }

    // 🧠 Added saveQuizHistory method
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
