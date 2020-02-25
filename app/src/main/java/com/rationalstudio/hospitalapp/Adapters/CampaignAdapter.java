package com.rationalstudio.hospitalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rationalstudio.hospitalapp.Models.AnswersModel;
import com.rationalstudio.hospitalapp.Models.CampaignModel;
import com.rationalstudio.hospitalapp.Models.DeleteAnswerModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.Warnings;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder> {
    List<CampaignModel> list;
    Context context;

    public CampaignAdapter(List<CampaignModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.campaignitemlayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.campaigntext.setText(list.get(position).getText().toString());
holder.campaigntitle.setText(list.get(position).getBaslik().toString());



    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView campaigntitle,campaigntext;

        public ViewHolder( View itemView) {
            super(itemView);
            campaigntitle = itemView.findViewById(R.id.campaigntitle);

            campaigntext = itemView.findViewById(R.id.campaigntext);



        }
    }
}
