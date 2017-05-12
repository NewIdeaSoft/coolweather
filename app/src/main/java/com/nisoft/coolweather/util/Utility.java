package com.nisoft.coolweather.util;

import android.text.TextUtils;

import com.nisoft.coolweather.db.City;
import com.nisoft.coolweather.db.County;
import com.nisoft.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Utility {
    /**
     * 解析响应数据 省级
     */
    public static boolean handleProvinceResponce(String responce){
        if (!TextUtils.isEmpty(responce)){
            try{
                JSONArray allProvince = new JSONArray(responce);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject object = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(object.getString("name"));
                    province.setId(object.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析响应数据 市级
     */
    public static boolean handleCityResponce(String responce,int provinceId){
        if (!TextUtils.isEmpty(responce)){
            try{
                JSONArray allProvince = new JSONArray(responce);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject object = allProvince.getJSONObject(i);
                    City city = new City();
                    city.setCityName(object.getString("name"));
                    city.setId(object.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析响应数据 县级
     */
    public static boolean handleCountyResponce(String responce,int cityId){
        if (!TextUtils.isEmpty(responce)){
            try{
                JSONArray allProvince = new JSONArray(responce);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject object = allProvince.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getInt("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
