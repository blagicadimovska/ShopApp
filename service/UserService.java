package SweetWorld.service;


import SweetWorld.config.model.User;
import SweetWorld.config.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
