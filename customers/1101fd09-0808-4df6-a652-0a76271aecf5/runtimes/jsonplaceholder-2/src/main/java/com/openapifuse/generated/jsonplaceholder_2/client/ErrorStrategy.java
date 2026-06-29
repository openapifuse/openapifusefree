package com.openapifuse.generated.jsonplaceholder_2.client;

/**
 * Strategy for handling a non-bound-success upstream response (CAP-ADR-1.17 §INV-CAP-1.17-d).
 * Only {@link #PASSTHROUGH} is implemented (relay the upstream response verbatim).
 */
public enum ErrorStrategy {

    /** Relay the upstream status + body + content-type verbatim (the only implemented strategy). */
    PASSTHROUGH,

    /** Reserved — normalize upstream errors to a canonical envelope. Not implemented (Composite, CAP-ADR-1.4). */
    NORMALIZE,

    /** Reserved — composite orchestration (retry / fallback / refresh). Not implemented (Composite, CAP-ADR-1.4). */
    COMPOSITE
}
