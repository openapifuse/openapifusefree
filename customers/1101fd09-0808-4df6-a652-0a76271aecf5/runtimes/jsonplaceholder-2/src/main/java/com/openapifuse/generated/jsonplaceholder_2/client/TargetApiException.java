package com.openapifuse.generated.jsonplaceholder_2.client;

/**
 * Signals a failure calling the upstream target API. Two flavours (CAP-ADR-1.17):
 * <ul>
 *   <li><b>Transport failure</b> — no HTTP response was received (timeout / connection-refused / DNS / TLS).
 *       {@code transportFailure=true}; {@code timeout} distinguishes read/connect timeout (→ 504) from other
 *       transport errors (→ 502). This is the ONLY case the app synthesizes an HTTP status.</li>
 *   <li><b>Pass-through</b> — an upstream HTTP response that must be relayed verbatim ({@code upstreamStatus}
 *       + {@code body} bytes + {@code contentType}). Carried for code paths that surface the failure by
 *       throwing (e.g. the MCP tool); REST controllers normally relay inline without throwing.</li>
 * </ul>
 */
public class TargetApiException extends RuntimeException {

    private final Integer upstreamStatus;
    private final byte[] body;
    private final String contentType;
    private final boolean transportFailure;
    private final boolean timeout;

    public TargetApiException(String message, Integer upstreamStatus, byte[] body,
            String contentType, boolean transportFailure, boolean timeout) {
        super(message);
        this.upstreamStatus = upstreamStatus;
        this.body = body;
        this.contentType = contentType;
        this.transportFailure = transportFailure;
        this.timeout = timeout;
    }

    public TargetApiException(String message, Integer upstreamStatus, byte[] body,
            String contentType, boolean transportFailure, boolean timeout, Throwable cause) {
        super(message, cause);
        this.upstreamStatus = upstreamStatus;
        this.body = body;
        this.contentType = contentType;
        this.transportFailure = transportFailure;
        this.timeout = timeout;
    }

    /** A transport failure with no HTTP response — synthesize 504 (timeout) or 502 (other). */
    public static TargetApiException transport(String message, boolean timeout, Throwable cause) {
        return new TargetApiException(message, null, null, null, true, timeout, cause);
    }

    /** A real upstream response to relay verbatim (status + body bytes + content-type). */
    public static TargetApiException passthrough(String message, int upstreamStatus,
            byte[] body, String contentType) {
        return new TargetApiException(message, upstreamStatus, body, contentType, false, false);
    }

    public Integer getUpstreamStatus() {
        return upstreamStatus;
    }

    public byte[] bodyBytes() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isTransportFailure() {
        return transportFailure;
    }

    public boolean isTimeout() {
        return timeout;
    }
}
