package org.monitordigital.jtwittery.controller;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.service.TweetService;
import org.monitordigital.jtwittery.model.TweetType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @GetMapping(produces = "text/csv")
    public ResponseEntity<?> getTweetsDataset(@Nullable @RequestParam List<String> authors,
                                              @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime since,
                                              @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime until,
                                              @Nullable @RequestParam List<TweetType> types) {
        var payload = tweetService.getTweetDataset(authors, since, until, types);
        return ResponseEntity.ok()
                .headers((HttpHeaders) payload.get("header"))
                .body(payload.get("dataset"));
    }

}
