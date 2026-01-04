package com.example.hunarmand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Button btnSendReset = findViewById(R.id.btnSendReset);

        btnSendReset.setOnClickListener(v -> {
            Toast.makeText(this, "Reset link sent (dummy)", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ResetPasswordActivity.class);
            startActivity(intent);
        });
    }

}
