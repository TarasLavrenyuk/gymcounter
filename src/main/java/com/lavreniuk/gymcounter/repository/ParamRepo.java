package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author taras
 * @date 03.06.18.
 */
public interface ParamRepo extends JpaRepository<Param, String> {

    @Query(value = "SELECT " +
            "  DISTINCT P.param_id, " +
            "  P.name, " +
            "  P.unit " +
            "FROM params P " +
            "JOIN user_params UP ON P.param_id=UP.param_id WHERE UP.user_id = :user_id ",
            nativeQuery = true)
    List<Param> getUserParams(@org.springframework.data.repository.query.Param("user_id") Long userId);

}
