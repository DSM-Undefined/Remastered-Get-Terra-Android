package com.dsm2018.get_terra_android_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TeamChoiceActivity extends AppCompatActivity {
    Button teamblue, teamgreen, teamyellow, teamviolet;
    String getToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamchoice);

        getToken = getIntent().getStringExtra("accessToken");
        teamblue = (Button)findViewById(R.id.teamblue_btn);
        teamblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TeamChoiceActivity.this,TeamMemberActivity.class);
                intent1.putExtra("teamNum","1");
                intent1.putExtra("accessToken",getToken);
                startActivity(intent1);
            }
        });
        teamgreen = (Button)findViewById(R.id.teamgreen_btn);
        teamgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TeamChoiceActivity.this,TeamMemberActivity.class);
                intent2.putExtra("teamNum","2");
                intent2.putExtra("accessToken",getToken);
                startActivity(intent2);
            }
        });
        teamyellow = (Button)findViewById(R.id.teamyellow_btn);
        teamyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(TeamChoiceActivity.this,TeamMemberActivity.class);
                intent3.putExtra("teamNum","3");
                intent3.putExtra("accessToken",getToken);
                startActivity(intent3);
            }
        });
        teamviolet = (Button)findViewById(R.id.teamviolet_btn);
        teamviolet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(TeamChoiceActivity.this,TeamMemberActivity.class);
                intent4.putExtra("teamNum","4");
                intent4.putExtra("accessToken",getToken);
                startActivity(intent4);
            }
        });

    }

}
