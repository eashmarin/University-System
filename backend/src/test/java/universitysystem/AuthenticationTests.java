package universitysystem;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.nsu.fit.universitysystem.UniversitySystemApplication;
import ru.nsu.fit.universitysystem.model.utils.TokenUtil;

@ContextConfiguration(
        classes = UniversitySystemApplication.class,
        initializers = TestContainerDbContextInitializer.class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testValidCredentialsAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "password1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testInvalidCredentialsAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "abcdefgh"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testAuthenticatedAccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "login": "user1",
                        "password": "password1"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.cookie().exists(TokenUtil.TOKEN_COOKIE_NAME))
                .andReturn();

        Cookie tokenCookie = mvcResult.getResponse().getCookie(TokenUtil.TOKEN_COOKIE_NAME);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/persons")
                        .cookie(tokenCookie))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
