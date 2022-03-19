package org.monitordigital.jtwittery.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.monitordigital.jtwittery.model.Tweet;
import org.monitordigital.jtwittery.model.TweetType;
import org.monitordigital.jtwittery.model.User;
import org.monitordigital.jtwittery.repository.TweetRepository;
import org.monitordigital.jtwittery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
    public void insertTestData() {
        var parentAuthor = new User(Long.valueOf(1), "@gabrielfelix");
        var retweetAuthor = new User(Long.valueOf(2), "@pablo_ortellado");
        var now = OffsetDateTime.now();
        var tweets = Arrays.asList(
                new Tweet(Long.valueOf(2), "RT @gabrielfelix: This is a standard tweet.", now, retweetAuthor, Short.valueOf("3"), Short.valueOf("0"), Short.valueOf("0"), Short.valueOf("1"), TweetType.RETWEET, null)
        );
        var parentTweet = new Tweet(Long.valueOf(1), "This is a standard tweet.", now, parentAuthor, Short.valueOf("10"), Short.valueOf("2"), Short.valueOf("1"), Short.valueOf("15"), TweetType.STANDARD, tweets);
        userRepository.saveAll(Arrays.asList(parentAuthor, retweetAuthor));
        tweetRepository.save(parentTweet);
    }

    @AfterEach
    public void deleteTestData() {
        var ids = Arrays.asList(Long.valueOf(1), Long.valueOf(2));
        tweetRepository.deleteAllById(ids);
        userRepository.deleteAllById(ids);
    }

    /*
    Casos de teste:
    TODO 1 - Inserção/atualização de tweets
    TODO 2 - Deleção de tweets
    TODO 3 - Listagem de tweets
     */

    @Test
    public void shouldCreateTweetAndReturnCreatedStatus() throws Exception {
        var tweetForm = "{\"id\": \"5\", \"text\": \"This is a standard tweet.\", \"createdAt\": \"t2022-03-18T07:37:10+00:00\", \"likes\": \"10\", \"retweets\": \"3\", \"quotes\": \"1\", \"replies\": \"10\", \"type\": \"STANDARD\"}";
        var postTweetUri = URI.create("/tweet");
        mockMvc.perform(MockMvcRequestBuilders.post(postTweetUri)
                .content(tweetForm)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
        var tweet = tweetRepository.findById(Long.valueOf(5));
        assertTrue(tweet.isPresent());
    }
}
