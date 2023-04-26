package ru.nsu.fit.universitysystem.model.repositories.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryProxyConfig {

    @Bean
    public RepositoryProxy repositoryProxy() {
        return new RepositoryProxy();
    }

    @Bean(name = "repositoryProxyBeanFactory")
    public RepositoryProxyBeanFactory repositoryProxyBeanFactory() {
        return new RepositoryProxyBeanFactory();
    }
}
