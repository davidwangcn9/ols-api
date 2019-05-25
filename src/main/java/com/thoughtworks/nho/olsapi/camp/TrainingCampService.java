package com.thoughtworks.nho.olsapi.camp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
class TrainingCampService {
    TrainingCampService(TrainingCampRepository trainingCampRepository) {
        this.trainingCampRepository = trainingCampRepository;
    }

    private final TrainingCampRepository trainingCampRepository;

    TrainingCamp getTrainingCamp(long id) throws TrainingCampNotFoundException {
        var optionalFoundTrainingCamp = trainingCampRepository.findById(id);
        if (optionalFoundTrainingCamp.isPresent()) {
            return optionalFoundTrainingCamp.get();
        } else {
            throw new TrainingCampNotFoundException();
        }
    }

    Iterable<TrainingCamp> getTrainingCamps() {
        return trainingCampRepository.findAll();
    }

    TrainingCamp createTrainingCamp(TrainingCamp trainingCamp) {
        return trainingCampRepository.save(trainingCamp);
    }

    void deleteTrainingCamp(long id) throws TrainingCampNotFoundException {
        try {
            trainingCampRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TrainingCampNotFoundException();
        }
    }
}
