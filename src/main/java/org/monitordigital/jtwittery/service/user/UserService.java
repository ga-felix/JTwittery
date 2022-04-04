package org.monitordigital.jtwittery.service.user;

import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.form.CreateUserForm;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(CreateUserForm createUserForm);
    User getUser(String id);

}
