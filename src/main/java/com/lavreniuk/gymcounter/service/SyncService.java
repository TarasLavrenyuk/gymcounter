package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.Exercise;
import com.lavreniuk.gymcounter.domain.Set;
import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import com.lavreniuk.gymcounter.security.utils.AuthenticationUtils;
import com.lavreniuk.gymcounter.service.specification.TrainingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author taras
 * @date 04.06.18.
 */
@Service
public class SyncService {

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private SetService setService;
    @Autowired
    private ExerciseService exerciseService;


    @Transactional
    public void save(List<Training> trainings) {
        List<Set> sets = new ArrayList<>();
        for (Training training : trainings) {
            training.setUserId(AuthenticationUtils.getCurrentUserId());
            sets.addAll(training.getSets());
        }
        trainingService.saveAll(trainings);
        setService.saveAll(sets);
    }

    public List<Training> retrieve(TrainingFilter filter) {
        List<Training> trainings = trainingService.findByFilter(TrainingSpecification.getFilter(filter));
        Map<String, Training> trainingMap = trainings.stream().collect(Collectors.toMap(Training::getTrainingId, Function.identity(), (e1, e2) -> e1));
        List<String> trainingsIds = trainings.stream().map(Training::getTrainingId).collect(Collectors.toList());
        List<Set> sets = setService.getByTrainingsIds(trainingsIds);
        List<String> exerciseIds = sets.stream().map(Set::getExerciseId).collect(Collectors.toList());
        Map<String, Exercise> exerciseMap = exerciseService.findAllById(exerciseIds).stream().collect(Collectors.toMap(Exercise::getExerciseId, Function.identity(), (e1, e2) -> e1));
        for (Set set : sets) {
            set.setExercise(exerciseMap.get(set.getExerciseId()));
            trainingMap.get(set.getTrainingId()).addSet(set);
        }
        return trainings;
    }
}
