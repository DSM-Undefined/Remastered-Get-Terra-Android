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

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText email;
    EditText certificationNum;
    String getEmail;
    String getCertificationNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login_btn);
        email=(EditText)findViewById(R.id.email_et);
        certificationNum=(EditText)findViewById(R.id.certificationNum_et);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmail=email.getText().toString();
                getCertificationNum=certificationNum.getText().toString();
                getEmail=getEmail.trim();
                getCertificationNum=getCertificationNum.trim();
                if(getEmail.getBytes().length<=0|getCertificationNum.getBytes().length<=0){
                    Toast.makeText(getApplicationContext(),"이메일 혹은 인증번호가 입력되지 않았습니다",Toast.LENGTH_SHORT).show();
                }
                else if(getEmail.matches(mail)){
                    Toast.makeText(getApplicationContext(),"이메일 형식이 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    post();
                }
            }
        });
    }
    public void post(){
        API retrofit= ServiceGenerator.getClient().create(API.class);
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("serial_number",getCertificationNum);
        Call<Void> call= retrofit.post_login(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Void repo = response.body();
                if(response.code()==200) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"인증번호가 맞지 않습니다.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}