package com.rationalstudio.hospitalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {
    List<AppoinmentModel> list;
    Context context;

    public AppointmentsAdapter(List<AppoinmentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointmentlistitemlayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.appoinmentLayoutappointmenttype.setText(list.get(position).getTur().toString());
        holder.appoinmentLayoutdocname.setText(list.get(position).getDokismi().toString());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView appoinmentLayoutappointmenttype,appoinmentLayoutdocname;
        CircleImageView appoinmentLayoutappointmentimage;
        public ViewHolder( View itemView) {
            super(itemView);
            appoinmentLayoutappointmenttype = itemView.findViewById(R.id.appoinmentLayoutappointmenttype);
            appoinmentLayoutdocname = itemView.findViewById(R.id.appoinmentLayoutdocname);
            appoinmentLayoutappointmentimage = itemView.findViewById(R.id.appoinmentLayoutappointmentimage);


        }
    }
}
