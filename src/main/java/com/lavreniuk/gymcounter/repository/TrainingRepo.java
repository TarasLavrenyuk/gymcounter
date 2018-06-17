package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taras
 * @date 03.06.18.
 */
@Repository
public interface TrainingRepo extends JpaRepository<Training, String>, JpaSpecificationExecutor<Training> {

    @Query(value = "SELECT * " +
            "FROM trainings T " +
            "WHERE T.user_id = (SELECT TC.user_id " +
            "                   FROM telegram_credentials TC " +
            "                   WHERE TC.telegram_username = :telegramNickname );",
            nativeQuery = true)
    List<Training> getTrainingsByTelegramNickName(@Param("telegramNickname") String telegramNickname);

}
