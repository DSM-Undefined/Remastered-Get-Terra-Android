package com.dsm2018.get_terra_android_v2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    var color = 1
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
        val topBackGround = findViewById<View>(R.id.main_topbackground_v)

        when (color) { // 배경의 색을 결정지음.
            0 -> topBackGround.setBackgroundResource(R.drawable.back_teamgreen_main) // 초록팀의 경우 초록색으로.
            1 -> topBackGround.setBackgroundResource(R.drawable.back_teamblue_main)  // 파란팀의 경우 파란색으로.
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IntentIntegrator.REQUEST_CODE){
            var res : IntentResult? = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
            if(res?.contents == null){
                Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
                Log.i("fail","QR")
            }
            else{ // res.contents : QR코드 스캔 결과
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                print(res.contents)
                Log.i(res.contents,"QR")
            }
        }
    }
}
