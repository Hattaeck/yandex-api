package ru.yandex.disk;

import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import static ru.yandex.disk.constants.HttpStatusCode.*;

public class DiskNegativeTests extends BaseTest {

    @Test
    @DisplayName("PUT: Ошибка 401 при неверном токене")
    public void testCreateFolderWithWrongToken() throws IOException {
        String badToken = "OAuth 12345";
        Response<Void> response = service.createFolder(badToken, "AnyPath").execute();
        Assertions.assertEquals(UNAUTHORIZED.getCode(), response.code());
    }

    @Test
    @DisplayName("DELETE: Ошибка 404 при удалении несуществующего ресурса")
    public void testDeleteNonExistent() throws IOException {
        String path = "NonExistent_" + System.currentTimeMillis();
        Response<Void> response = service.deleteResource(myToken, path).execute();
        Assertions.assertEquals(NOT_FOUND.getCode(), response.code());
    }

    @Test
    @DisplayName("PUT: Ошибка 409 при создании дубликата")
    public void testCreateDuplicateFolder() throws IOException {
        String path = "Duplicate_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> response = service.createFolder(myToken, path).execute();
        Assertions.assertEquals(CONFLICT.getCode(), response.code());
    }

    @Test
    @DisplayName("GET: Ошибка 404 при запросе инфо несуществующего ресурса")
    public void testGetInfoNonExistent() throws IOException {
        Response<Void> response = service.getResourceInfo(myToken, "non_existent_path").execute();
        Assertions.assertEquals(NOT_FOUND.getCode(), response.code());
    }

    @Test
    @DisplayName("POST: Ошибка 404 при перемещении несуществующего ресурia")
    public void testMoveNonExistent() throws IOException {
        Response<Void> response = service.moveResource(myToken, "no_from", "no_to").execute();
        Assertions.assertEquals(NOT_FOUND.getCode(), response.code());
    }

    @Test
    @DisplayName("PUT: Ошибка 404 при публикации несуществующей папки")
    public void testPublishNonExistent() throws IOException {
        String path = "NonExistentPath_" + System.currentTimeMillis();
        Response<Void> response = service.publishResource(myToken, path).execute();

        Assertions.assertEquals(NOT_FOUND.getCode(), response.code());
    }
}