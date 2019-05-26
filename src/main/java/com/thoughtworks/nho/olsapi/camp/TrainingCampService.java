package com.thoughtworks.nho.olsapi.camp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

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

    Iterable<TrainingCamp> getTrainingCamps(String title) {
        var camps = trainingCampRepository.findAllByOrderByCreatedTimeDesc();
        if (title != null && !title.equals("")) {
            camps = camps.stream().filter(c->c.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        }
        return camps;
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
