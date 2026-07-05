package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.entity.Role;
import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private String email;
    private Set<Role> roles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}