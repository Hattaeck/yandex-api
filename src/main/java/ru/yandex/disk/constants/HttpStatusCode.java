package ru.yandex.disk.constants;

public enum HttpStatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    CONFLICT(409);

    private final int code;

    HttpStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}