package ru.kata.spring.boot_security.demo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (userService.isUsernameExists(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getRoleIds()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (userService.isUsernameExistsForOtherUser(id, userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userService.updateUser(
                id,
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getRoleIds()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    static class UserDto {
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
}