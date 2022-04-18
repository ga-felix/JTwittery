package org.monitordigital.jtwittery.service.twitter.downloader;

import com.twitter.clientlib.ApiException;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.twitter.token.BearerToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TwitterDownloader {

    void downloadTweets(List<User> users) throws ApiException;
    void downloadTweets(String query) throws ApiException;
    void downloadTweets(List<User> users, String query) throws ApiException;
    void downloadRetweetsOf(List<User> users) throws ApiException;
    BearerToken getToken();

}