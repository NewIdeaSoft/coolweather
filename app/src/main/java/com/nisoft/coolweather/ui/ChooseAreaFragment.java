package com.nisoft.coolweather.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nisoft.coolweather.R;
import com.nisoft.coolweather.db.City;
import com.nisoft.coolweather.db.County;
import com.nisoft.coolweather.db.Province;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backupButton;
    private ListView listView;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;

    private ArrayList<Province> provinceList;
    private ArrayList<City> cityList;
    private ArrayList<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private County selectedCounty;
    private int currentLevel;
    public static ChooseAreaFragment newInstance() {
        Bundle args = new Bundle();
        ChooseAreaFragment fragment = new ChooseAreaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area,container,false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backupButton = (Button) view.findViewById(R.id.backup_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,dataList);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
                    queryCities();
                }else if (currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
            }
        });
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY){
                    queryCities();
                }else if (currentLevel == LEVEL_CITY){
                    queryProvinces();
                }
            }
        });
    }

    private void queryProvinces() {
        titleText.setText("中国");
        backupButton.setVisibility(View.GONE);
        provinceList = new ArrayList<>(DataSupport.findAll(Province.class));
        if (provinceList.size()>0){
            dataList.clear();
            for(Province province:provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        }else{
            String adress = "http://guolin.tech/api/china";
            queryFromServer(adress,"province");
        }
    }



    private void queryCounties() {
    }

    private void queryCities() {
    }
    private void queryFromServer(String adress, String province) {
    }
}
