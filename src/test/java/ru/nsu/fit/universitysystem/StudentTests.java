package ru.nsu.fit.universitysystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.nsu.fit.universitysystem.entities.Faculty;
import ru.nsu.fit.universitysystem.entities.Group;
import ru.nsu.fit.universitysystem.entities.Student;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(
        classes = UniversitySystemApplication.class,
        initializers = TestContainerDbContextInitializer.class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentTests {

    @Test
    public void someTest() {
        String name = "name";
        String password = "password";

        /*Student student = new Student(name, "male", new Date(), 1,
                new Group(new Faculty("FIT"), "21202", 3),
                "bachelor", BigDecimal.ZERO);*/

        //assertEquals("name", student.getName());
    }
}
