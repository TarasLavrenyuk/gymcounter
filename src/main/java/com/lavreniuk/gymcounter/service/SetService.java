package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.Set;
import com.lavreniuk.gymcounter.repository.SetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taras
 * @date 17.06.18.
 */
@Service
public class SetService {

    @Autowired
    private SetRepo setRepo;


    public void saveAll(List<Set> sets) {
        setRepo.saveAll(sets);
    }

    public List<Set> getByTrainingsIds(List<String> trainingsIds) {
        return setRepo.findByTrainingsIds(trainingsIds);
    }

    public List<Set> getByTrainingsIdsAndExerciseId(List<String> trainingsIds, String exerciseId) {
        return setRepo.findByTrainingsIdsAndExerciseId(trainingsIds, exerciseId);
    }
}

