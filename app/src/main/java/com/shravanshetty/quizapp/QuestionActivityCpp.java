package com.shravanshetty.quizapp;

import android.content.Intent;
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

public class QuestionActivityCpp extends AppCompatActivity {

    int flag=0;
    int marks = 0;
    public static int correct=0;
    public static int wrong=0;
    String[] questions ={
            "Which of the following is the correct file extension for a C++ source file?",
            "What is the size of a 'double' data type in most C++ compilers?",
            "Which operator is used to allocate memory dynamically in C++?",
            "Which of the following is not a valid C++ access specifier?",
            "What is the correct way to start the main function in C++?"
    };
    String[] options={
            ".c", ".cpp", ".cp", ".cc",
            "2 bytes", "4 bytes", "8 bytes", "Depends on compiler",
            "malloc", "alloc", "new", "create",
            "public", "private", "internal", "protected",
            "int main()", "void main()", "main()", "start()"

    };
    String[] answers = {
            ".cpp",
            "8 bytes",
            "new",
            "internal",
            "int main()"
    };
    TextView quitBtn,dispNo,score,question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_cpp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        quitBtn=findViewById(R.id.quitBtn);
        score=findViewById(R.id.score);
        dispNo=findViewById(R.id.dispNo);
        next=findViewById(R.id.nextBtn);
        radio_g=findViewById(R.id.answerGroup);
        question=findViewById(R.id.question);
        rb1=findViewById(R.id.radioBtn1);
        rb2=findViewById(R.id.radioBtn2);
        rb3=findViewById(R.id.radioBtn3);
        rb4=findViewById(R.id.radioBtn4);

        question.setText(questions[flag]);
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radio_g.getCheckedRadioButtonId() == -1){
                    Toast.makeText(QuestionActivityCpp.this,"Please select an option",Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uAnswer.getText().toString();

                if(ansText.equals(answers[flag])){
                    correct++;
                    Toast.makeText(QuestionActivityCpp.this,"Hurry!! it was correct",Toast.LENGTH_SHORT).show();
                }else{
                    wrong++;
                    Toast.makeText(QuestionActivityCpp.this,"ohh!! it was incorrect",Toast.LENGTH_SHORT).show();
                }
                flag++;
                if(score!=null){
                    score.setText(""+correct);
                    if(flag<questions.length){
                        question.setText(questions[flag]);
                        rb1.setText(options[flag*4]);
                        rb2.setText(options[flag*4+1]);
                        rb3.setText(options[flag*4+2]);
                        rb4.setText(options[flag*4+3]);

                        dispNo.setText(flag+1+"/"+questions.length);
                    }else{
                        marks=correct;
                        Intent intent =new Intent(QuestionActivityCpp.this,ResultActivity.class);
                        intent.putExtra("attempted",flag);
                        intent.putExtra("correct",correct);
                        intent.putExtra("wrong",wrong);
                        startActivity(intent);
                        finish();

                    }
                    radio_g.clearCheck();
                }

            }
        });
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuestionActivityCpp.this,ResultActivity.class);
                intent.putExtra("attempted",flag);
                intent.putExtra("correct",correct);
                intent.putExtra("wrong",wrong);
                QuestionActivity.correct = 0;
                QuestionActivity.wrong = 0;
                startActivity(intent);
                finish();
            }
        });
    }
}