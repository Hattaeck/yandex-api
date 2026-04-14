package ru.yandex.disk;

import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import static ru.yandex.disk.constants.HttpStatusCode.*;

public class DiskPositiveTests extends BaseTest {

    @Test
    @DisplayName("GET: Инфо о диске")
    public void testGetDiskInfo() throws IOException {
        Response<Void> response = service.getDiskInfo(myToken).execute();
        Assertions.assertEquals(OK.getCode(), response.code());
    }

    @Test
    @DisplayName("PUT: Создание папки")
    public void testCreateFolder() throws IOException {
        String path = "Folder_" + System.currentTimeMillis();
        Response<Void> response = service.createFolder(myToken, path).execute();
        Assertions.assertEquals(CREATED.getCode(), response.code());
    }

    @Test
    @DisplayName("DELETE: Полная очистка корзины")
    public void testClearTrash() throws IOException {
        Response<Void> response = service.clearTrash(myToken).execute();
        Assertions.assertEquals(ACCEPTED.getCode(), response.code());
    }

    @Test
    @DisplayName("GET: Получение инфо о ресурсе")
    public void testGetResourceInfo() throws IOException {
        String path = "InfoFolder_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> response = service.getResourceInfo(myToken, path).execute();
        Assertions.assertEquals(OK.getCode(), response.code());

        service.deleteResource(myToken, path).execute();
    }

    @Test
    @DisplayName("POST: Копирование ресурса")
    public void testCopyResource() throws IOException {
        String fromPath = "FolderFrom_" + System.currentTimeMillis();
        String toPath = "FolderTo_" + System.currentTimeMillis();

        service.createFolder(myToken, fromPath).execute();

        Response<Void> response = service.copyResource(myToken, fromPath, toPath).execute();

        int code = response.code();
        Assertions.assertTrue(code == CREATED.getCode() || code == ACCEPTED.getCode());
    }

    @Test
    @DisplayName("POST: Перемещение ресурса")
    public void testMoveResource() throws IOException {
        String fromPath = "ForMove_" + System.currentTimeMillis();
        String toPath = "Moved_" + System.currentTimeMillis();

        service.createFolder(myToken, fromPath).execute();

        Response<Void> response = service.moveResource(myToken, fromPath, toPath).execute();
        Assertions.assertEquals(CREATED.getCode(), response.code());
    }

    @Test
    @DisplayName("PUT: Публикация и отмена публикации ресурса")
    public void testPublishAndUnpublish() throws IOException {
        String path = "PublishFolder_" + System.currentTimeMillis();
        service.createFolder(myToken, path).execute();

        Response<Void> publishRes = service.publishResource(myToken, path).execute();
        Assertions.assertEquals(OK.getCode(), publishRes.code());

        Response<Void> unpublishRes = service.unpublishResource(myToken, path).execute();
        Assertions.assertEquals(OK.getCode(), unpublishRes.code());
    }

    @Test
    @DisplayName("GET: Просмотр ресурсов в корзине")
    public void testGetTrashResources() throws IOException {
        Response<Void> response = service.getTrashResources(myToken).execute();

        Assertions.assertEquals(OK.getCode(), response.code());
        Assertions.assertNotNull(response.raw().body());
    }
}