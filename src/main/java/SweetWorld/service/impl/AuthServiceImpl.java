package SweetWorld.service.impl;


import SweetWorld.config.model.User;
import SweetWorld.config.model.exceptions.InvalidArgumentsException;
import SweetWorld.config.model.exceptions.InvalidUserCredentialsException;
import SweetWorld.repository.UserRepository;
import SweetWorld.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}