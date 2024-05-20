package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers(){
        return  userRepository.findAll();}
    public void saveUser(User user){
        log.info("Saving new {}",user);
        userRepository.save(user);}
    public void removeById(Long id){
        userRepository.deleteById(id);}
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);}

    public void updateUser(User user){
        userRepository.save(user);
    }
}
