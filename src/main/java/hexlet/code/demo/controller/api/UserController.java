package hexlet.code.demo.controller.api;

import hexlet.code.demo.dto.UserCreateDTO;
import hexlet.code.demo.dto.UserDTO;
import hexlet.code.demo.dto.UserUpdateDTO;
import hexlet.code.demo.mapper.UserMapper;
import hexlet.code.demo.repository.UserRepository;
import hexlet.code.demo.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomUserDetailsService userService;

    @GetMapping("")
    public List<UserDTO> index() {
        var userDTOs = userService.indexUsers();
        return userDTOs;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO data) {
        var userDTO = userService.createUser(data);
        return userDTO;
    }

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable Long id) {
        var user = userService.getUserById(id);
        return user;
    }

    @PutMapping("/{id}")
    public UserDTO update(@Valid @RequestBody UserUpdateDTO data, @PathVariable Long id) {
        return userService.updateUser(data, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
