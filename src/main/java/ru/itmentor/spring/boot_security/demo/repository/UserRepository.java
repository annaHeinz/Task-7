package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

public class UserRepository implements JpaRepository<User, Long>{
    User findByUsername(String username);
}
