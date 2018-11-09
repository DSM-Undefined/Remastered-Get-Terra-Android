package com.dsm2018.get_terra_android_v2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    var color = 1
    var boothNameList = arrayListOf<BoothNameList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackground()                    // 팀 색상 설정
        setBoothName()
        setRecyclerView()                  // 부스 리스트 설정

        val QRCodeButton = findViewById<ImageView>(R.id.main_qrcode_btn)
        QRCodeButton.setOnClickListener {
            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
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
}
