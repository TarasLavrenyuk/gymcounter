package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.controllers.SyncController;
import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.exceptions.NoSuchTrainingException;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import com.lavreniuk.gymcounter.repository.TrainingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taras
 * @date 17.06.18.
 */
@Service
public class TelegramService {

    @Autowired
    private TrainingRepo trainingRepo;
    @Autowired
    private SyncService syncService;



    public List<Training> getUserTrainings(String telegramNickname) {
        return trainingRepo.getTrainingsByTelegramNickName(telegramNickname);
    }

    public Training getUserTraining(String trainingId) throws NoSuchTrainingException {
        TrainingFilter trainingFilter = new TrainingFilter(trainingId);
        List<Training> trainings = syncService.retrieve(trainingFilter);
        if (trainings.size() > 0) {
            return trainings.get(0);
        } else throw new NoSuchTrainingException();
    }
}
