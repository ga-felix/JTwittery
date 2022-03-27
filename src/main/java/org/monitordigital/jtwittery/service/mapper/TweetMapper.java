package org.monitordigital.jtwittery.service.mapper;

import org.monitordigital.jtwittery.model.user.User;

import java.util.List;

public interface TweetMapper {

    List<User> toUsers(List<String> ids) throws Exception;

}
