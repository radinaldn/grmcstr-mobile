package com.gramcaster.radinaldn.aurelia.rest;

import com.gramcaster.radinaldn.aurelia.response.ResponseGetOrders;
import com.gramcaster.radinaldn.aurelia.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by radinaldn on 10/02/19.
 */

public interface ApiInterface {

    // Interface Login
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseLogin> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("order/find-all-by-id-user")
    Call<ResponseGetOrders> ordersFindAllByIdUser(
            @Query("users_id") String usersId
    );
}
