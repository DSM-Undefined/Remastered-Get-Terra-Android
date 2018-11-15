package com.dsm2018.get_terra_android_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsm2018.get_terra_android_v2.Connector.API;
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator;
import com.dsm2018.get_terra_android_v2.sibal.PrefManager;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText key_certificate;
    EditText email;
    EditText ID;
    EditText password;
    String getID;
    String getPassword;
    String getKeyCertificate;
    String getEmail;
    String getAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        key_certificate = (EditText) findViewById(R.id.key_certificate_et);
        login = (Button) findViewById(R.id.login_btn);
        ID = (EditText) findViewById(R.id.id_et);
        password = (EditText) findViewById(R.id.password_et);
        email = (EditText) findViewById(R.id.email_et);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getID = ID.getText().toString();
                getPassword = password.getText().toString();
                getKeyCertificate = key_certificate.getText().toString();
                getEmail = email.getText().toString();
                getID = getID.trim();
                getPassword = getPassword.trim();
                getKeyCertificate = getKeyCertificate.trim();
                getEmail = getEmail.trim();
                if (getID.getBytes().length <= 0 | getPassword.getBytes().length <= 0 | getKeyCertificate.getBytes().length <= 0 | getEmail.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "입력이 완료되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else {
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
        jsonObject.addProperty("email", getEmail);
        Call<JsonObject> call = retrofit.login(getKeyCertificate, jsonObject);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject repo = response.body();
                getAccessToken=repo.get("accessToken").getAsString();
                if (response.code() == 200) {
                    Intent intent = new Intent(LoginActivity.this, TeamChoiceActivity.class);
                    intent.putExtra("accessToken", getAccessToken);
                    startActivity(intent);
                } else if (response.code() == 204) {
                    Toast.makeText(getApplicationContext(), "인증번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                PrefManager prefManager = new PrefManager();
                prefManager.saveToken(getApplicationContext(), getAccessToken, true);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

}