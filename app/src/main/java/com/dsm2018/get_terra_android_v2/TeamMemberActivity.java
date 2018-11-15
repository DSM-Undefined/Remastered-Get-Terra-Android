package com.dsm2018.get_terra_android_v2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsm2018.get_terra_android_v2.Connector.API;
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator;
import com.dsm2018.get_terra_android_v2.sibal.PrefManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamMemberActivity extends AppCompatActivity {

    Button selected_btn;
    ImageView colorView;
    ImageView back_color;
    TextView teamColor;
    TextView listTv;
    String getAuthorization;
    String getToken;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teammember);

        colorView = findViewById(R.id.color_view);
        back_color = findViewById(R.id.back_top);
        selected_btn = findViewById(R.id.select_btn);
        teamColor = findViewById(R.id.team_tv);
        listTv = findViewById(R.id.list_tv);

        getToken = getIntent().getStringExtra("accessToken");
        Log.d("token",getToken);
        getAuthorization = getIntent().getStringExtra("teamNum");

        API retrofit = ServiceGenerator.getClient().create(API.class);
        Call<JsonObject> call = retrofit.teamList(new PrefManager().getToken(getBaseContext(), "pref", true ));
        call.enqueue(new Callback<JsonObject>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject repo = response.body();
                if (response.code() == 200) {
                    Log.d("성공", "성공");
                    if(!repo.get(getAuthorization).getAsJsonObject().get("member").isJsonNull()) {
                        for (JsonElement member : repo.get(getAuthorization).getAsJsonObject().get("member").getAsJsonArray()) {
                            listTv.append(member.getAsString() + "\n");
                        }
                    }
                    if (getAuthorization.equals("1")) {
                        Log.d("블루", "블루팀");
                        teamColor.setText(R.string.blueTeam);
                        colorView.setImageResource(R.drawable.blue_square);
                        back_color.setImageResource(R.drawable.back_teammembershow_blue);
                        selected_btn.setTextColor(R.color.blue);
                    } else if (getAuthorization.equals("2")) {
                        Log.d("2", "2");
                        teamColor.setText(R.string.greenTeam);
                        colorView.setImageResource(R.drawable.green_square);
                        back_color.setImageResource(R.drawable.back_teammembershow_green);
                        selected_btn.setTextColor(R.color.green);
                    } else if (getAuthorization.equals("3")) {
                        Log.d("3", "3");
                        teamColor.setText(R.string.yellowTeam);
                        colorView.setImageResource(R.drawable.yellow_square);
                        back_color.setImageResource(R.drawable.back_teammembershow_yellow);
                        selected_btn.setTextColor(R.color.yellow);
                    } else if (getAuthorization.equals("4")) {
                        Log.d("44", "4");
                        teamColor.setText(R.string.violetTeam);
                        colorView.setImageResource(R.drawable.violet_square);
                        back_color.setImageResource(R.drawable.back_teammembershow_violet);
                        selected_btn.setTextColor(R.color.violet);
                    }

                    selected_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TeamMemberActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("실패함", "실패");
            }
        });
    }



}
