package org.monitordigital.jtwittery.service.query;

import org.monitordigital.jtwittery.model.tweet.Tweet;
import org.monitordigital.jtwittery.model.tweet.TweetType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public interface TweetQuery {

    List<Tweet> getTweets(List<String> authors,
                          OffsetDateTime since,
                          OffsetDateTime until,
                          List<TweetType> types);

}
