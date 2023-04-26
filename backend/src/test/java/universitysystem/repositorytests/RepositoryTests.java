package universitysystem.repositorytests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import universitysystem.TestContainerDbContextInitializer;
import ru.nsu.fit.universitysystem.UniversitySystemApplication;
import ru.nsu.fit.universitysystem.model.entities.Person;
import ru.nsu.fit.universitysystem.model.enums.Gender;
import ru.nsu.fit.universitysystem.model.repositories.custom.CustomPersonRepository;

import java.util.List;

@ContextConfiguration(
        classes = UniversitySystemApplication.class,
        initializers = TestContainerDbContextInitializer.class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RepositoryTests {
    private final CustomPersonRepository customPersonRepository;

    @Autowired
    public RepositoryTests(CustomPersonRepository customPersonRepository) {
        this.customPersonRepository = customPersonRepository;
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    void findByFieldTest() {
        Person person = customPersonRepository.findByLogin("user1");

        Assertions.assertEquals("John Smith", person.getName());
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    void findByEnumField() {
        List<Person> persons = customPersonRepository.findByGender(Gender.MALE);

        Assertions.assertEquals(1, persons.size());
        Assertions.assertEquals("John Smith", persons.get(0).getName());
    }
}
