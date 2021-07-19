package com.sumup.rest.article.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ArticleControllerTest {
    private MockMvc mockMvc;

    private static final String VALID_JSON = "{\n" +
            "    \"title\": \"Modern Times\",\n" +
            "    \"group\": \"History\",\n" +
            "    \"text\": \"To write such essential! narratives-clominated by a sense of movement through time, incorporating the technical studies(STUDIES??), and devoted (sure) to showing how the present world was shaped by its emergence from a very different past and hence concentrated on critical transitions from the past toward the present-seems to me - the great scholar!?!    to be the great challenge of modern historical scholarship\"\n" +
            "}";

    private static final String INVALID_JSON = "{\n" +
            "    \"title\": \"\",\n" +
            "    \"group\": \"History And Other Very Interesting but Too Long Things\",\n" +
            "    \"text\": \"To write such essential! narratives-clominated by a sense of movement through time, incorporating the technical studies(STUDIES??), and devoted (sure) to showing how the present world was shaped by its emergence from a very different past and hence concentrated on critical transitions from the past toward the present-seems to me - the great scholar!?!    to be the great challenge of modern historical scholarship\"\n" +
            "}";

    private static final String VALIDATION_ERRORS = "{\"messages\":[\"'title' cannot be empty!\",\"'title' should be between 3 and 17 in length!\",\"'group' should be between 5 and 10 in length!\"]}";

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
    }

    @Test
    void postArticle_ValidData_Returns201WithBody() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/articles")
                        .contentType("application/json")
                        .content(VALID_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(VALID_JSON + "kk"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void postArticle_InvalidData_Returns400WithErrorReport() throws Exception {
        final String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/articles")
                        .contentType("application/json")
                        .content(INVALID_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(VALIDATION_ERRORS))
                .andReturn().getResponse().getContentAsString();
    }
}