package com.a0720i1.project_be.common;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private String httpCodeMessage;

    public ExceptionResponse(Date timestamp, String message, String details, String httpCodeMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.httpCodeMessage = httpCodeMessage;
    }
}
