package universitysystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.nsu.fit.universitysystem.UniversitySystemApplication;

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
