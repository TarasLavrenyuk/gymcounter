package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author taras
 * @date 28.04.18.
 */
@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {



}
