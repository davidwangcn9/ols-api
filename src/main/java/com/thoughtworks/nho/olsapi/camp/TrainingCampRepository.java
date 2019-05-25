package com.thoughtworks.nho.olsapi.camp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

interface TrainingCampRepository extends CrudRepository<TrainingCamp, UUID> {
    List<TrainingCamp> findAllByOrderByCreatedTimeDesc();
}
