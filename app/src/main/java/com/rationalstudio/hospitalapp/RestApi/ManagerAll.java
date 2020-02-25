package com.rationalstudio.hospitalapp.RestApi;

import com.rationalstudio.hospitalapp.Models.AnswersModel;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.Models.AskQuestionModel;
import com.rationalstudio.hospitalapp.Models.CampaignModel;
import com.rationalstudio.hospitalapp.Models.DeleteAnswerModel;
import com.rationalstudio.hospitalapp.Models.LoginModel;
import com.rationalstudio.hospitalapp.Models.OnlineModel;
import com.rationalstudio.hospitalapp.Models.RegisterPojo;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private  static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<RegisterPojo> kayitol(String mail , String username, String password)
    {
        Call<RegisterPojo> x = getRestApi().registerUser(mail,username,password);
        return  x ;
    }

    public Call<LoginModel> girisyap(String mail ,String password)
    {
        Call<LoginModel> x = getRestApi().loginUser(mail,password);
        return  x ;
    }
    public Call<List<AppoinmentModel>> getAppointmenst(String id )
    {
        Call<List<AppoinmentModel>> x = getRestApi().getAppoinments(id);
        return  x ;
    }
    public Call<AskQuestionModel> askQuestion(String id , String soru)
    {
        Call<AskQuestionModel> x = getRestApi().askQuestion(id,soru);
        return  x ;
    }
    public Call<List<AnswersModel>> getAnswers(String id )
    {
        Call<List<AnswersModel>> x = getRestApi().getAnswers(id);
        return  x ;
    }
    public Call<DeleteAnswerModel> deleteAnswer(String cevap, String soru )
    {
        Call<DeleteAnswerModel> x = getRestApi().deleteAnswer(cevap,soru);
        return  x ;
    }

    public Call<List<CampaignModel>> getCampaign()
    {
        Call<List<CampaignModel>> x = getRestApi().getCampaign();
        return  x ;
    }
    public Call<List<OnlineModel>> getOnline(String id )
    {
        Call<List<OnlineModel>> x = getRestApi().getOnline(id);
        return  x ;
    }
    public Call<List<OnlineModel>> getGecmisRandevu(String id,String doc_id )
    {
        Call<List<OnlineModel>> x = getRestApi().getGecmisRandevu(id,doc_id);
        return  x ;
    }

}
