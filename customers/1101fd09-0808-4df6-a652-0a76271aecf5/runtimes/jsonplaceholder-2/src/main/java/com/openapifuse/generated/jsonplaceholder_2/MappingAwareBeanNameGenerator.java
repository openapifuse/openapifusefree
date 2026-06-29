package com.openapifuse.generated.jsonplaceholder_2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.ClassUtils;

import java.util.Set;

/**
 * Namespaces per-mapping Spring beans with their mapping identifier to prevent
 * bean name collisions when multiple mappings share class names (e.g., TargetApiClientConfig).
 *
 * <p>Example: TargetApiClientConfig in packages toolone.client and tooltwo.client
 * become tooloneTargetApiClientConfig and tooltwoTargetApiClientConfig.
 */
public class MappingAwareBeanNameGenerator extends AnnotationBeanNameGenerator {

    // Depth of the base package — identifier segment is at this index in the FQN.
    // E.g. base "com.example.app" has depth 3; identifier is parts[3].
    private static final int BASE_PACKAGE_DEPTH = 4;

    // Base-level sub-packages shared across all mappings — not mapping identifiers.
    private static final Set<String> SHARED_SUBPACKAGES = Set.of(
            "exception", "logging", "client", "config", "model", "mapper", "controller", "service"
    );

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String className = definition.getBeanClassName();
        if (className == null) return super.buildDefaultBeanName(definition);

        String[] parts = className.split("[.]");
        if (parts.length > BASE_PACKAGE_DEPTH) {
            String candidate = parts[BASE_PACKAGE_DEPTH];
            if (!SHARED_SUBPACKAGES.contains(candidate)) {
                String simpleName = ClassUtils.getShortName(className);
                return candidate + Character.toUpperCase(simpleName.charAt(0)) + simpleName.substring(1);
            }
        }
        return super.buildDefaultBeanName(definition);
    }
}
