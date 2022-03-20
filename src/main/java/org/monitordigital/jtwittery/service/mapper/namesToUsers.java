package org.monitordigital.jtwittery.service.mapper;

import org.monitordigital.jtwittery.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class namesToUsers {

    public static List<User> toUsers(List<String> names) {
        return names.stream().map(name -> new User(name)).collect(Collectors.toList());
    }

}
