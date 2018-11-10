package com.dsm2018.get_terra_android_v2

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    var color = "#ffffff"
    var boothNameList = arrayListOf<BoothNameList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackground()                    // 팀 색상 설정
        setBoothName()                      //부스 데이터 설정
        setRecyclerView()                  // 부스 리스트 설정

        val QRCodeButton = findViewById<ImageView>(R.id.main_qrcode_btn)
        QRCodeButton.setOnClickListener { // QR코드 버튼 눌리면 QR코드 스캐너 시작.
            startQRCodeScanner()
        }
    }

    fun setBackground() : Unit{ // 각 팀에 대한 색상 레이아웃 설정
        val topBackground = findViewById<ImageView>(R.id.main_topbackground_v)
        topBackground.drawable.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
        /*when (color) { // 배경의 색을 결정지음.
            0 -> {topBackGround.setBackgroundColor(0xffffff)
            topBackGround.setBackgroundResource(R.drawable.back_teamgreen_main)} // 초록팀의 경우 초록색으로.
            1 -> {topBackGround.setBackgroundColor(0xffffff)
                topBackGround.setBackgroundResource(R.drawable.back_teamblue_main)}  // 파란팀의 경우 파란색으로.
        }*/
    }

    fun setBoothName() : Unit{ // 부스이름(동아리명)데이터 등록
        boothNameList = arrayListOf<BoothNameList>(
                BoothNameList("Undefined", color, true),
                BoothNameList("Gram", color, false),
                BoothNameList("시나브로", color, true),
                BoothNameList("Lapio", color, true),
                BoothNameList("Sweetfab", color, false),
                BoothNameList("GG", color, true),
                BoothNameList("MoreDeep", color, false),
                BoothNameList("Bench",color, true),
                BoothNameList("D", color, false),
                BoothNameList("Nonamed", color, true),
                BoothNameList("ClubName", color, true)
        )
    }

    fun setRecyclerView() : Unit{ // 리사이클러뷰 생성

        val adapter = MainRvAdapter(this, boothNameList)
        val lm = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.main_recyclerview_rv)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)
    }
    fun startQRCodeScanner(): Unit{ // QR코드 스캐너 시작
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // qr코드 처리
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IntentIntegrator.REQUEST_CODE){
            var res : IntentResult? = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
            if(res?.contents == null){
                Log.i("fail","QR")
            }
            else{ // res.contents : QR코드 스캔 결과
                val intent = Intent(this, MultiplechoiceActivity::class.java)
                intent.putExtra("sendBoothName", res.contents)
                startActivity(intent)
            }
        }
    }


}
