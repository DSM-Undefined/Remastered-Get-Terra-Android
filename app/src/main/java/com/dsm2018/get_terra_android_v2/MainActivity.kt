package com.dsm2018.get_terra_android_v2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    var boothNameList = arrayListOf<BoothNameList>(
            BoothNameList("Undefined"),
            BoothNameList("Gram"),
            BoothNameList("시나브로"),
            BoothNameList("Lapio"),
            BoothNameList("Sweetfab")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackground(0)                    // 팀 색상 설정
        setRecyclerView()                  // 부스 리스트 설정
    }

    fun setBackground(color : Int) : Unit{
        val topBackGround = findViewById<View>(R.id.main_topbackground_v)

        when (color) { // 배경의 색을 결정지음.
            0 -> topBackGround.setBackgroundResource(R.drawable.back_teamgreen_main) // 초록팀의 경우 초록색으로.
            1 -> topBackGround.setBackgroundResource(R.drawable.back_teamblue_main)  // 파란팀의 경우 파란색으로.
        }
    }
    
    fun setRecyclerView() : Unit{

        val adapter = MainRvAdapter(this, boothNameList)
        val lm = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.main_recyclerview_rv)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)
    }
}
