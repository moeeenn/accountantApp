package com.example.myapplication1234.Retrofit;


import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    // USER API

    @POST("Register.php")
    Call<ResponseBody> RegisterUser(@Query("U_iduser") String U_id,
                                    @Query("U_idreshte") int U_idreshte);

//    @POST("SelectReshte.php")
//    Call<List<Reshte_Model>> SelectReshte();

}
