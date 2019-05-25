package com.thoughtworks.nho.olsapi.camp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class TrainingCampService {
    TrainingCampService(TrainingCampRepository trainingCampRepository) {
        this.trainingCampRepository = trainingCampRepository;
    }

    private final TrainingCampRepository trainingCampRepository;

    TrainingCamp getTrainingCamp(UUID id) throws TrainingCampNotFoundException {
        var optionalFoundTrainingCamp = trainingCampRepository.findById(id);
        if (optionalFoundTrainingCamp.isPresent()) {
            return optionalFoundTrainingCamp.get();
        } else {
            throw new TrainingCampNotFoundException();
        }
    }

    Iterable<TrainingCamp> getTrainingCamps() {
        return trainingCampRepository.findAllByOrderByCreatedTimeDesc();
    }

    TrainingCamp createTrainingCamp(TrainingCamp trainingCamp) {
        return trainingCampRepository.save(trainingCamp);
    }

    void deleteTrainingCamp(UUID id) throws TrainingCampNotFoundException {
        try {
            trainingCampRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TrainingCampNotFoundException();
        }
    }
}
