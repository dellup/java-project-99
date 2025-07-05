package hexlet.code.demo.component;


import hexlet.code.demo.dto.UserCreateDTO;
import hexlet.code.demo.exception.ResourceNotFoundException;
import hexlet.code.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import hexlet.code.demo.repository.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CustomUserDetailsService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        var userData = new UserCreateDTO();
        userData.setEmail(email);
        userData.setPassword("qwerty");
        userService.createUser(userData);

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User is empty"));

    }
}
