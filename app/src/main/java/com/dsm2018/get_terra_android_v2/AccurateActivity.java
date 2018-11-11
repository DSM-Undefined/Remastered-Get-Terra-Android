package com.dsm2018.get_terra_android_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AccurateActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accurate);

        Button btn_gomian = (Button)findViewById(R.id.btn_main);
        btn_gomian.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccurateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

