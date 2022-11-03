package com.example.sitectestapp.data.network;

import com.example.sitectestapp.data.response.AuthResponse;
import com.example.sitectestapp.data.response.UsersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SiTecApi {

    @GET("/UKA_TRADE/hs/MobileClient/{IMEI}/form/users/")
    Single<UsersResponse> getUsersList(@Path(value = "IMEI", encoded = true) String imei);

    @GET("/UKA_TRADE/hs/MobileClient/{IMEI}/authentication/")
    Single<AuthResponse> performAuthentication(@Path(value = "IMEI", encoded = true) String imei,
                                               @Query("uid") String uid,
                                               @Query("pass") String pass,
                                               @Query("copyFromDevice") String copyFromDevice,
                                               @Query("nfc") String nfc);
}
