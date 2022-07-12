package mutlu.movies.service;

import mutlu.movies.dto.ChangePasswordDto;
import mutlu.movies.dto.ChangeUsernameDto;
import mutlu.movies.dto.LoginCredentialsDto;
import mutlu.movies.dto.PaymentDetailsDto;
import mutlu.movies.entity.User;
import mutlu.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Optional<User> getByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    public User update(User request, Long movieId) {
        return request;
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public User login(LoginCredentialsDto credentialsDto) {
        return login(credentialsDto.getUsername(), credentialsDto.getPassword());
    }

    //Tries to log user in, if successful returns that User entity.
    private User login(String username, String passwordHash) {
        var userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            if (passwordEncoder.matches(passwordHash, user.getPasswordHash())) {
                return user;
            } else {
                throw new RuntimeException("Username or password is wrong.");
            }
        } else {
            throw new RuntimeException("Username or password is wrong.");
        }
    }

    public User changeUsername(ChangeUsernameDto usernameDto) {
        //Authenticate the user.
        var user = login(usernameDto.getUsername(), usernameDto.getPassword());
        user.setUsername(usernameDto.getNewUsername());
        userRepository.flush();
        return user;
    }

    public User changePassword(ChangePasswordDto changePasswordDto) {
        //Authenticate the user.
        var user = login(changePasswordDto.getUsername(), changePasswordDto.getPassword());
        if (changePasswordDto.getFirstNewPassword().equals(changePasswordDto.getSecondNewPassword())) {
            user.setPasswordHash(passwordEncoder.encode(changePasswordDto.getFirstNewPassword()));
            userRepository.flush();
        } else {
            throw new RuntimeException("The passwords does not match.");
        }
        return user;
    }

    public User makePayment(PaymentDetailsDto paymentDetailsDto) {
        var userOpt = userRepository.findById(paymentDetailsDto.getUserId());
        if (userOpt.isPresent()) {
            var user = userOpt.get();
            //send rquest to payment service and wait for verification.
            user.setPremiumUntil(LocalDateTime.now().plusMonths(paymentDetailsDto.getPaymentType().ordinal()));
            userRepository.flush();
            return user;
        } else {
            throw new RuntimeException("Username or password is wrong.");
        }
    }
}
