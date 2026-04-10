package ru.yandex.disk;

import retrofit2.Call;
import retrofit2.http.*;

public interface YandexDiskService {
    @GET("v1/disk/")
    Call<Void> getDiskInfo(@Header("Authorization") String token);

    @PUT("v1/disk/resources")
    Call<Void> createFolder(@Header("Authorization") String token, @Query("path") String path);

    @POST("v1/disk/resources/copy")
    Call<Void> copyResource(
            @Header("Authorization") String token,
            @Query("from") String from,
            @Query("path") String to
    );

    @DELETE("v1/disk/resources")
    Call<Void> deleteResource(@Header("Authorization") String token, @Query("path") String path);
}