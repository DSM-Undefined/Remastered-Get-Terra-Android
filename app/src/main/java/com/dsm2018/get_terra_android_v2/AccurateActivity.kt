package com.dsm2018.get_terra_android_v2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button


class AccurateActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accurate)

        val btn_gomian = findViewById<View>(R.id.btn_main) as Button
        btn_gomian.setOnClickListener {
            var intent = Intent(this@AccurateActivity, MainActivity::class.java)
        }
        /*btn_gomian.setOnClickListener(object : Button.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@AccurateActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })*/

    }
}

