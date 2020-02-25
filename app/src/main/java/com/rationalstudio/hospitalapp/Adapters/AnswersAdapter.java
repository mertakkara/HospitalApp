package com.rationalstudio.hospitalapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rationalstudio.hospitalapp.Models.AnswersModel;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.Models.DeleteAnswerModel;
import com.rationalstudio.hospitalapp.R;
import com.rationalstudio.hospitalapp.RestApi.ManagerAll;
import com.rationalstudio.hospitalapp.Utils.Warnings;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {
    List<AnswersModel> list;
    Context context;

    public AnswersAdapter(List<AnswersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.answeritemlayout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.questiontext.setText("Question: " + list.get(position).getSoru().toString());
holder.answertext.setText("Answer: " + list.get(position).getCevap().toString());
holder.anserdeletebutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        deleteToDb(list.get(position).getCevapid().toString(),list.get(position).getSoruid().toString(),position);
    }
});



    }
    public void deleteToList(int position ){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    private void deleteToDb(String cevapid, String soruid, final int position) {
        Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(cevapid,soruid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                if(response.body().isTf()){
                    if(response.isSuccessful()){
                        Toast.makeText(context,response.body().getText(),Toast.LENGTH_SHORT).show();
                        deleteToList(position);

                    }

                }else{
                    Toast.makeText(context,response.body().getText(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context, Warnings.internetProblemText,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView questiontext,answertext;
        MaterialButton anserdeletebutton;
        public ViewHolder( View itemView) {
            super(itemView);
            questiontext = itemView.findViewById(R.id.questiontext);

            answertext = itemView.findViewById(R.id.answertext);

            anserdeletebutton =(MaterialButton) itemView.findViewById(R.id.anserdeletebutton);

        }
    }
}
