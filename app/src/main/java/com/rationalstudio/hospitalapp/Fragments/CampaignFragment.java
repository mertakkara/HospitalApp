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
import android.widget.Toast;

import com.rationalstudio.hospitalapp.Adapters.CampaignAdapter;
import com.rationalstudio.hospitalapp.Models.CampaignModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;


public class CampaignFragment extends Fragment {
    private View view;
    private RecyclerView campaignRecyclerView;
    private ChangeFragments changeFragments;
    private CampaignAdapter campaignAdapter;
    private List<CampaignModel> campaignModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_campaign, container, false);
        campaignRecyclerView= view.findViewById(R.id.campaignRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        campaignRecyclerView.setLayoutManager(layoutManager);
        changeFragments = new ChangeFragments(getContext());
        campaignModelList = new ArrayList<>();
        getCampaign();
        return view;
    }
    public void getCampaign(){
        Call<List<CampaignModel>> req= ManagerAll.getInstance().getCampaign();
        req.enqueue(new Callback<List<CampaignModel>>() {
            @Override
            public void onResponse(Call<List<CampaignModel>> call, Response<List<CampaignModel>> response) {
                if(response.body().get(0).isTf()){
                    campaignModelList = response.body();
                    campaignAdapter = new CampaignAdapter(campaignModelList,getContext());
                    campaignRecyclerView.setAdapter(campaignAdapter);

                }else{
                    Toast.makeText(getContext(), "No Discount",Toast.LENGTH_SHORT).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<CampaignModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_SHORT).show();
            }
        });
    }



}
