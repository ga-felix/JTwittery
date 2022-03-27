package org.monitordigital.jtwittery.service.mapper;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.User;
import org.monitordigital.jtwittery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TweetMapper {

    private UserRepository userRepository;

    public List<User> toUsers(List<String> ids) throws Exception {
        return ids
                .stream()
                .map(id -> userRepository.findById(Long.valueOf(id)).orElseThrow())
                .collect(Collectors.toList());
    }

}
