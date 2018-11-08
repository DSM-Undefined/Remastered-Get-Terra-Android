package com.dsm2018.get_terra_android_v2

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.dsm2018.get_terra_android_v2.Connector.API
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MultiplechoiceActivity : Activity() {



    fun post(Authorization: String,boothName:String) {
        val api: API
        api = ServiceGenerator.createService()
        val req = JsonObject()
        api.getQuestion(Authorization,boothName)
        val call = api.getQuestion(boothName)
        call.enqueue(object : Callback<List<RetrofitGetQuestion>> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    code = response.code()
                }
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MultiplechoiceActivity, "서버꺼짐", Toast.LENGTH_SHORT).show()
            }
        })
    }

    internal var code: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplechoice)


    }
}
