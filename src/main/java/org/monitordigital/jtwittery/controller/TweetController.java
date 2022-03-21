package org.monitordigital.jtwittery.controller;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.Tweet;
import org.monitordigital.jtwittery.model.TweetType;
import org.monitordigital.jtwittery.service.QueryBuilder;
import org.monitordigital.jtwittery.service.exporter.TweetExporter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

import static org.monitordigital.jtwittery.service.mapper.mapStringToUsers.toUsers;


@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private QueryBuilder queryBuilder;
    private TweetExporter tweetExporter;

    @GetMapping(produces = "text/csv")
    public ResponseEntity<?> getTweetsDataset(@Nullable @RequestParam List<String> authors,
                                              @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime since,
                                              @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime until,
                                              @Nullable @RequestParam List<TweetType> types) {
        List<Tweet> tweets = queryBuilder.getTweets(toUsers(authors), since, until, types);
        var payload = tweetExporter.export(tweets);
        return ResponseEntity.ok()
                .headers((HttpHeaders) payload.get("header"))
                .body(payload.get("dataset"));
    }

}
