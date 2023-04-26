package ru.nsu.fit.universitysystem.model.repositories.custom;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Configuration
public class RepositoryProxyBeansRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {

    private ClassPathScanner classpathScanner;
    private ClassLoader classLoader;

    public RepositoryProxyBeansRegistrar() {
        classpathScanner = new ClassPathScanner(false);
        classpathScanner.addIncludeFilter(new AnnotationTypeFilter(CustomRepository.class));
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String basePackage = "ru.nsu.fit.universitysystem";
        createRepositoryProxies(basePackage, registry);
    }

    private void createRepositoryProxies(String basePackage, BeanDefinitionRegistry registry) {
        try {
            for (Class<?> repositoryClass : getRepoClasses()) {
                String beanName = ClassUtils.getShortNameAsProperty(repositoryClass);
                System.out.println("beanName = " + beanName);
                GenericBeanDefinition proxyBeanDefinition = new GenericBeanDefinition();
                proxyBeanDefinition.setBeanClass(repositoryClass);

                ConstructorArgumentValues args = new ConstructorArgumentValues();

                args.addGenericArgumentValue(classLoader);
                args.addGenericArgumentValue(repositoryClass);
                proxyBeanDefinition.setConstructorArgumentValues(args);

                proxyBeanDefinition.setFactoryBeanName("repositoryProxyBeanFactory");
                proxyBeanDefinition.setFactoryMethodName("createRepositoryProxyBean");

                registry.registerBeanDefinition(beanName, proxyBeanDefinition);

            }
        } catch (Exception e) {
            System.out.println("Exception while creating proxy");
            e.printStackTrace();
        }

    }

    private List<Class<?>> getRepoClasses() throws ClassNotFoundException, IOException {
        List<Class<?>> customRepositories = new ArrayList<>();

        String basePackage = getClass().getPackage().getName();

        ClassLoader classLoader = getClass().getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(basePackage.replace('.', '/'));
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        for (File directory : dirs) {
            File[] files = directory.listFiles();
            if (files == null) {
                continue;
            }

            for (File file : files) {
                String fileName = file.getName();
                if (fileName.endsWith(".class")) {
                    String className = basePackage + '.' + fileName.substring(0, fileName.length() - 6);
                    Class<?> customRepositoryClass = Class.forName(className);

                    if (customRepositoryClass.isAnnotationPresent(CustomRepository.class)) {
                        customRepositories.add(customRepositoryClass);
                    }
                }
            }
        }

        return customRepositories;
    }
}
