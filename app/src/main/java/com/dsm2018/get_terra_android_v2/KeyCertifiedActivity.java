package com.dsm2018.get_terra_android_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm2018.get_terra_android_v2.Connector.API;
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeyCertifiedActivity extends AppCompatActivity {
    TextView ID;
    EditText keytoken;
    Button joingame;
    String getKeytoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_keycertified);

        keytoken = (EditText) findViewById(R.id.keytoken_et);
        joingame = (Button) findViewById(R.id.certificate_btn);
        joingame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getKeytoken = keytoken.getText().toString();
                getKeytoken = getKeytoken.trim();
                if (getKeytoken.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "이메일 혹은 인증번호가 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else {
                    post();
                }

            }
        });
    }

    public void post() {
        API retrofit = ServiceGenerator.getClient().create(API.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("gameKey", getKeytoken);
        Call<Void> call = retrofit.key_certified(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void repo = response.body();
                if (response.code()==201) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else if(response.code()==204) {
                    Toast.makeText(getApplicationContext(), "인증번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}

