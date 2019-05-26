package com.thoughtworks.nho.olsapi.camp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainingCampController.class)
class TrainingCampControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingCampService trainingCampService;

    private static ObjectMapper jacksonDataMapper;

    @BeforeAll
    static void initializeJacksonMapper() {
        jacksonDataMapper = new ObjectMapper();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        jacksonDataMapper.setDateFormat(dateFormat);
    }

    private ArrayList<TrainingCamp> buildTrainingCamps() {
        var expectedCamp = buildTrainingCamp();
        var expectedCamps = new ArrayList<TrainingCamp>();
        expectedCamps.add(expectedCamp);
        return expectedCamps;
    }

    private TrainingCamp buildTrainingCamp() {
        var expectedCamp = new TrainingCamp("iPhone","","");
        return expectedCamp;
    }

    @Nested
    class getTrainingCamps {
        @Nested
        class when_requesting_camps {
            @Test
            void responds_camps() throws Exception {
                var expectedTrainingCamps = buildTrainingCamps();
                var payload = jacksonDataMapper.writeValueAsString(expectedTrainingCamps);
                when(trainingCampService.getTrainingCamps()).thenReturn(expectedTrainingCamps);
                mockMvc.perform(get("/camps"))
                        .andExpect(status().isOk())
                        .andExpect(content().string(payload));
            }
        }
    }

    @Nested
    class getItem {
        @Nested
        class when_an_existing_id_is_given {
            @Test
            void responds_the_camp() throws TrainingCampNotFoundException, Exception {
                var givenId = UUID.randomUUID();
                var expectedTrainingCamp = buildTrainingCamp();
                var payload = jacksonDataMapper.writeValueAsString(expectedTrainingCamp);
                when(trainingCampService.getTrainingCamp(givenId)).thenReturn(expectedTrainingCamp);
                mockMvc.perform(get("/camps/{id}", givenId))
                        .andExpect(status().isOk())
                        .andExpect(content().string(payload));
            }
        }

        @Nested
        class when_an_non_existing_id_is_given {
            @Test
            void responds_404() throws TrainingCampNotFoundException, Exception {
                var givenId = UUID.randomUUID();
                when(trainingCampService.getTrainingCamp(givenId)).thenThrow(TrainingCampNotFoundException.class);
                mockMvc.perform(get("/items/{id}", givenId))
                        .andExpect(status().isNotFound());
            }
        }
    }

    @Nested
    class createCamp {
        @Nested
        class when_an_camp_is_given {
            @Test
            void creates_the_camp() throws Exception {
                var createdCamp = buildTrainingCamp();
                var payload = jacksonDataMapper.writeValueAsString(createdCamp);
                when(trainingCampService.createTrainingCamp(any(TrainingCamp.class))).thenReturn(createdCamp);
                mockMvc.perform(post("/camp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(payload))
                        .andExpect(status().isCreated())
                        .andExpect(content().string(payload));
            }
        }
    }

}
