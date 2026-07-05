package ru.kata.spring.boot_security.demo.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserRequestDto;
import ru.kata.spring.boot_security.demo.dto.UserResponseDto;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return convertToResponseDto(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto dto) {
        if (userService.isUsernameExists(dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userService.createUser(
                dto.getUsername(),
                dto.getPassword(),
                dto.getName(),
                dto.getAge(),
                dto.getEmail(),
                dto.getRoleIds()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        if (userService.isUsernameExistsForOtherUser(id, dto.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userService.updateUser(
                id,
                dto.getUsername(),
                dto.getPassword(),
                dto.getName(),
                dto.getAge(),
                dto.getEmail(),
                dto.getRoleIds()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

}