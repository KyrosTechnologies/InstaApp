package com.kyrostechnologies.template.insta;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    private EditText input_username, input_password;
    private Button btnSignUp;
    private View parent_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parent_view = findViewById(android.R.id.content);

        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);

        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        Snackbar.make(parent_view, "Registration Success", Snackbar.LENGTH_SHORT).show();
        hideKeyboard();
    }

    private boolean validateName() {
        if (input_username.getText().toString().trim().isEmpty()) {
            //inputLayoutName.setError(getString(R.string.err_msg_name));
            Snackbar.make(parent_view, getString(R.string.err_msg_name), Snackbar.LENGTH_SHORT).show();
            requestFocus(input_username);
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String password = input_password.getText().toString().trim();

        if (password.isEmpty()) {
            Snackbar.make(parent_view, getString(R.string.err_msg_password), Snackbar.LENGTH_SHORT).show();
            requestFocus(input_password);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
