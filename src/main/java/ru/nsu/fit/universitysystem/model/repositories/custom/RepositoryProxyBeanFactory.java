package ru.nsu.fit.universitysystem.model.repositories.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class RepositoryProxyBeanFactory {
    @Autowired
    private RepositoryProxy repositoryProxy;

    public <R> R createRepositoryProxyBean(ClassLoader classLoader, Class<R> clazz) {
        return (R) Proxy.newProxyInstance(classLoader, new Class[] {clazz}, repositoryProxy);
    }
}
