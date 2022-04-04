package org.monitordigital.jtwittery.service.twitter.downloader;

import org.monitordigital.jtwittery.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TwitterDownloader {

    void downloadTweets(List<User> users);
    void downloadTweets(String query);
    void downloadTweets(List<User> users, String query);
    void downloadRetweetsOf(List<User> users);

}