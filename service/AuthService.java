package SweetWorld.service;

import SweetWorld.config.model.User;

public interface AuthService {
    User login(String username, String password);
}

