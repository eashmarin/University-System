package ru.nsu.fit.universitysystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(
        classes = UniversitySystemApplication.class,
        initializers = TestContainerDbContextInitializer.class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testValidCredentialsAuthentication() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "password1"
                        }
                        """)
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testInvalidCredentialsAuthentication() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "abcdefgh"
                        }
                        """)
        ).andExpect(status().isUnauthorized()).andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testAuthenticatedAccess() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "password1"
                        }
                        """)
        ).andExpect(status().isOk()).andReturn();

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
