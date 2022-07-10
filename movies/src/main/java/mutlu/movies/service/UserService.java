package mutlu.movies.service;

import mutlu.movies.entity.User;
import mutlu.movies.repository.UserRepository;
import mutlu.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User request){
        return userRepository.save(request);
    }
    public Optional<User> getByUserId(String  username){
        return userRepository.findByUsername(username);
    }
    public User update(User request, Long movieId){
       return  request;
    }
    public void delete(String username){
        userRepository.deleteById(username);
    }
}
