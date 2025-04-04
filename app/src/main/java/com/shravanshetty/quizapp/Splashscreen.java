package com.shravanshetty.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splashscreen extends AppCompatActivity {
    Animation topAnim;
    Animation bottomAnim;
    TextView appName, description, developer;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);

        // Set up window insets listener to handle system UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Get the system bar insets
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            // Set padding for the view
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Return the insets to indicate that the insets are handled
            return insets;
        });

        // Initialize views
        appName = findViewById(R.id.appName);
        description = findViewById(R.id.description);
        developer = findViewById(R.id.developer);
        imageView = findViewById(R.id.imageView);

        // Load animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Set animations for views
        appName.setAnimation(bottomAnim);
        description.setAnimation(bottomAnim);
        developer.setAnimation(bottomAnim);
        imageView.setAnimation(topAnim);

        // Start main activity after delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Splashscreen.this, signUp.class);
            startActivity(intent);
            finish();
        }, 4000);
    }
}
