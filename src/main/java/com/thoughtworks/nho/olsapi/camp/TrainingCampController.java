package com.thoughtworks.nho.olsapi.camp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainingCampController {
    TrainingCampController(TrainingCampService trainingCampService) {
        this.trainingCampService = trainingCampService;
    }

    private final TrainingCampService trainingCampService;

    @GetMapping(value = "/camps")
    public Iterable<TrainingCamp> getTrainingCamps() {
        return trainingCampService.getTrainingCamps();
    }

    @GetMapping(value = "/camps/{id}")
    public ResponseEntity<TrainingCamp> getTrainingCamp(@PathVariable Long id) {
        try {
            var foundTrainingCamp = trainingCampService.getTrainingCamp(id);
            return new ResponseEntity<>(foundTrainingCamp, HttpStatus.OK);
        } catch (TrainingCampNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/camps")
    public ResponseEntity<TrainingCamp> createTrainingCamp(@RequestBody TrainingCamp trainingCamp) {
        var createdTrainingCamp = trainingCampService.createTrainingCamp(trainingCamp);
        return new ResponseEntity<>(createdTrainingCamp, HttpStatus.CREATED);
    }

//    @PutMapping(value = "/camps/{id}")
//    public ResponseEntity<TrainingCamp> updateTrainingCamp(@PathVariable Long id, @RequestBody TrainingCamp trainingCamp) {
//        try {
//            var updatedTrainingCamp = trainingCampService.updateTrainingCamp(id, camp);
//            return new ResponseEntity<>(updatedTrainingCamp, HttpStatus.OK);
//        } catch (TrainingCampNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping(value = "/camps/{id}")
    public ResponseEntity deleteTrainingCamp(@PathVariable Long id) {
        try {
            trainingCampService.deleteTrainingCamp(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (TrainingCampNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
