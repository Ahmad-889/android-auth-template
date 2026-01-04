package com.example.hunarmand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText etNewPassword, etConfirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        Button btnReset = findViewById(R.id.btnResetPassword);

        btnReset.setOnClickListener(v -> {
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmNewPassword.getText().toString().trim();
            if (newPass.isEmpty() || newPass.length() < 8) {
                etNewPassword.setError("Password must be at least 8 characters");
                return;
            }

            if (!newPass.equals(confirmPass)) {
                etConfirmNewPassword.setError("Passwords do not match");
                return;
            }

            // ✅ RESET HARDCODED PASSWORD
            AuthManager.PASSWORD = newPass;

            Toast.makeText(this, "Password Reset Successfully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
