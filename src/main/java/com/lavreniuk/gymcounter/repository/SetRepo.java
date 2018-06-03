package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Set;
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
public interface SetRepo extends JpaRepository<Set, String> {

    @Query(value = "SELECT * FROM gymcounter.public.sets S " +
            " WHERE S.training_id IN :trainingsIds ", nativeQuery = true)
    List<Set> findByTrainingsIds(@Param("trainingsIds") List<String> trainingsIds);
}
