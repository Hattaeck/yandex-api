package ru.yandex.disk;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class DiskRetrofitTest {
    private static YandexDiskService service;
    private final String myToken = "OAuth " + System.getenv("YANDEX_TOKEN");

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
    @DisplayName("GET: Инфо о диске")
    public void testGetDiskInfo() throws IOException {
        Response<Void> response = service.getDiskInfo(myToken).execute();
        Assertions.assertEquals(200, response.code());
    }

    @Test
    @DisplayName("PUT: Создание папки")
    public void testCreateFolder() throws IOException {
        String folderPath = "TestFolder_" + System.currentTimeMillis();
        Response<Void> response = service.createFolder(myToken, folderPath).execute();
        Assertions.assertEquals(201, response.code());
    }

    @Test
    @DisplayName("POST: Копирование ресурса")
    public void testCopyFolder() throws IOException {
        String from = "FolderForCopy_" + System.currentTimeMillis();
        service.createFolder(myToken, from).execute();

        String to = "CopiedFolder_" + System.currentTimeMillis();
        Response<Void> response = service.copyResource(myToken, from, to).execute();

        int code = response.code();

        Assertions.assertTrue(code == 201 || code == 202);
    }

    @Test
    @DisplayName("DELETE: Удаление ресурса")
    public void testDeleteFolder() throws IOException {
        String path = "FolderToDelete_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> response = service.deleteResource(myToken, path).execute();
        Assertions.assertEquals(204, response.code());
    }
}