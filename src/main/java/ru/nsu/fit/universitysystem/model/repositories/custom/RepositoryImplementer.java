package ru.nsu.fit.universitysystem.model.repositories.custom;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.nsu.fit.universitysystem.model.enums.Gender;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.*;

@Component
public class RepositoryImplementer {
    private Map<Class<?>, Object> classMap;

    private final MethodHandler methodHandler;

    public RepositoryImplementer(MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
    }

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {
        classMap = new HashMap<>();

        List<Class<?>> repoClasses = getRepoClasses();

        for(Class<?> customRepository: repoClasses) {
            Object implementation = Proxy.newProxyInstance(customRepository.getClassLoader(), new Class[]{customRepository}, methodHandler);

            classMap.put(customRepository, implementation);
        }

        getImplementation(PersonRepository.class).findByGender(Gender.FEMALE);
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

    public <T> T getImplementation(Class<T> interfaceClass) {
        return (T) classMap.get(interfaceClass);
    }
}