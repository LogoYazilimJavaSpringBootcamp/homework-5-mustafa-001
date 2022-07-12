package mutlu.movies.service;

import mutlu.movies.dto.LoginCredentialsDto;
import mutlu.movies.entity.User;
import mutlu.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User request) {
        request.setPasswordHash(passwordEncoder.encode(request.getPasswordHash()));
        return userRepository.save(request);
    }

    public Optional<User> getByUserId(String username) {
        return userRepository.findByUsername(username);
    }

    public User update(User request, Long movieId) {
        return request;
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }

    public User login(LoginCredentialsDto credentialsDto) {
        var userOpt = userRepository.findById(credentialsDto.getUsername());
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            if (passwordEncoder.matches(credentialsDto.getPassword(), user.getPasswordHash())) {
                return user;
            } else {
                throw new RuntimeException("Username or password is wrong.");
            }
        } else {
            throw new RuntimeException("Username or password is wrong.");
        }
    }
}
