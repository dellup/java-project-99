package hexlet.code.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import hexlet.code.demo.repository.UserRepository;
import hexlet.code.demo.model.User;

@Component
@AllArgsConstructor
public class UserUtils {
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        var email = authentication.getName();
        return userRepository.findByEmail(email).get();
    }


    public User getTestUser() {
        return userRepository.findByEmail("hexlet@example.com")
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }
}
