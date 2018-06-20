package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.*;
import com.lavreniuk.gymcounter.domain.dto.UserParamDto;
import com.lavreniuk.gymcounter.exceptions.NoSuchTrainingException;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author taras
 * @date 17.06.18.
 */
@Service
public class TelegramService {

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private SetService setService;
    @Autowired
    private SyncService syncService;
    @Autowired
    private UserParamService userParamService;


    public List<Training> getUserTrainings(String telegramNickname) {
        return trainingService.getTrainingsByTelegramNickName(telegramNickname);
    }

    public Training getUserTraining(String trainingId) throws NoSuchTrainingException {
        TrainingFilter trainingFilter = new TrainingFilter(trainingId);
        List<Training> trainings = syncService.retrieve(trainingFilter);
        if (trainings.size() > 0) {
            return trainings.get(0);
        } else throw new NoSuchTrainingException();
    }

    public List<Exercise> getUserExercises(String telegramNickname) {
        return exerciseService.getExercisesByTelegramNickName(telegramNickname);
    }

    public List<Training> getUserExercise(String telegramNickname, String exerciseId) {
        List<Training> trainings = trainingService.findByTelegramNickNameAndExerciseId(telegramNickname, exerciseId);
        Map<String, Training> trainingMap = trainings.stream().collect(Collectors.toMap(Training::getTrainingId, Function.identity(), (e1, e2) -> e1));
        List<String> trainingsIds = trainings.stream().map(Training::getTrainingId).collect(Collectors.toList());
        List<Set> sets = setService.getByTrainingsIdsAndExerciseId(trainingsIds, exerciseId);
        Exercise exercise = exerciseService.getExerciseById(exerciseId);
        for (Set set : sets) {
            set.setExercise(exercise);
            trainingMap.get(set.getTrainingId()).addSet(set);
        }
        return trainings;

    }

    public List<Param> getUserParams(String telegramNickname) {
        return userParamService.getUserParamsListByTelegramNickname(telegramNickname);

    }

    public List<UserParamDto> getUserParam(String telegramNickname, String paramId) {
        return userParamService.getUserParamsByTelegramNickname(paramId, telegramNickname);
    }
}
