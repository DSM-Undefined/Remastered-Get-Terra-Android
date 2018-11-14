package com.dsm2018.get_terra_android_v2.Connector

import com.google.gson.JsonObject
import org.json.JSONObject

data class GetMap(val map : JsonObject, val myTeam: Int, val myTeamColor : String)