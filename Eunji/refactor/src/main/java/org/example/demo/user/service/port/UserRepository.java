package org.example.demo.user.service.port;

import java.util.Optional;
import org.example.demo.user.domain.User;
import org.example.demo.user.domain.UserStatus;

public interface UserRepository {

    User getById(long id);

    Optional<User> findById(long id);

    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);

    User save(User user);
}