package com.gramcaster.radinaldn.aurelia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gramcaster.radinaldn.aurelia.R;

public class LoginActivity extends AppCompatActivity {

    private ImageView ivLogoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivLogoApp = findViewById(R.id.ivLogo);

    }

    public void goToMainActivity(View view) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void goToRegisterActivity(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
