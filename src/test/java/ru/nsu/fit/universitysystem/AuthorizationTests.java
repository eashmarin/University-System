package ru.nsu.fit.universitysystem;

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
import ru.nsu.fit.universitysystem.model.utils.TokenUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(
        classes = UniversitySystemApplication.class,
        initializers = TestContainerDbContextInitializer.class
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AuthorizationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"classpath:insert-user.sql"})
    public void testUserWithUserRoleAccessToPostMethods() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "login": "user1",
                                "password": "password1"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(cookie().exists(TokenUtil.TOKEN_COOKIE_NAME))
                .andReturn();

        Cookie tokenCookie = mvcResult.getResponse().getCookie(TokenUtil.TOKEN_COOKIE_NAME);

        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "login": "user2",
                                "password": "password2",
                                "name": "Will Smith",
                                "gender": "MALE",
                                "birthDate": "2000-04-23T18:25:43.511Z",
                                "childNum": 1
                                }
                                """)
                        .cookie(tokenCookie))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @Sql(scripts = {"classpath:insert-user-with-role-admin.sql"})
    public void testUserWithAdminRoleAccessToPostMethods() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "login": "admin1",
                                "password": "password1"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(cookie().exists(TokenUtil.TOKEN_COOKIE_NAME))
                .andReturn();

        Cookie tokenCookie = mvcResult.getResponse().getCookie(TokenUtil.TOKEN_COOKIE_NAME);

        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "login": "testuser",
                                "password": "password2",
                                "name": "Will Smith",
                                "gender": "MALE",
                                "birthDate": "2000-04-23T18:25:43.511Z",
                                "childNum": 1
                                }
                                """)
                        .cookie(tokenCookie))
                .andExpect(status().isOk())
                .andReturn();
    }
}
