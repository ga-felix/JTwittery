package org.monitordigital.jtwittery.service;

import org.junit.jupiter.api.Test;
import org.monitordigital.jtwittery.exception.UserBadRequestException;
import org.monitordigital.jtwittery.exception.UserNotFoundException;
import org.monitordigital.jtwittery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldThrowUserNotFoundException() {
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUser("999"));
    }

    @Test
    public void shouldThrowUserBadRequestException() {
        assertThrows(
                UserBadRequestException.class,
                () -> userService.getUser("3.0"));
    }

}