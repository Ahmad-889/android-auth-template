package com.example.hunarmand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnSignup;
    private TextView tvLoginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // attach signup layout

        // Bind UI elements
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginRedirect = findViewById(R.id.tvLoginLink);

        // Signup button click
        btnSignup.setOnClickListener(v -> {
            if (validateForm()) {
                Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                // For now → just go to LoginActivity after signup
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Find the "Already have an account? Log In" TextView
        TextView tvLoginRedirect = findViewById(R.id.tvLoginLink);

        // Redirect to LoginActivity when clicked
        tvLoginRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    // Strong password regex:
    // ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])" +           // at least 1 digit
                    "(?=.*[a-z])" +            // at least 1 lowercase letter
                    "(?=.*[A-Z])" +            // at least 1 uppercase letter
                    "(?=.*[@#$%^&+=!])" +      // at least 1 special character
                    "(?=\\S+$)" +              // no white spaces
                    ".{8,}$";                  // at least 8 characters

    // ✅ Form validation function
    private boolean validateForm() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Full name validation
        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Full name is required");
            etFullName.requestFocus();
            return false;
        }

        // Email validation
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        // Phone validation (Pakistani format = 11 digits)
        if (TextUtils.isEmpty(phone) || phone.length() <= 11) {
            etPhone.setError("Enter a valid 11-digit phone number");
            etPhone.requestFocus();
            return false;
        }

        // Strong password validation
        if (!password.matches(PASSWORD_PATTERN)) {
            etPassword.setError("Password must be at least 8 chars, include uppercase, lowercase, number & special char");
            etPassword.requestFocus();
            return false;
        }

        // Confirm password validation
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
}