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

    @Test
    public void shouldListTweetsAccordingToAuthors() throws Exception {
        var getTweetUri = URI.create("/tweet");
        var lineSeparator = System.lineSeparator();
        mockMvc.perform(MockMvcRequestBuilders
                .get(getTweetUri)
                .param("authors", "gabrielfelix"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("id,text,createdAt,author,likes,retweets,quotes,replies,type,referenced,referenceType" + lineSeparator +
                                "1,Eu acho que...,2007-12-03T07:15:30-02:00,gabrielfelix,10,1,0,2,standard,," + lineSeparator));
    }

}
