package com.example.myapplication1234.Retrofit;


import com.example.myapplication1234.Model.get_category;
import com.example.myapplication1234.Model.test;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    // USER API

//    @POST("Register.php")
//    Call<ResponseBody> RegisterUser(@Query("U_iduser") String U_id,
//                                    @Query("U_idreshte") int U_idreshte);

//    @POST("SelectReshte.php")
//    Call<List<test>> SelectReshte();


    // add category
    @POST("add_category.php")
    Call<ResponseBody> add_category(@Query("text") String text);

    // add get category
    @POST("get_category.php")
    Call<List<get_category>> set_category();

    //add product
    @POST("add_product.php")
    Call<ResponseBody> add_product(@Query("text") String text,@Query("price") int price,
                                   @Query("num") int num,@Query("c_id") int c_id);
}
