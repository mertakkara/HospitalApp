package com.rationalstudio.hospitalapp.RestApi;


import com.rationalstudio.hospitalapp.Models.AnswersModel;
import com.rationalstudio.hospitalapp.Models.AppoinmentModel;
import com.rationalstudio.hospitalapp.Models.AskQuestionModel;
import com.rationalstudio.hospitalapp.Models.CampaignModel;
import com.rationalstudio.hospitalapp.Models.DeleteAnswerModel;
import com.rationalstudio.hospitalapp.Models.LoginModel;
import com.rationalstudio.hospitalapp.Models.OnlineModel;
import com.rationalstudio.hospitalapp.Models.RegisterPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("/kayitol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdress") String mailAdress, @Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("/girisyap.php")
    Call<LoginModel> loginUser(@Field("mailadres") String mailAdress, @Field("sifre") String pass);

    @FormUrlEncoded
    @POST("/randevularim.php")
    Call<List<AppoinmentModel>> getAppoinments(@Field("musid") String mus_id);

    @FormUrlEncoded
    @POST("/sorusor.php")
    Call<AskQuestionModel> askQuestion(@Field("id") String id, @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/cevaplar.php")
    Call<List<AnswersModel>> getAnswers(@Field("musid") String musid);

    @FormUrlEncoded
    @POST("/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("cevap") String cevapid, @Field("soru") String soruid);


    @GET("/kampanya.php")
    Call<List<CampaignModel>> getCampaign();

    @FormUrlEncoded
    @POST("/randevutakip.php")
    Call<List<OnlineModel>> getOnline(@Field("id") String id);

    @FormUrlEncoded
    @POST("/gecmisrandevu.php")
    Call<List<OnlineModel>> getGecmisRandevu(@Field("id") String id,@Field("docid") String doc_id);
}
