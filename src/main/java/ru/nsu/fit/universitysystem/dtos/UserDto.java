package ru.nsu.fit.universitysystem.dtos;

public class UserDto {
    private Long id;
    private String name;
    private String login;
    private String token;

    public UserDto(Long id, String name, String login, String token) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
