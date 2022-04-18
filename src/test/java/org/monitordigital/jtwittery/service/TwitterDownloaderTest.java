package org.monitordigital.jtwittery.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.monitordigital.jtwittery.service.twitter.downloader.TwitterDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TwitterDownloaderTest {

    @Autowired
    private TwitterDownloader twitterDownloader;

    @Test
    @SneakyThrows
    public void shouldDownloadTweetFromTwitterApi() {
        String query = "Bolsonaro";
        twitterDownloader.downloadTweets(query, null, null, 15);
    }

}
