package org.monitordigital.jtwittery.service.twitter.downloader;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetSearchResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.twitter.downloader.mounter.FullArchiveQuery;
import org.monitordigital.jtwittery.service.twitter.token.BearerToken;
import org.monitordigital.jtwittery.service.twitter.token.BearerTokenProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwitterDownloaderImp implements TwitterDownloader {

    @Getter
    @NonNull
    private BearerTokenProvider bearerTokenProvider;
    private BearerToken bearerToken;

    public void downloadTweets(List<User> users) {

    }

    public void downloadTweets(String query,
                               OffsetDateTime since,
                               OffsetDateTime until,
                               Integer maxResults) throws ApiException {
        FullArchiveQuery fullArchiveQuery = FullArchiveQuery
                .builder()
                .since(since)
                .until(until)
                .maxResults(maxResults)
                .build();
        TweetSearchResponse response = fullArchiveQuery.call(query,api(), null, null);
        log.info(response.toString());
        while (doesResponseAndTokenExist(response)) {
            response = fullArchiveQuery.call(query,
                    api(),
                    response.getMeta().getNextToken(),
                    response.getMeta().getNextToken());
            log.info(response.toString());
        }
    }

    private boolean doesResponseAndTokenExist(TweetSearchResponse response) {
        return (response != null && requireNonNull(response.getMeta()).getNextToken() != null);
    }

    public void downloadTweets(List<User> users, String query) {

    }

    public void downloadRetweetsOf(List<User> users) {

    }

    private TwitterApi api() {
        bearerToken = bearerTokenProvider.getValidToken();
        var api = new TwitterApi();
        bearerToken.setFirstCall(Instant.now());
        api.setTwitterCredentials(new TwitterCredentialsBearer(bearerToken.getToken()));
        return api;
    }

    @Override
    public BearerToken getToken() {
        return bearerToken;
    }

}
