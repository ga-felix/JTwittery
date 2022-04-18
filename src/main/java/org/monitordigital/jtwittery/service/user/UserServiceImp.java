package org.monitordigital.jtwittery.service.user;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.monitordigital.jtwittery.exception.UserBadRequestException;
import org.monitordigital.jtwittery.exception.UserNotFoundException;
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

    public User getUser(String id) {
        User user;
        try {
            user = userRepository.findById(Long.valueOf(id)).orElseThrow(UserNotFoundException::new);
        } catch(NumberFormatException exception) {
            user = userRepository.findByName(id).orElseThrow(UserBadRequestException::new);
        }
        return user;
    }

}