package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taras
 * @date 17.06.18.
 */
@Service
public class TrainingService {

    @Autowired
    private TrainingRepo trainingRepo;


    public void saveAll(List<Training> trainings) {
        trainingRepo.saveAll(trainings);
    }

    public List<Training> findByFilter(Specification<Training> filter) {
        return trainingRepo.findAll(filter);
    }

    public List<Training> getTrainingsByTelegramNickName(String telegramNickname) {
        return trainingRepo.getTrainingsByTelegramNickName(telegramNickname);
    }

    public List<Training> findByTelegramNickNameAndExerciseId(String telegramNickname, String exerciseId) {
        return trainingRepo.getTrainingsByTelegramNickNameAndExerciseId(telegramNickname, exerciseId);
    }
}
