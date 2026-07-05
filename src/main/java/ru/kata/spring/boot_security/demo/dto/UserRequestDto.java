package ru.kata.spring.boot_security.demo.dto;

import java.util.Set;

public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String email;
    private Set<Long> roleIds;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Long> getRoleIds() { return roleIds; }
    public void setRoleIds(Set<Long> roleIds) { this.roleIds = roleIds; }
}