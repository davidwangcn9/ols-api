package com.thoughtworks.nho.olsapi.camp;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class TrainingCampServiceTest {


    @Nested
    class getTrainingCamp {
        @Nested
        class when_an_existing_id_is_given {
            @Mock
            private TrainingCampRepository trainingCampRepository;

            @Test
            void returns_the_TrainingCamp() throws TrainingCampNotFoundException {
                var id= UUID.randomUUID();
                var trainingCampService = new TrainingCampService(trainingCampRepository);
                var expectedTrainingCamp = new TrainingCamp("","","");
                when(trainingCampRepository.findById(id)).thenReturn(Optional.of(expectedTrainingCamp));
                var actualTrainingCamp = trainingCampService.getTrainingCamp(id);
                assertEquals(expectedTrainingCamp, actualTrainingCamp);
            }
        }

        @Nested
        class when_an_non_existing_id_is_given {
            @Mock
            private TrainingCampRepository trainingCampRepository;

            @Test
            void throws_item_not_found_exception() {
                var givenId = UUID.randomUUID();
                when(trainingCampRepository.findById(givenId)).thenReturn(Optional.empty());
                var itemService = new TrainingCampService(trainingCampRepository);
                assertThrows(TrainingCampNotFoundException.class, () -> itemService.getTrainingCamp(givenId));
            }
        }
    }

    @Nested
    class getTrainingCamps {
        @Nested
        class when_requesting_camps {
            @Mock
            private TrainingCampRepository trainingCampRepository;

            @Test
            void returns_camps() {
                var expectedTrainingCamps = new ArrayList<TrainingCamp>();
                when(trainingCampRepository.findAllByOrderByCreatedTimeDesc()).thenReturn(expectedTrainingCamps);
                var trainingCampService = new TrainingCampService(trainingCampRepository);
                var actualTrainingCamps = trainingCampService.getTrainingCamps();
                assertEquals(expectedTrainingCamps, actualTrainingCamps);
            }
        }
    }

    @Nested
    class createTrainingCamp {
        @Nested
        class when_an_camp_is_given {
            @Mock
            private TrainingCampRepository trainingCampRepository;

            @Test
            void creates_the_camp() {
                var givenTrainingCamp = new TrainingCamp("", "", "");
                var createdTrainingCamp = new TrainingCamp("", "", "");
                when(trainingCampRepository.save(givenTrainingCamp)).thenReturn(createdTrainingCamp);
                var trainingCampService = new TrainingCampService(trainingCampRepository);
                var actualTrainingCamp = trainingCampService.createTrainingCamp(givenTrainingCamp);
                assertEquals(createdTrainingCamp, actualTrainingCamp);
            }
        }
    }
}
