package com.dsm2018.get_terra_android_v2

import android.content.Intent
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.dsm2018.get_terra_android_v2.Connector.API
import com.dsm2018.get_terra_android_v2.Connector.GetMap
import com.dsm2018.get_terra_android_v2.Connector.ServiceGenerator
import com.google.gson.JsonObject
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    var color = "#00aaff"
    var boothNameList = arrayListOf<BoothNameList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBackground()                    // 팀 색상 설정

        val QRCodeButton = findViewById<ImageView>(R.id.main_qrcode_btn)
        QRCodeButton.setOnClickListener { // QR코드 버튼 눌리면 QR코드 스캐너 시작.
            startQRCodeScanner()
        }
    }

    override fun onResume() {//액티비티 재실행시에 리사이클러뷰 재생성
        super.onResume()
        setBoothName()                    //부스 데이터 설정
        setRecyclerView()                  // 부스 리스트 설정,보여주기
    }

    override fun onPause() { // 리사이클러뷰 데이터 삭제
        super.onPause()
        boothNameList.clear()
    }

    fun setBackground() : Unit{ // 각 팀에 대한 색상 레이아웃 설정
        val topBackground = findViewById<ImageView>(R.id.main_topbackground_v)
        topBackground.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
    }

    fun getAPI() : JSONObject{
        var service = ServiceGenerator.createService(API::class.java)
        var call : Call<GetMap> = service.getMap("토오오오큰")
        var boothList = JSONObject()
        call.enqueue(object : Callback<GetMap>{
            override fun onResponse(call : Call<GetMap>, response : Response<GetMap>){
                val repo : GetMap = response.body()
                color = repo.myTeamColor
                boothList = repo.map
            }
            override fun onFailure(call : Call<GetMap>, t : Throwable){
            }
        })
        return boothList
    }

    fun setBoothName() : Unit{ // 부스이름(동아리명)데이터 등록N
        /*val boothList = getAPI()
        var i = boothList.keys()
        var keyList = ArrayList<String>()
        var idx = 0
        while(i.hasNext()){
            var b = i.next().toString()
            keyList.add(b)
            var t = boothList.getInt(keyList.get(idx))
            idx++
            boothNameList.add(BoothNameList(b,color,t))
        }*/
        boothNameList = arrayListOf<BoothNameList>(
                BoothNameList("Undefined", color, 1),
                BoothNameList("Gram", color, 0),
                BoothNameList("시나브로", color, -1),
                BoothNameList("Lapio", color, 1),
                BoothNameList("Sweetfab", color, 1),
                BoothNameList("GG", color, 0),
                BoothNameList("MoDeep", color, -1),
                BoothNameList("Bench",color, 1),
                BoothNameList("D", color, 0),
                BoothNameList("Nonamed", color, 1),
                BoothNameList("Phantom",color,0)
        )
        boothNameList.add(BoothNameList("ClubName", color, 1))
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
            }
            else{ // res.contents : QR코드 스캔 결과
                val intent = Intent(this, MultiplechoiceActivity::class.java)
                intent.putExtra("sendBoothName", res.contents)
                startActivity(intent)
            }
        }
    }


}
