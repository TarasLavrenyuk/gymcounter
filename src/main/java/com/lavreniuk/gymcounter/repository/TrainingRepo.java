package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author taras
 * @date 03.06.18.
 */
public interface TrainingRepo extends JpaRepository<Training, String>, JpaSpecificationExecutor<Training> {
}
