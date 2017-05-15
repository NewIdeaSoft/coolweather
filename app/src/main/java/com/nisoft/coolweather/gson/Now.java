package com.nisoft.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/15.
 */

public class Now {
    @SerializedName("tmp")
    public String temperatrue;
    @SerializedName("cond")
    public More more;
    public class More{
        public String info;
    }
}
