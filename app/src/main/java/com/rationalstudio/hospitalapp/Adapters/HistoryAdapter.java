package com.rationalstudio.hospitalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rationalstudio.hospitalapp.Fragments.OnlineDetailFragment;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.Utils.ChangeFragments;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<AppoinmentModel> list;
    Context context;

    public HistoryAdapter(List<AppoinmentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historylayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.historydocname.setText(list.get(position).getDokismi().toString());
        holder.historyInformation.setText("Click to see past appointments for this " + list.get(position).getTur().toString());
        holder.historylayoutcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeFragments changeFragments = new ChangeFragments(context);
                changeFragments.changeWithParameters(new OnlineDetailFragment(),list.get(position).getRandid());
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView historydocname,historyInformation;
        CardView historylayoutcardview;

        public ViewHolder( View itemView) {
            super(itemView);
            historydocname = itemView.findViewById(R.id.historydocname);
            historyInformation = itemView.findViewById(R.id.historyInformation);
            historylayoutcardview = itemView.findViewById(R.id.historylayoutcardview);



        }
    }
}
