package ru.yandex.disk;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskRetrofitTest {

    private static YandexDiskService service;
    private final String myToken = "OAuth y0__xDY5dfkCBjblgMgmpLighe_kMxdgmz3m_cibxKubwKblgr4Cg";

    @BeforeAll
    public static void setup() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cloud-api.yandex.net/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(YandexDiskService.class);
    }

    @Test
    public void testGetDiskInfo() throws IOException {
        Response<Void> response = service.getDiskInfo(myToken).execute();
        assertEquals(200, response.code());
    }
}