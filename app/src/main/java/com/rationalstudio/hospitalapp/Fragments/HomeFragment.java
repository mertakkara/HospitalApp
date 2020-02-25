package com.rationalstudio.hospitalapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rationalstudio.hospitalapp.Adapters.AnswersAdapter;
import com.rationalstudio.hospitalapp.Models.AnswersModel;
import com.rationalstudio.hospitalapp.Models.AskQuestionModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;
import com.rationalstudio.hospitalapp.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
   private View view;
   private LinearLayout appoinmentLayout,sorusorlinear,answerLayout,campaignlinearlayout,onlinelayout,historylayout;
   private ChangeFragments changeFragments;
   private GetSharedPreferences getSharedPreferences;
   private String id;
   private AnswersAdapter answersAdapter;
   private List<AnswersModel> answersModelList;
   private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        appoinmentLayout = view.findViewById(R.id.appoinmentLayout);
        sorusorlinear = view.findViewById(R.id.sorusorlinear);
        answerLayout = view.findViewById(R.id.answerLayout);
        onlinelayout = view.findViewById(R.id.onlinelayout);
        campaignlinearlayout=view.findViewById(R.id.campaignlinearlayout);
        historylayout = view.findViewById(R.id.historylayout);
        answersModelList = new ArrayList<>();

        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);

                action();

        return view;
    }
    public void action(){
        appoinmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new UserAppointmentFragment());
            }
        });
        sorusorlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionAlert();
            }
        });
        answerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAnswers(id);

            }
        });
        campaignlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new CampaignFragment());
            }
        });
        onlinelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new OnlineFragment());
            }
        });
        historylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new HistoryFragment());
            }
        });
    }

    private void getAnswers(String mus_id) {
        Call<List<AnswersModel>> req = ManagerAll.getInstance().getAnswers(mus_id);
        req.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
            if(response.body().get(0).isTf()){
                if(response.isSuccessful()){
                    answersModelList = response.body();
                    answersAdapter = new AnswersAdapter(answersModelList,getContext());
                    openAnswerAlert();
                }

            }else{
                Toast.makeText(getContext(), "There isnot any answer",Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<List<AnswersModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openQuestionAlert(){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.questionalertlayout,null);
        final EditText questionedittext = view.findViewById(R.id.questionedittext);
        MaterialButton questionbutton = view.findViewById(R.id.questionbutton);
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();
        questionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soru =questionedittext.getText().toString();
                questionedittext.setText("");
alertDialog.cancel();
askQuestion(id,soru,alertDialog);
            }
        });
        alertDialog.show();
    }
    public void askQuestion(String mus_id,String text,final AlertDialog alr){
        Call<AskQuestionModel> req=ManagerAll.getInstance().askQuestion(mus_id,text);
        req.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
                if(response.body().isTf()){
                    Toast.makeText(getContext(), response.body().getText(),Toast.LENGTH_SHORT).show();
                    alr.cancel();
                }else {
                    Toast.makeText(getContext(), response.body().getText(),Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<AskQuestionModel> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void openAnswerAlert(){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.answeralertlayout,null);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView = view.findViewById(R.id.answerrecyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(answersAdapter);

        alertDialog.show();
    }


}
