package com.rationalstudio.hospitalapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rationalstudio.hospitalapp.Models.OnlineModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;
import com.rationalstudio.hospitalapp.Utils.GetSharedPreferences;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class OnlineFragment extends Fragment {

private View view;
private CalendarPickerView calenderPickerView;
private DateFormat dateFormat;
private Calendar nextYear;
private Date today;
private List<OnlineModel> onlineList;
private List<Date> dateList;
private String id;
private GetSharedPreferences getSharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_online, container, false);
        calenderPickerView = view.findViewById(R.id.calenderPickerView);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        today = new Date();
        calenderPickerView.init(today,nextYear.getTime());
        onlineList = new ArrayList<>();
        dateList = new ArrayList<>();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id= getSharedPreferences.getSession().getString("id",null);

        getOnline();
        clickToCalendar();
        return view;
    }
    public void getOnline(){
        Call<List<OnlineModel>> req = ManagerAll.getInstance().getOnline(id);
        req.enqueue(new Callback<List<OnlineModel>>() {
            @Override
            public void onResponse(Call<List<OnlineModel>> call, Response<List<OnlineModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().get(0).isTf()){
                        onlineList = response.body();
                        for(int i=0;i<onlineList.size();i++){
                            String dateString = response.body().get(i).getRandevutarih().toString();
                            try {
                                Date date = dateFormat.parse(dateString);
                                dateList.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        calenderPickerView.init(today,nextYear.getTime()).withHighlightedDates(dateList);
                    }

                }else{
                    ChangeFragments changeFragments= new ChangeFragments(getContext());
                    changeFragments.change(new HomeFragment());
                    Toast.makeText(getContext(),"There isnot any Appointment for you.",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<OnlineModel>> call, Throwable t) {

            }
        });
    }
    public void clickToCalendar(){
        calenderPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                for(int i=0;i<dateList.size();i++){
                    if(date.toString().equals(dateList.get(i).toString())){
                       openQuestionAlert(onlineList.get(i).getDocid().toString(),onlineList.get(i).getTur().toString(),onlineList.get(i).getRandevutarih().toString());
                    }
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
    public void openQuestionAlert(String docname,String tur,String date){
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.onlinelayout,null);
        TextView docName=view.findViewById(R.id.docName);
        TextView onlineInformation=view.findViewById(R.id.onlineInformation);

        docName.setText(docname);
        onlineInformation.setText("There is an appointment with " + tur + " on " + date);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        alertDialog.show();
    }

}
