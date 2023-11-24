package com.gitittech.paygo.integrations.payments;

import com.gitittech.paygo.integrations.dtos.BillStatusResponse;
import com.gitittech.paygo.integrations.dtos.FlutterWaveBillerValidateRes;
import com.gitittech.paygo.integrations.dtos.FlutterWaveBillerResposne;
import com.gitittech.paygo.integrations.dtos.NewBillResponse;
import com.gitittech.paygo.integrations.dtos.NewBillRequest;

import retrofit2.Call;
import retrofit2.http.*;

public interface IFlutterwave {

    String ENDPOINT = "https://api.flutterwave.com/v3/";
    String FLUTTER_WAVE_KEY = "Bearer FLWSECK-f06b13600789b3c1b108677e1404d738-X";

    @GET("bill-categories")
    Call<FlutterWaveBillerResposne> getBillCategories(@Header("Content-Type") String contentType, @Header("Authorization") String token);

    @GET("bill-items/{item_code}/validate")
    Call<FlutterWaveBillerValidateRes> validate(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Path("item_code") String itemCode, @Query("code") String code, @Query("customer") String customer);

    @POST("bills")
    Call<NewBillResponse> createBill(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body NewBillRequest req);

    @GET("bills/{reference}")
    Call<BillStatusResponse> fetchBill(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Path("reference") String reference);
}
