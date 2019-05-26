package com.thoughtworks.nho.olsapi.camp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(allowCredentials="true",maxAge = 3600)
@RequestMapping("/api")
public class TrainingCampController {
    TrainingCampController(TrainingCampService trainingCampService) {
        this.trainingCampService = trainingCampService;
    }

    private final TrainingCampService trainingCampService;


    @GetMapping(value = "/camps")
    public Iterable<TrainingCamp> getTrainingCamps() {
        return trainingCampService.getTrainingCamps();
    }

    @GetMapping(value = "/camps/query={name}")
    public Iterable<TrainingCamp> getTrainingCamps(@PathVariable String name) {
        return trainingCampService.getTrainingCamps(name);
    }

    @GetMapping(value = "/camps/{uuid}")
    public ResponseEntity<TrainingCamp> getTrainingCamp(@PathVariable UUID uuid) {
        try {
            var foundTrainingCamp = trainingCampService.getTrainingCamp(uuid);
            return new ResponseEntity<>(foundTrainingCamp, HttpStatus.OK);
        } catch (TrainingCampNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/camp")
    public ResponseEntity<TrainingCamp> createTrainingCamp(@RequestBody TrainingCamp trainingCamp) {
        var createdTrainingCamp = trainingCampService.createTrainingCamp(trainingCamp);
        return new ResponseEntity<>(createdTrainingCamp, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/camps/{id}")
    public ResponseEntity deleteTrainingCamp(@PathVariable UUID id) {
        try {
            trainingCampService.deleteTrainingCamp(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (TrainingCampNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
