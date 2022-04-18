package org.monitordigital.jtwittery.service.twitter.downloader;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.twitter.token.BearerToken;
import org.monitordigital.jtwittery.service.twitter.token.BearerTokenProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TwitterDownloaderImp implements TwitterDownloader {

    @Getter
    @NonNull
    private BearerTokenProvider bearerTokenProvider;
    private BearerToken bearerToken;

    public void downloadTweets(List<User> users) {

    }

    public void downloadTweets(String query) throws ApiException {
        throw new ApiException(419, "Erro forçado");
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
