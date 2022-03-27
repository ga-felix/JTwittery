package org.monitordigital.jtwittery.controller;

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
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String path = "/user";

    @Test
    public void shouldCreateNewUser() throws Exception {
        var form = "{\"id\": \"3\", \"name\": \"testUser\"}";
        var uri = URI.create(path);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .contentType("application/json")
                        .content(form))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("{\"id\":3,\"name\":\"testUser\"}"));
    }

    @Test
    public void shouldFindUserById() throws Exception {
        var uri = URI.create(path + "/1");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("{\"id\":1,\"name\":\"gabrielfelix\"}"));
    }

    @Test
    public void shouldFindUserByName() throws Exception {
        var uri = URI.create(path + "/gabrielfelix");
        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("{\"id\":1,\"name\":\"gabrielfelix\"}"));
    }

}