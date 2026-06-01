package com.openapifuse.generated.mapping1001.client;

public class TargetApiException extends RuntimeException {

    private final Integer upstreamStatus;

    public TargetApiException(String message, Integer upstreamStatus) {
        super(message);
        this.upstreamStatus = upstreamStatus;
    }

    public TargetApiException(String message, Integer upstreamStatus, Throwable cause) {
        super(message, cause);
        this.upstreamStatus = upstreamStatus;
    }

    public Integer getUpstreamStatus() {
        return upstreamStatus;
    }
}
