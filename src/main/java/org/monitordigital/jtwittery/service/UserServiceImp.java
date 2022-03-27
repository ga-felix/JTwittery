package org.monitordigital.jtwittery.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.repository.UserRepository;
import org.monitordigital.jtwittery.service.form.CreateUserForm;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    public User createUser(CreateUserForm createUserForm) {
        User newUser = new User(createUserForm.getId(), createUserForm.getName());
        return userRepository.save(newUser);
    }

    @SneakyThrows
    public User getUser(String id) {
        User user = null;
        try {
            user = userRepository.findById(Long.valueOf(id)).orElseThrow();
        } catch(NumberFormatException exception) {
            user = userRepository.findByName(id).orElseThrow();
        }
        return user;
    }

}