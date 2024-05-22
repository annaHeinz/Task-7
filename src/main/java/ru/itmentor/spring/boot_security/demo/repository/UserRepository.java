package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    List<User> findBySalaryGreaterThan(int salary);

    void deleteById(long id);

    void delete(User user);}
