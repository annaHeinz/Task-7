package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        String currentPassword = userRepository.getById(user.getId()).getPassword();
        user.setPassword(currentPassword);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + username));
    }

//    public List<User> getUsersByDepartment(String department) {
//        return userRepository.findByDepartment(department);
//    }

    public List<User> getUsersWithSalaryGreaterThan(int salary) {
        return userRepository.findBySalaryGreaterThan(salary);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void createAdminUser() {
        // Создание и сохранение роли
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setName("ROLE_USER");
        roleRepository.save(role2);

        // Создание пользователя и установка роли
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User user = new User();
        user.setName("Daniil");
        user.setSurname("Abobin");
        user.setDepartment("Daun");
        user.setSalary(210000);
        user.setUsername("Anna");
        user.setPassword("$2a$12$ydD1iLd9iFHIU3jfPiGfDeCZ65EYEMr.9gYZCOGKvuaG.Foi/yp0q");
        user.setRoles(roles);

        // Сохранение пользователя в базе данных
        userRepository.save(user);
    }
}
