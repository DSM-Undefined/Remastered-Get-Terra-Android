package com.dsm2018.get_terra_android_v2;

import java.util.ArrayList;

public class RetrofitGetQuestion {
    public String boothName;
    public ArrayList[] choices;
    public String content;
    public String problemId;
    public String getboothName(){
        return boothName;
    }
    public ArrayList[] getChoices(){
        return choices;
    }
    public String getContent(){
        return content;
    }
    public String getProblemId(){
        return problemId;
    }
}
