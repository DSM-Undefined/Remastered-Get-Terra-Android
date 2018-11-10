package com.dsm2018.get_terra_android_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsm2018.get_terra_android_v2.Connector.API;
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText ID;
    EditText password;
    String getID;
    String getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (Button) findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        login = (Button) findViewById(R.id.login_btn);
        ID = (EditText) findViewById(R.id.id_et);
        password = (EditText) findViewById(R.id.password_et);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getID = ID.getText().toString();
                getPassword = password.getText().toString();
                getID = getID.trim();
                getPassword = getPassword.trim();
                if (getID.getBytes().length <= 0 | getPassword.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "이메일 혹은 인증번호가 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    post();
                }
            }
        });
    }

    public void post() {
        API retrofit = ServiceGenerator.getClient().create(API.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", getID);
        jsonObject.addProperty("password", getPassword);
        Call<Void> call = retrofit.login(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void repo = response.body();
                if (response.code() == 401) {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), KeyCertifiedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}