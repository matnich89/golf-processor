package com.mat.golfprocessor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mat.golfprocessor.domain.dto.data.GolfData;
import com.mat.golfprocessor.exception.InvalidIdException;
import com.mat.golfprocessor.exception.convertor.DateMismatchException;
import com.mat.golfprocessor.service.GolfProcessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GolfProcessorController.class)
@AutoConfigureMockMvc
public class GolfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GolfProcessorService processorService;

    @Test
    public void shouldAcceptSource1Data() throws Exception {
        performPostWithData("src/test/resources/data/GolfData1.json");
        verify(processorService).processData(any(GolfData.class));
    }

    @Test
    public void shouldAcceptSource2Data() throws Exception {
        performPostWithData("src/test/resources/data/GolfData1.json");
        verify(processorService).processData(any(GolfData.class));
    }

    @Test
    public void shouldBadRequestUnknownFormat() throws Exception {
        mockMvc.perform(post("/data/golf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(Paths.get("src/test/resources/data/InvalidFormat.json")))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBadRequestSource1DataWithMissingField() throws Exception {
        mockMvc.perform(post("/data/golf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(Paths.get("src/test/resources/data/GolfData1Missing.json")))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBadRequestSource2DataWithMissingField() throws Exception {
        mockMvc.perform(post("/data/golf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(Paths.get("src/test/resources/data/GolfData1Missing.json")))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBadRequestBadDates() throws Exception {
        doThrow(DateMismatchException.class).when(processorService).processData(any(GolfData.class));
        mockMvc.perform(post("/data/golf")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(Paths.get("src/test/resources/data/GolfData1.json")))).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotFoundInvalidId() throws Exception {
        doThrow(new InvalidIdException("cannot find golf tournament with id 2")).when(processorService).getById(2L);
        mockMvc.perform(get("/data/golf/{id}","2")).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("cannot find golf tournament with id 2")))
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void shouldBadRequestBadContentType() throws Exception {

    }



    private void performPostWithData(String filePath) throws Exception {
        mockMvc.perform(post("/data/golf").contentType(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(Files.readAllBytes(Paths.get(filePath)))).andExpect(status().isAccepted());
    }

    @TestConfiguration
    static class MyTestConfig {
        @Bean
        public ObjectMapper objectMapper() {
            final ObjectMapper mapper = new ObjectMapper();
            final SimpleModule module = new SimpleModule();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.registerModule(module);
        }

        @Bean
        public ModelMapper modelMapper() {
            final ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                    .setFieldMatchingEnabled(true);
            return modelMapper;
        }
    }

}
