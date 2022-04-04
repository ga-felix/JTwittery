package org.monitordigital.jtwittery.service.tweet;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.tweet.Tweet;
import org.monitordigital.jtwittery.model.tweet.TweetType;
import org.monitordigital.jtwittery.service.exporter.TweetExporter;
import org.monitordigital.jtwittery.service.query.TweetQuery;
import org.monitordigital.jtwittery.service.tweet.TweetService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TweetServiceImp implements TweetService {

    private TweetQuery tweetQuery;
    private TweetExporter tweetExporter;

    public Map<String, Object> getTweetDataset(List<String> authors,
                                        OffsetDateTime since,
                                        OffsetDateTime until,
                                        List<TweetType> types) {
        List<Tweet> tweets = tweetQuery.getTweets(authors, since, until, types);
        return tweetExporter.export(tweets);
    }

}
