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
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText email;
    EditText ID;
    EditText password;
    String getEmail;
    String getID;
    String getPassword;
    Button signup_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        signup_btn = (Button) findViewById(R.id.signup_btn);
        email = findViewById(R.id.email_et);
        ID = findViewById(R.id.id_et);
        password = findViewById(R.id.password_et);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail = email.getText().toString();
                getID = ID.getText().toString();
                getPassword = password.getText().toString();
                if (getEmail.getBytes().length <= 0 | getID.getBytes().length <= 0 | getPassword.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "입력이 완료되지 않았습니다", Toast.LENGTH_SHORT).show();
                }
                else if (getID.matches(mail)) {
                    Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
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
        jsonObject.addProperty("email", getEmail);
        jsonObject.addProperty("id",getID);
        jsonObject.addProperty("password", getPassword);
        Call<Void> call = retrofit.key_certified(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void repo = response.body();
                if (response.code() == 205) {
                    Toast.makeText(getApplicationContext(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
