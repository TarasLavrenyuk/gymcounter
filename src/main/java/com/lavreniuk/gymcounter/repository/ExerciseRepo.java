package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taras
 * @date 28.04.18.
 */
@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, String> {

    @Query(value = "SELECT DISTINCT E.* " +
            "FROM exercises E " +
            "  JOIN sets S ON E.exercise_id = S.exercise_id " +
            "  JOIN trainings T ON S.training_id = T.training_id " +
            "  JOIN telegram_credentials TC ON TC.user_id = T.user_id " +
            "WHERE TC.telegram_username = :telegramNickname ;",
            nativeQuery = true)
    List<Exercise> getExercisesByTelegramNickName(@Param("telegramNickname") String telegramNickname);
}
