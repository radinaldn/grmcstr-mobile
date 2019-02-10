package com.gramcaster.radinaldn.aurelia.activity;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.model.User;
import com.gramcaster.radinaldn.aurelia.response.ResponseLogin;
import com.gramcaster.radinaldn.aurelia.rest.ApiClient;
import com.gramcaster.radinaldn.aurelia.rest.ApiInterface;
import com.gramcaster.radinaldn.aurelia.util.AbsRuntimePermission;
import com.gramcaster.radinaldn.aurelia.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AbsRuntimePermission {

    private ImageView ivLogoApp;
    public final String TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 10;

    private TextInputEditText etUsername, etPassword;
    private Button btLogin;
    private ApiInterface apiService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //request permission here
        requestAppPermissions(new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                R.string.msg, REQUEST_PERMISSION);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()){
            Intent intent =  new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etpassword);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionLogin();
            }
        });

        ivLogoApp = findViewById(R.id.ivLogo);
    }

    private void actionLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        apiService.userLogin(username, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()){
                    if (response.body().getCode() == 1){
                        User user = response.body().getUser();
                        Log.i(TAG, "onResponse: User  id : "+user.getId()+
                        "username : "+user.getUsername());
                        sessionManager.createLoginSession(user);

                        Intent intent =  new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_occured, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: isNotSuccessful : "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.failed_to_connect_to_server, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onPermissionGranted(int requestcode) {
        Toast.makeText(getApplicationContext(), R.string.permission_granted, Toast.LENGTH_SHORT).show();
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
