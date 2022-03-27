package org.monitordigital.jtwittery.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private URI path = URI.create("/tweet");

    @Test
    public void shouldListTweetsAccordingSinceDate() throws Exception {
        var lineSeparator = System.lineSeparator();
        mockMvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .param("since", "2007-12-04T07:15:30-02:00"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("id,text,createdAt,author,likes,retweets,quotes,replies,type,referenced,referenceType"
                                + lineSeparator
                                + "2,RT @gabrielfelix:Eu acho que...,2007-12-04T07:15:30-02:00,pablo_ortellado,3,0,0,0,retweet,1,retweeted"
                                + lineSeparator));
    }

    @Test
    public void shouldListTweetsAccordingUntilDate() throws Exception {
        var lineSeparator = System.lineSeparator();
        mockMvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .param("until", "2007-12-03T07:15:30-02:00"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("id,text,createdAt,author,likes,retweets,quotes,replies,type,referenced,referenceType"
                                + lineSeparator
                                + "1,Eu acho que...,2007-12-03T07:15:30-02:00,gabrielfelix,10,1,0,2,standard,,"
                                + lineSeparator));
    }

    @Test
    public void shouldListTweetsAccordingToTypes() throws Exception {
        var lineSeparator = System.lineSeparator();
        mockMvc.perform(MockMvcRequestBuilders
                        .get(path)
                        .param("types", "STANDARD"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("id,text,createdAt,author,likes,retweets,quotes,replies,type,referenced,referenceType"
                                + lineSeparator
                                + "1,Eu acho que...,2007-12-03T07:15:30-02:00,gabrielfelix,10,1,0,2,standard,,"
                                + lineSeparator));
    }

    @Test
    public void shouldListTweetsAccordingToAuthors() throws Exception {
        var lineSeparator = System.lineSeparator();
        mockMvc.perform(MockMvcRequestBuilders
                .get(path)
                .param("authors", "1"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("id,text,createdAt,author,likes,retweets,quotes,replies,type,referenced,referenceType"
                                + lineSeparator
                                + "1,Eu acho que...,2007-12-03T07:15:30-02:00,gabrielfelix,10,1,0,2,standard,,"
                                + lineSeparator));
    }

}
