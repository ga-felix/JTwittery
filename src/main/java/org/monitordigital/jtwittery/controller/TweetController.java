package org.monitordigital.jtwittery.controller;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.Tweet;
import org.monitordigital.jtwittery.service.QueryBuilder;
import org.monitordigital.jtwittery.service.exporter.TweetExporter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

import static org.monitordigital.jtwittery.service.mapper.namesToUsers.toUsers;

@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private QueryBuilder queryBuilder;
    private TweetExporter tweetExporter;

    @GetMapping(produces = "text/csv")
    public ResponseEntity<?> getTweetsDataset(@Nullable @RequestParam List<String> authors,
                                              @Nullable @RequestParam OffsetDateTime since,
                                              @Nullable @RequestParam OffsetDateTime until,
                                              @Nullable @RequestParam List<String> types) {
        List<Tweet> tweets = queryBuilder.getTweets(toUsers(authors), since, until, types);
        var payload = tweetExporter.export(tweets);
        return ResponseEntity.ok()
                .headers((HttpHeaders) payload.get("header"))
                .body(payload.get("dataset"));
    }

}
