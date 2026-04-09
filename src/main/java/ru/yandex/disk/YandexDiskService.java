package ru.yandex.disk;

import retrofit2.Call;
import retrofit2.http.*;

public interface YandexDiskService {

    @GET("v1/disk/")
    Call<Void> getDiskInfo(@Header("Authorization") String token);
}