package ru.nsu.fit.universitysystem.model.repositories.custom;

import org.postgresql.jdbc.PgResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MethodHandler implements InvocationHandler {
    private final PackageScanner packageScanner;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    public MethodHandler(PackageScanner packageScanner) {
        this.packageScanner = packageScanner;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String entityName = getEntityName(method.getDeclaringClass().getSimpleName());

        boolean isCollectionExpected = Collection.class.isAssignableFrom(method.getReturnType());

        String selectedColumn = parseSelectedColumn(methodName).toLowerCase();

        if (packageScanner.entityContainsField(entityName, selectedColumn)) {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + "public." + entityName.toLowerCase() + " WHERE " + selectedColumn + " = '" + args[0].toString() + "'");

            Object requestResult = parseResultSet(resultSet, entityName, isCollectionExpected);

            resultSet.close();
            statement.close();
            connection.close();

            return requestResult;
        }

        return null;
    }

    private Object parseResultSet(ResultSet resultSet, String entityName, boolean isCollectionExpected) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> entityClass = packageScanner.getEntityClass(entityName);
        Field[] fields = packageScanner.getEntityFields(entityName);
        Class<?>[] fieldTypes = new Class<?>[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldTypes[i] = fields[i].getType();
        }

        Object requestResult = isCollectionExpected ? new ArrayList<>() : null;

        Object[] values = new Object[fields.length];
        while (resultSet.next()) {
            for (int i = 0; i < fields.length; i++) {
                int columnIndex = resultSet.findColumn(convertToColumnName(fields[i].getName()));
                values[i] = ((PgResultSet) resultSet).getObject(columnIndex);

                if (fieldTypes[i].isEnum()) {
                    values[i] = extractEnumValue(values[i], fieldTypes[i]);
                }
            }

            Object entity = entityClass.getDeclaredConstructor(fieldTypes).newInstance(values);
            if (isCollectionExpected) {
                ((List) requestResult).add(entity);
            } else {
                requestResult = entity;
            }
        }

        return requestResult;
    }

    private Object extractEnumValue(Object value, Class<?> enumClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method valueOf = enumClass.getMethod("valueOf", String.class);
        return valueOf.invoke(null, (String) value);
    }

    private String convertToColumnName(String fieldName) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        fieldName = fieldName.replaceAll(regex, replacement).toLowerCase();

        return fieldName;
    }

    private String parseSelectedColumn(String methodName) {
        int index = methodName.indexOf("findBy") + 6;
        return methodName.substring(index);
    }

    private String getEntityName(String methodName) {
        return methodName.substring(0, methodName.indexOf("Repository"));
    }
}
