package com.rationalstudio.hospitalapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rationalstudio.hospitalapp.Adapters.ReportHistoryAdapter;
import com.rationalstudio.hospitalapp.Models.OnlineModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;


public class OnlineDetailFragment extends Fragment {


private View view;
private String musid;
private String docId;
private GetSharedPreferences getSharedPreferences;
private RecyclerView onlinedetailView;
private ReportHistoryAdapter reportHistoryAdapter;
private List<OnlineModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_online_detail, container, false);
        onlinedetailView = view.findViewById(R.id.onlinedetailView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        onlinedetailView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        docId = getArguments().getString("docId").toString();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        musid= getSharedPreferences.getSession().getString("id",null);
        getGecmisRandevu();
        return view;
    }
    public void getGecmisRandevu(){
        Call<List<OnlineModel>> req = ManagerAll.getInstance().getGecmisRandevu(musid,docId);
        req.enqueue(new Callback<List<OnlineModel>>() {
            @Override
            public void onResponse(Call<List<OnlineModel>> call, Response<List<OnlineModel>> response) {
                if(response.body().get(0).isTf()){
list = response.body();
                    reportHistoryAdapter = new ReportHistoryAdapter(list,getContext());
                    onlinedetailView.setAdapter(reportHistoryAdapter);
                }else{
                    ChangeFragments changeFragments = new ChangeFragments(getContext());
                    changeFragments.change(new HistoryFragment());
                    Toast.makeText(getContext(),"You have no previous appointments with this doctor",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<OnlineModel>> call, Throwable t) {

            }
        });

    }

}
