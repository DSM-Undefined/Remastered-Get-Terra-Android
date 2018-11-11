package com.dsm2018.get_terra_android_v2.Connector

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetMap{
    /*@SerializedName("map")
    @Expose
    private var map:List<BoothList> = null*/
    @SerializedName("myTeam")
    @Expose
    var myTeam : Int = 0
    @SerializedName("myTeamColor")
    private var myTeamColor = 0
}

class BoothList()