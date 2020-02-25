package com.rationalstudio.hospitalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rationalstudio.hospitalapp.Fragments.OnlineDetailFragment;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.Models.OnlineModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReportHistoryAdapter extends RecyclerView.Adapter<ReportHistoryAdapter.ViewHolder> {
    List<OnlineModel> list;
    Context context;

    public ReportHistoryAdapter(List<OnlineModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reporthistorylayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.reporthistorytypename.setText(list.get(position).getTur().toString()+ " is done");
holder.reporthistoryInformation.setText(list.get(position).getDocisim().toString()+ " looked at you on " + list.get(position).getRandevutarih());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView reporthistorytypename,reporthistoryInformation;


        public ViewHolder( View itemView) {
            super(itemView);
            reporthistorytypename = itemView.findViewById(R.id.reporthistorytypename);
            reporthistoryInformation = itemView.findViewById(R.id.reporthistoryInformation);




        }
    }
}
