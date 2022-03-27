package org.monitordigital.jtwittery.service;

import org.monitordigital.jtwittery.model.tweet.TweetType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface TweetService {

    Map<String, Object> getTweetDataset(List<String> authors,
                                        OffsetDateTime since,
                                        OffsetDateTime until,
                                        List<TweetType> types);

}