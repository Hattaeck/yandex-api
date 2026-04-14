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

    @Test
    @DisplayName("GET: Получение инфо о созданном ресурсе")
    public void testGetResourceInfo() throws IOException {
        String path = "InfoFolder_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> response = service.getResourceInfo(myToken, path).execute();

        Assertions.assertEquals(200, response.code());

        service.deleteResource(myToken, path).execute();
    }

    @Test
    @DisplayName("POST: Перемещение/Переименование ресурса")
    public void testMoveFolder() throws IOException {
        String from = "OldFolder_" + System.currentTimeMillis();
        service.createFolder(myToken, from).execute();

        String to = "RenamedFolder_" + System.currentTimeMillis();

        Response<Void> response = service.moveResource(myToken, from, to).execute();

        Assertions.assertEquals(201, response.code());
    }

    @Test
    @DisplayName("DELETE: Ошибка 404 при удалении несуществующего ресурса")
    public void testDeleteNonExistentFolder() throws IOException {
        String path = "NonExistent_" + System.currentTimeMillis();

        Response<Void> response = service.deleteResource(myToken, path).execute();

        Assertions.assertEquals(404, response.code());
    }

    @Test
    @DisplayName("PUT: Ошибка 401 при неверном токене")
    public void testCreateFolderWithWrongToken() throws IOException {
        String badToken = "OAuth 12345";

        Response<Void> response = service.createFolder(badToken, "AnyPath").execute();

        Assertions.assertEquals(401, response.code());
    }

    @Test
    @DisplayName("PUT: Ошибка 409 при создании уже существующей папки")
    public void testCreateDuplicateFolder() throws IOException {
        String path = "Duplicate_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> response = service.createFolder(myToken, path).execute();

        Assertions.assertEquals(409, response.code());
    }

    @Test
    @DisplayName("DELETE: Полная очистка корзины")
    public void testClearTrash() throws IOException {
        String path = "ForTrash_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();
        service.deleteResource(myToken, path).execute();

        Response<Void> response = service.clearTrash(myToken).execute();
        int code = response.code();

        Assertions.assertTrue(code == 204 || code == 202,
                "Ожидался код 204 или 202, но пришел: " + code);
    }
}