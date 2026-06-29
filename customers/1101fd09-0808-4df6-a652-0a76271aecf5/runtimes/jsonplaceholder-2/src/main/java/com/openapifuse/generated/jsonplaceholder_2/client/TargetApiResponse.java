package com.openapifuse.generated.jsonplaceholder_2.client;

import java.util.List;
import java.util.Map;

/**
 * An upstream HTTP response captured verbatim — status code, raw body bytes and ALL upstream response
 * {@code headers}. The "byte invariant" (CAP-ADR-1.17): bytes the upstream produced are relayed unchanged
 * with their original headers (Content-Type, Location, Content-Disposition, custom metadata, …); the app
 * never assumes JSON nor re-serializes a pass-through.
 */
public record TargetApiResponse(int status, byte[] body, Map<String, List<String>> headers) {

    /** Convenience accessor for the upstream {@code Content-Type} (first value), or {@code null}. */
    public String contentType() {
        if (headers == null) {
            return null;
        }
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            if ("content-type".equalsIgnoreCase(e.getKey())
                    && e.getValue() != null && !e.getValue().isEmpty()) {
                return e.getValue().get(0);
            }
        }
        return null;
    }
}
