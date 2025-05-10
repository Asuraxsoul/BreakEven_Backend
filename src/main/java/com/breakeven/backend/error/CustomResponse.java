package com.breakeven.backend.error;

public class CustomResponse {
    private final ResponseStatus status;
    private final String message;
    private final String details;

    public enum ResponseStatus {
        Success, Fail;
    }

    public CustomResponse(ResponseStatus status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
