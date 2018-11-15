package com.dsm2018.get_terra_android_v2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.dsm2018.get_terra_android_v2.Connector.API
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator
import com.dsm2018.get_terra_android_v2.Connector.solveGet
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MultiplechoiceActivity : AppCompatActivity() {
    var choices = ArrayList<String>()
     var content: String = ""
    var problemId: String = ""
    lateinit var contents: solveGet
    lateinit var per: String
    var postcode: Int = 0
    var list_cnt: Int = 0
    lateinit var answer: ArrayList<String>
    var answer1: String = ""
    var answer2: String = ""
    var answer3: String = ""
    var answer4: String = ""

    /*val getintent = Intent(this, MainActivity::class.java)
    val i = getintent
    val a = i.getStringExtra("token")
    val b = i.getStringExtra("sendBoothName")

*/
    var authorization: String = ""
    var boothName: String = ""

    fun get(Authorization: String, boothName: String) {
        var Authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NDIyODgwMDEsIm5iZiI6MTU0MjI4ODAwMSwianRpIjoiOGNmMmM5YjctNmVlNy00N2I4LTkxMDctMjg3YjQ0NGE4ZGUyIiwiZXhwIjoxNTQzMTUyMDAxLCJpZGVudGl0eSI6InRtZGFscyIsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyIsInVzZXJfY2xhaW1zIjp7InVzZXJfaWQiOiJ0bWRhbHMiLCJnYW1lX2tleSI6MTAwMDAwfX0.MIROH2Weaw3XU3_4TeJ8qJpuOH1pUDn3rke3WeFhebA"
        var boothName = "undefined"
        val api: API
        api = ServiceGenerator.createService(API::class.java)
        var rep = JsonObject()
        rep.addProperty("problemId", problemId)
        var call = api.getQuestion(Authorization, boothName)
        call.enqueue(object : Callback<solveGet> {
            override fun onResponse(call: Call<solveGet>, response: Response<solveGet>) {
                if (response.isSuccessful) {
                    contents = response.body()
                    getcode = response.code()
                    /*BoothName = contents.get(0).getBoothName().toString()
                    choices = contents.get("choices").toString()
                    content = contents.get("content").toString()
                    problemId = contents.get("problemId").toString()*/
                    this@MultiplechoiceActivity.boothName = contents.boothName
                    this@MultiplechoiceActivity.choices = contents.choices
                    this@MultiplechoiceActivity.content = contents.content
                    this@MultiplechoiceActivity.problemId = contents.problemId


                    val answer_1 = findViewById<Button>(R.id.answer_1)
                    val answer_2 = findViewById<Button>(R.id.answer_2)
                    val answer_3 = findViewById<Button>(R.id.answer_3)
                    val answer_4 = findViewById<Button>(R.id.answer_4)
                    val quizcontent = findViewById<TextView>(R.id.quizcontent)

                    quizcontent.setText(content)
                    answer_1.setText("   1. " + choices[0])
                    answer_2.setText("   2. " + choices[1])
                    answer_3.setText("   3. " + choices[2])
                    answer_4.setText("   4. " + choices[3])
                }
                Log.e("서버연동성공"," ")
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<solveGet>?, t: Throwable?) {
                Log.e("asdf", "서버연동실패")
            }
        })
    }

    fun post(Authorization: String, boothName: String, problemId: String, answer: String) {
        var Authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NDIyODgwMDEsIm5iZiI6MTU0MjI4ODAwMSwianRpIjoiOGNmMmM5YjctNmVlNy00N2I4LTkxMDctMjg3YjQ0NGE4ZGUyIiwiZXhwIjoxNTQzMTUyMDAxLCJpZGVudGl0eSI6InRtZGFscyIsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyIsInVzZXJfY2xhaW1zIjp7InVzZXJfaWQiOiJ0bWRhbHMiLCJnYW1lX2tleSI6MTAwMDAwfX0.MIROH2Weaw3XU3_4TeJ8qJpuOH1pUDn3rke3WeFhebA"
        var boothName = "undefined"
        val api = ServiceGenerator.createService(API::class.java)
        lateinit var postobj: JsonObject
        postobj.addProperty("problemId", problemId)
        postobj.addProperty("answer", answer)
        val call = api.postQuestion(Authorization, boothName, postobj)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    postcode = response.code()
                }
                Log.e("서버연동성공", " ㅁㄴㅇㄹ")
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MultiplechoiceActivity, "서버꺼짐", Toast.LENGTH_SHORT).show()
                Log.e("서버연동실패", "ㅁㄴㅇㄹ")
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
        val quizcontent = findViewById<TextView>(R.id.quizcontent)
        get("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NDIyODgwMDEsIm5iZiI6MTU0MjI4ODAwMSwianRpIjoiOGNmMmM5YjctNmVlNy00N2I4LTkxMDctMjg3YjQ0NGE4ZGUyIiwiZXhwIjoxNTQzMTUyMDAxLCJpZGVudGl0eSI6InRtZGFscyIsImZyZXNoIjpmYWxzZSwidHlwZSI6ImFjY2VzcyIsInVzZXJfY2xhaW1zIjp7InVzZXJfaWQiOiJ0bWRhbHMiLCJnYW1lX2tleSI6MTAwMDAwfX0.MIROH2Weaw3XU3_4TeJ8qJpuOH1pUDn3rke3WeFhebA", "undefined")

        when (getcode) {
            201 -> Toast.makeText(this, "성공", Toast.LENGTH_SHORT)
            204 -> {
                Toast.makeText(this, "해당이름의 부스없음", Toast.LENGTH_SHORT);finish()
            }
            205 -> {
                Toast.makeText(this, "이미 해당팀에서 점령된 부스임", Toast.LENGTH_SHORT);finish()
            }
            401 -> {
                Toast.makeText(this, "request header 에 access token 없음", Toast.LENGTH_SHORT);finish()
            }
            406 -> {
                Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT);finish()
            }
            408 -> {
                Toast.makeText(this, "딜레이시간", Toast.LENGTH_SHORT);finish()
            }
            412 -> {
                Toast.makeText(this, "게임종료", Toast.LENGTH_SHORT);finish()
            }
        }

        answer_1.setOnClickListener {
            post(authorization, boothName, problemId, answer1)
            when (postcode) {
                201 -> {
                    val nextIntent = Intent(this, AccurateActivity::class.java)
                    startActivity(nextIntent)
                }
                205 -> {
                    val nextIntent = Intent(this, FailActivity::class.java)
                    startActivity(nextIntent)
                }
                401 -> Toast.makeText(this, "request header 에 access token 없음", Toast.LENGTH_SHORT)
                403 -> Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT)
                406 -> Toast.makeText(this, "게임 시작 전", Toast.LENGTH_SHORT)
                408 -> Toast.makeText(this, "딜레이시간", Toast.LENGTH_SHORT)
                412 -> Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
            }
        }
        answer_2.setOnClickListener {
            post(authorization, boothName, problemId, answer2)
            when (postcode) {
                201 -> {
                    val nextIntent = Intent(this, AccurateActivity::class.java)
                    startActivity(nextIntent)
                }
                205 -> {
                    val nextIntent = Intent(this, FailActivity::class.java)
                    startActivity(nextIntent)
                }
                401 -> Toast.makeText(this, "request header 에 access token 없음", Toast.LENGTH_SHORT)
                403 -> Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT)
                406 -> Toast.makeText(this, "게임 시작 전", Toast.LENGTH_SHORT)
                408 -> Toast.makeText(this, "딜레이시간", Toast.LENGTH_SHORT)
                412 -> Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
            }
        }
        answer_3.setOnClickListener {
            post(authorization, boothName, problemId, answer3)
            when (postcode) {
                201 -> {
                    val nextIntent = Intent(this, AccurateActivity::class.java)
                    startActivity(nextIntent)
                }
                205 -> {
                    val nextIntent = Intent(this, FailActivity::class.java)
                    startActivity(nextIntent)
                }
                401 -> Toast.makeText(this, "request header 에 access token 없음", Toast.LENGTH_SHORT)
                403 -> Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT)
                406 -> Toast.makeText(this, "게임 시작 전", Toast.LENGTH_SHORT)
                408 -> Toast.makeText(this, "딜레이시간", Toast.LENGTH_SHORT)
                412 -> Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
            }
        }
        answer_4.setOnClickListener {
            post(authorization, boothName, problemId, answer4)
            when (postcode) {
                201 -> {
                    val nextIntent = Intent(this, AccurateActivity::class.java)
                    startActivity(nextIntent)
                }
                205 -> {
                    val nextIntent = Intent(this, FailActivity::class.java)
                    startActivity(nextIntent)
                }
                401 -> Toast.makeText(this, "request header 에 access token 없음", Toast.LENGTH_SHORT)
                403 -> Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT)
                406 -> Toast.makeText(this, "게임 시작 전", Toast.LENGTH_SHORT)
                408 -> Toast.makeText(this, "딜레이시간", Toast.LENGTH_SHORT)
                412 -> Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT)
            }
        }
    }
}
