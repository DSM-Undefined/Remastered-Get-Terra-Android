package com.dsm2018.get_terra_android_v2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.dsm2018.get_terra_android_v2.Connector.API
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import undefined.dsm.getterra.ui.FailActivity
import java.util.*


class MultiplechoiceActivity : Activity() {
    var api: API? = null
    var boothName : String = ""
    var choices = ArrayList<String>()
    var content  :String = ""
    var problemId : String = ""
    var contents : JsonArray? = null
    var postcode : Int = 0
    var list_cnt : Int = 0
    var answer : String = ""
    var answer1 : String = ""
    var answer2 : String = ""
    var answer3 : String = ""
    var answer4 : String = ""

    fun get(Authorization : String , boothName: String,problemId  : String,answer: String){
        var api : API
        api = ServiceGenerator.createService()
        var req = JsonObject()
        req.addProperty("problemId",problemId)
        req.addProperty("answer",answer)
        val call = api.postQuestion(req)
        call.enqueue(object  : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    postcode = response.code()
                }
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this, "서버꺼짐", Toast.LENGTH_SHORT)
            }
        })
    }

    fun post(Authorization: String,boothName:String) {

        api = ServiceGenerator.createService()
        var postobj : JsonObject
        postobj.addProperty("problemId",problemId )
        postobj.addProperty("answer",answer)
        val call = api.getQuestion(boothName)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    contents = response.body()
                    getcode = response.code()
                }
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MultiplechoiceActivity, "서버꺼짐", Toast.LENGTH_SHORT).show()
            }
        })
    }


    internal var getcode: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplechoice)


        val answer_1 = findViewById<Button>(R.id.answer_1)
        val answer_2 = findViewById<Button>(R.id.answer_2)
        val answer_3 = findViewById<Button>(R.id.answer_3)
        val answer_4 = findViewById<Button>(R.id.answer_4)

        var getobj : JsonObject
        getobj = contents.asJsonObject
        boothName = getobj.getAsJsonObject("boothName").toString()
        choices = getobj.getAsJsonArray("choices")
        content = getobj.getAsJsonObject("content").toString()
        problemId= getobj.getAsJsonObject("problemId").toString()
        if(getcode == 201){
            Toast.makeText(this,"성공",Toast.LENGTH_SHORT)

        }else if(getcode == 204){
            Toast.makeText(this,"해당이름의 부스없음",Toast.LENGTH_SHORT)
        }else if(getcode == 205){
            Toast.makeText(this,"이미 해당팀에서 정렬된 부스임",Toast.LENGTH_SHORT)
        }else if(getcode == 401){
            Toast.makeText(this,"request header 에 access token 없음",Toast.LENGTH_SHORT)
        }else if(getcode == 406){
            Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT)
        }else if(getcode == 408){
            Toast.makeText(this,"딜레이시간",Toast.LENGTH_SHORT)
        }else if(getcode == 412){
            Toast.makeText(this,"게임종료",Toast.LENGTH_SHORT)
        }

        answer_1.setOnClickListener {

            if(postcode == 201){
                val nextIntent = Intent(this,AccurateActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode == 205){
                val nextIntent = Intent(this,FailActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode== 401){
                Toast.makeText(this,"request header 에 access token 없음",Toast.LENGTH_SHORT)
            }else if(postcode== 403){
                Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT)
            }else if(postcode== 406){
                Toast.makeText(this,"게임 시작 전",Toast.LENGTH_SHORT)
            }else if(postcode== 408){
                Toast.makeText(this,"딜레이시간",Toast.LENGTH_SHORT)
            }else if(postcode== 412){
                Toast.makeText(this,"게임이 종료되었습니다.",Toast.LENGTH_SHORT)
            }
        }
        answer_2.setOnClickListener {

            if(postcode == 201){
                val nextIntent = Intent(this,AccurateActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode == 205){
                val nextIntent = Intent(this,FailActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode== 401){
                Toast.makeText(this,"request header 에 access token 없음",Toast.LENGTH_SHORT)
            }else if(postcode== 403){
                Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT)
            }else if(postcode== 406){
                Toast.makeText(this,"게임 시작 전",Toast.LENGTH_SHORT)
            }else if(postcode== 408){
                Toast.makeText(this,"딜레이시간",Toast.LENGTH_SHORT)
            }else if(postcode== 412){
                Toast.makeText(this,"게임이 종료되었습니다.",Toast.LENGTH_SHORT)
            }
        }
        answer_3.setOnClickListener {

            if(postcode == 201){
                val nextIntent = Intent(this,AccurateActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode == 205){
                val nextIntent = Intent(this,FailActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode== 401){
                Toast.makeText(this,"request header 에 access token 없음",Toast.LENGTH_SHORT)
            }else if(postcode== 403){
                Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT)
            }else if(postcode== 406){
                Toast.makeText(this,"게임 시작 전",Toast.LENGTH_SHORT)
            }else if(postcode== 408){
                Toast.makeText(this,"딜레이시간",Toast.LENGTH_SHORT)
            }else if(postcode== 412){
                Toast.makeText(this,"게임이 종료되었습니다.",Toast.LENGTH_SHORT)
            }
        }
        answer_4.setOnClickListener {

            if(postcode == 201){
                val nextIntent = Intent(this,AccurateActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode == 205){
                val nextIntent = Intent(this,FailActivity::class.java)
                startActivity(nextIntent)
            }else if(postcode== 401){
                Toast.makeText(this,"request header 에 access token 없음",Toast.LENGTH_SHORT)
            }else if(postcode== 403){
                Toast.makeText(this,"권한없음",Toast.LENGTH_SHORT)
            }else if(postcode== 406){
                Toast.makeText(this,"게임 시작 전",Toast.LENGTH_SHORT)
            }else if(postcode== 408){
                Toast.makeText(this,"딜레이시간",Toast.LENGTH_SHORT)
            }else if(postcode== 412){
                Toast.makeText(this,"게임이 종료되었습니다.",Toast.LENGTH_SHORT)
            }
        }
    }
}
