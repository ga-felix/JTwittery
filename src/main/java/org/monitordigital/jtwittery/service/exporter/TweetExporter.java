package org.monitordigital.jtwittery.service.exporter;

import org.monitordigital.jtwittery.model.tweet.Tweet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TweetExporter {

    Map<String, Object> export(List<Tweet> tweets);

}
