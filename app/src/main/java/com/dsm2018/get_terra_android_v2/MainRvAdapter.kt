package com.dsm2018.get_terra_android_v2


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.*

class MainRvAdapter (val context: Context, private val boothNameList : ArrayList<BoothNameList>):RecyclerView.Adapter<MainRvAdapter.BoothHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BoothHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main,p0, false)
        return BoothHolder(view)
    }

    override fun getItemCount() :Int{
        return boothNameList.size
    }

    override fun onBindViewHolder(holder: BoothHolder, position: Int) {
        /*if(boothNameList[position].isToken){
            when(boothNameList[position].teamColor){
                0 -> holder.boothText.setTextColor(0xFFFFFF)
                else -> holder.boothText.setTextColor(0x000000)
            }
        } else {
            holder.boothText.setTextColor(0xFFB587)
        }*/
        holder?.bind(boothNameList[position], context)
    }
    inner class BoothHolder(v:View) : RecyclerView.ViewHolder(v){
        val boothText = v?.findViewById<TextView>(R.id.main_item_tv)
        fun bind(boothInfo: BoothNameList, context: Context)
        {
            boothText?.text = boothInfo.boothName
        }
    }
}