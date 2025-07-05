package hexlet.code.demo.service;

import hexlet.code.demo.dto.UserCreateDTO;
import hexlet.code.demo.dto.UserDTO;
import hexlet.code.demo.dto.UserUpdateDTO;
import hexlet.code.demo.exception.ResourceNotFoundException;
import hexlet.code.demo.mapper.UserMapper;
import hexlet.code.demo.model.User;
import hexlet.code.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> indexUsers() {
        var users = userRepository.findAll();
        var userDTOs = users.stream()
                .map(userMapper::map)
                .toList();
        return userDTOs;
    }

    public UserDTO createUser(UserCreateDTO data) {
        var user = new User();
        user.setEmail(data.getEmail());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        var hashedPassword = passwordEncoder.encode(data.getPassword());
        user.setPasswordDigest(hashedPassword);
        userRepository.save(user);
        return userMapper.map(user);
    }

    public UserDTO getUserById(Long id) {
        var maybeUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id" + id + " not found"));
        return userMapper.map(maybeUser);
    }

    public UserDTO updateUser(UserUpdateDTO data, Long id) {
        var maybeUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id" + id + " not found"));
        userMapper.update(data, maybeUser);
        userRepository.save(maybeUser);
        return userMapper.map(maybeUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

}
