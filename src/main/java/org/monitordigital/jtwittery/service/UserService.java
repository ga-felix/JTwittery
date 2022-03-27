package org.monitordigital.jtwittery.service;

import org.monitordigital.jtwittery.model.User;
import org.monitordigital.jtwittery.service.form.CreateUserForm;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(CreateUserForm createUserForm);
    User getUserById(Long id);

}
