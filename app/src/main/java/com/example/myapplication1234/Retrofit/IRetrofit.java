package com.example.myapplication1234.Retrofit;


import java.util.List;

import ir.eyrsa.app.Model.Chat_Model;
import ir.eyrsa.app.Model.GetNameLessonsJozve_Model;
import ir.eyrsa.app.Model.GozineExamReview_Model;
import ir.eyrsa.app.Model.GroupNameLessonsOfflineExam_Model;
import ir.eyrsa.app.Model.InformationExam_Model;
import ir.eyrsa.app.Model.InformationLessons_Model;
import ir.eyrsa.app.Model.Jozve_Model;
import ir.eyrsa.app.Model.NameLessonsOfflineExam_Model;
import ir.eyrsa.app.Model.Reshte_Model;
import ir.eyrsa.app.Model.ReviewExam_Model;
import ir.eyrsa.app.Model.ShowQuestionsOffine_Model;
import ir.eyrsa.app.Model.ShowQuestions_Model;
import ir.eyrsa.app.Model.Taraz_Model;
import ir.eyrsa.app.Model.UserInformation_Model;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    // USER API

    @POST("Register.php")
    Call<ResponseBody> RegisterUser(@Query("U_iduser") String U_id,
                                    @Query("U_idreshte") int U_idreshte);

    @POST("SelectReshte.php")
    Call<List<Reshte_Model>> SelectReshte();

}
