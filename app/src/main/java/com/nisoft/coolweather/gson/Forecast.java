package com.nisoft.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Forecast {
    public String date;
    @SerializedName("tmp")
    public Temperature temperatrue;
    @SerializedName("cond")
    public More  more;
    public class Temperature{
        public String max;
        public String min;
    }
    public class More{
        @SerializedName("txt_d")
        public String info;
    }

}