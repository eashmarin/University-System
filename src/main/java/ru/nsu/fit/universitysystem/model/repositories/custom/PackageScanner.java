package ru.nsu.fit.universitysystem.model.repositories.custom;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class PackageScanner {
    private final List<? extends Class<?>> entityClasses = scanEntityClasses();

    private List<? extends Class<?>> scanEntityClasses() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents("ru.nsu.fit.universitysystem");
        List<? extends Class<?>> classes = candidateComponents.stream()
                .map(beanDefinition -> {
                    try {
                        return (Class<?>) Class.forName(beanDefinition.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        return classes;
    }

    public boolean entityContainsField(String entityName, String fieldName) {
        Class<?> requiredEntityClass = entityClasses.stream()
                .filter(entityClass -> entityClass.getSimpleName().equals(entityName))
                .findAny()
                .orElseThrow();
        return Arrays.stream(requiredEntityClass.getDeclaredFields())
                .anyMatch(field -> field.getName().equals(fieldName));
    }

    public Field[] getEntityFields(String entityName) {
        return entityClasses.stream()
                .filter(entityClass -> entityClass.getSimpleName().equals(entityName))
                .map(Class::getDeclaredFields)
                .findFirst()
                .orElseThrow();
    }

    public Class<?> getEntityClass(String entityName) {
        return entityClasses.stream()
                .filter(entityClass -> entityClass.getSimpleName().equals(entityName))
                .findAny()
                .orElseThrow();
    }
}
