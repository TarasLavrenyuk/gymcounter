package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.UserParam;
import com.lavreniuk.gymcounter.domain.dto.UserParamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author taras
 * @date 03.06.18.
 */
public interface UserParamRepo extends JpaRepository<UserParam, String> {

    @Query(value = "SELECT * " +
            "FROM user_params UP \n" +
            "WHERE UP.param_id = :param_id AND UP.user_id = :user_id ",
            nativeQuery = true)
    List<UserParam> getUserParams(@Param("param_id") String paramId,
                                  @Param("user_id") Long userId);
}
