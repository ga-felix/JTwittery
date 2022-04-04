package org.monitordigital.jtwittery.service.twitter.downloader;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.twitter.api.ApiProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TwitterDownloaderImp implements TwitterDownloader {

    private ApiProvider apiProvider;

    public void downloadTweets(List<User> users) {

    }

    public void downloadTweets(String query) {

    }

    public void downloadTweets(List<User> users, String query) {

    }

    public void downloadRetweetsOf(List<User> users) {

    }

}
