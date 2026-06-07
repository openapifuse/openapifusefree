package com.openapifuse.generated.usercustomermapping.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class McpAuditAspect {

    private static final Logger log = LoggerFactory.getLogger(McpAuditAspect.class);
    private static final String MAPPING_VERSION_ID = "multi-mapping";

    @Around("@annotation(org.springframework.ai.tool.annotation.Tool)")
    public Object auditToolCall(ProceedingJoinPoint pjp) throws Throwable {
        String toolName = pjp.getSignature().getName();
        String correlationId = MDC.get("correlationId");
        boolean createdCorrelationId = false;
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
            MDC.put("correlationId", correlationId);
            createdCorrelationId = true;
        }
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long durationMs = System.currentTimeMillis() - start;
            log.info("tool={} mappingVersionId={} durationMs={} correlationId={}",
                    toolName, MAPPING_VERSION_ID, durationMs, correlationId);
            return result;
        } catch (Throwable t) {
            long durationMs = System.currentTimeMillis() - start;
            log.warn("tool={} mappingVersionId={} durationMs={} correlationId={}",
                    toolName, MAPPING_VERSION_ID, durationMs, correlationId);
            throw t;
        } finally {
            if (createdCorrelationId) {
                MDC.remove("correlationId");
            }
        }
    }
}
