package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.Exercise;
import com.lavreniuk.gymcounter.repository.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taras
 * @date 17.06.18.
 */
@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepo exerciseRepo;


    public List<Exercise> findAllById(List<String> exerciseIds) {
        return exerciseRepo.findAllById(exerciseIds);
    }

    public List<Exercise> getExercisesByTelegramNickName(String telegramNickname) {
        return exerciseRepo.getExercisesByTelegramNickName(telegramNickname);
    }

    public Exercise getExerciseById(String exerciseId) {
        return exerciseRepo.getOne(exerciseId);
    }
}
