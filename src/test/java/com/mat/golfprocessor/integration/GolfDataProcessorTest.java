package com.mat.golfprocessor.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GolfDataProcessorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveSourceData1() throws Exception {
        performPostWithData("src/test/resources/data/GolfData1.json");
        await().atMost(120, SECONDS).until(awaitUntilSourceDataIsPersisted());
        mockMvc.perform(get("/data/golf/{id}", "1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.externalId", is("174638")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.startDate", is("2021-07-09")))
                .andExpect(jsonPath("$.endDate", is("2021-07-13")))
                .andExpect(jsonPath("$.country", is("GB")))
                .andExpect(jsonPath("$.rounds", is(4)))
                .andExpect(jsonPath("$.source", is("source1")))
                .andExpect(jsonPath("$.course", is("Sunnydale Golf Course")));
    }

    @Test
    public void shouldSaveSourceData2() throws Exception {
        performPostWithData("src/test/resources/data/GolfData2.json");
        await().atMost(120, SECONDS).until(awaitUntilSourceDataIsPersisted());
        mockMvc.perform(get("/data/golf/{id}", "1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.externalId", is("southWestInvitational")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.startDate", is("2021-12-01")))
                .andExpect(jsonPath("$.endDate", is("2021-12-02")))
                .andExpect(jsonPath("$.country", is("United States Of America")))
                .andExpect(jsonPath("$.rounds", is(2)))
                .andExpect(jsonPath("$.source", is("source2")))
                .andExpect(jsonPath("$.course", is("Happy Days Golf Club")));

    }

    private Callable<Boolean> awaitUntilSourceDataIsPersisted() throws Exception {
        MvcResult result = mockMvc.perform(get("/data/golf/{id}", "1")).andReturn();
        while (result.getResponse().getStatus() != 200) {
            result = mockMvc.perform(get("/data/golf/{id}", "1")).andReturn();
        }
        return () -> true;
    }


    private void performPostWithData(String fileLocation) throws Exception {
        mockMvc.perform(post("/data/golf").contentType(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(Files.readAllBytes(Paths.get(fileLocation)))).andExpect(status().isAccepted()).andReturn();
    }
}
