package org.monitordigital.jtwittery.service;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.Tweet;
import org.monitordigital.jtwittery.model.TweetType;
import org.monitordigital.jtwittery.model.User;
import org.monitordigital.jtwittery.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

import static org.monitordigital.jtwittery.repository.TweetSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@AllArgsConstructor
@Service
public class QueryBuilder {

    private TweetRepository tweetRepository;

    public List<Tweet> getTweets(List<User> authors,
                                 OffsetDateTime since,
                                 OffsetDateTime until,
                                 List<String> types) {
        return tweetRepository.findAll(where(withAuthors(authors))
                .and(withTypes(types))
                .and(withSince(since))
                .and(withUntil(until)));
    }
}
