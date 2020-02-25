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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rationalstudio.hospitalapp.Adapters.AppointmentsAdapter;
import com.rationalstudio.hospitalapp.Adapters.HistoryAdapter;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;
import com.rationalstudio.hospitalapp.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private RecyclerView historyrecyclerview;
    View view;
    private HistoryAdapter historyAdapter;
    private List<AppoinmentModel> appoinmentList;
    private ChangeFragments changeFragments;
    private String mus_id;
    private GetSharedPreferences getSharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);


        appoinmentList = new ArrayList<>();
        historyrecyclerview = view.findViewById(R.id.historyrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        historyrecyclerview.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        mus_id = getSharedPreferences.getSession().getString("id",null);
        getAppointments(mus_id);
        return  view;    }
    public void getAppointments(String mus_id){
        Call<List<AppoinmentModel>> req = ManagerAll.getInstance().getAppointmenst(mus_id);
        req.enqueue(new Callback<List<AppoinmentModel>>() {
            @Override
            public void onResponse(Call<List<AppoinmentModel>> call, Response<List<AppoinmentModel>> response) {
                if(response.body().get(0).isTf()){
                    appoinmentList = response.body();
                    historyAdapter = new HistoryAdapter(appoinmentList,getContext());
                    historyrecyclerview.setAdapter(historyAdapter);





                }else{
                    Toast.makeText(getContext(),"There is no appointment in the system",Toast.LENGTH_SHORT).show();
                    changeFragments.change(new  HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<AppoinmentModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_SHORT).show();                }
        });
    }



}
