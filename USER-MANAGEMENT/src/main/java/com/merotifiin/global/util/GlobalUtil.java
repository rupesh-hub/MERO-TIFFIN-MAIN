package com.merotifiin.global.util;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class GlobalUtil {

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static LocalDateTime currentDate(){
        return LocalDateTime.now();
    }

    public static <T> GlobalResponse<T> globalResponse(final String message,
                                                       final HttpStatus httpStatus,
                                                       final T data) {
        return GlobalResponse
                .<T>builder()
                .message(message)
                .status(httpStatus)
                .statusCode(httpStatus.value())
                .timeStamp(LocalDateTime.now())
                .data(data)
                .build();

    }

}
