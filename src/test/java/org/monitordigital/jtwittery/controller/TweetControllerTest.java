package org.monitordigital.jtwittery.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.monitordigital.jtwittery.model.*;
import org.monitordigital.jtwittery.repository.TweetRepository;
import org.monitordigital.jtwittery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RequiredArgsConstructor
public class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @Transactional
    public void insertTestData() {
        var parentAuthor = new User(1L, "gabrielfelix");
        var retweetAuthor = new User(2L, "pablo_ortellado");
        var now = OffsetDateTime.now();
        var parent = new Tweet(1L,
                "This is a standard tweet.",
                now,
                parentAuthor,
                Short.valueOf("10"),
                Short.valueOf("2"),
                Short.valueOf("1"),
                Short.valueOf("15"),
                TweetType.STANDARD,
                null);
        var tweets = new ReferencedTweet(parent, ReferenceType.RETWEETED);
        var retweet = new Tweet(2L,
                "RT @gabrielfelix: This is a standard tweet.",
                now,
                parentAuthor,
                Short.valueOf("3"),
                Short.valueOf("0"),
                Short.valueOf("0"),
                Short.valueOf("1"),
                TweetType.RETWEET,
                Arrays.asList(tweets));
        userRepository.saveAll(Arrays.asList(parentAuthor, retweetAuthor));
        tweetRepository.saveAll(Arrays.asList(parent, retweet));
    }

    @Test
    public void shouldListTweetsAccordingToAuthors() throws Exception {
        var getTweetUri = URI.create("/tweet");
        mockMvc.perform(MockMvcRequestBuilders
                .get(getTweetUri)
                .param("authors", "1," + "2"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }
}
