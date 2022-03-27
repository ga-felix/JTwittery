package org.monitordigital.jtwittery.service.query;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.monitordigital.jtwittery.model.Tweet;
import org.monitordigital.jtwittery.model.TweetType;
import org.monitordigital.jtwittery.model.User;
import org.monitordigital.jtwittery.repository.TweetRepository;
import org.monitordigital.jtwittery.service.mapper.TweetMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static org.monitordigital.jtwittery.repository.TweetSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class TweetQueryImp implements TweetQuery {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    @SneakyThrows
    public List<Tweet> getTweets(List<String> authors,
                                 OffsetDateTime since,
                                 OffsetDateTime until,
                                 List<TweetType> types) {
        List<User> converted = tweetMapper.toUsers(authors);
        return tweetRepository.findAll(where(withAuthors(converted))
                .and(withTypes(types))
                .and(withSince(since))
                .and(withUntil(until)));
    }

}