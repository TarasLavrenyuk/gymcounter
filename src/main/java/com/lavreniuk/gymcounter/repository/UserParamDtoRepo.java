package com.lavreniuk.gymcounter.repository;

import com.lavreniuk.gymcounter.domain.dto.UserParamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taras
 * @date 04.06.18.
 */
@Repository
public interface UserParamDtoRepo extends JpaRepository<UserParamDto, String> {

    @Query(value = "SELECT " +
            "  UP.user_param_id, \n" +
            "  UP.user_id, \n" +
            "  UP.value, \n" +
            "  UP.param_date, \n" +
            "  UP.param_id, \n" +
            "  P.name, \n" +
            "  P.unit " +
            "FROM user_params UP \n" +
            "  JOIN params P ON UP.param_id=P.param_id \n" +
            "WHERE UP.param_id = :param_id AND UP.user_id = :user_id ",
            nativeQuery = true)
    List<UserParamDto> getUserParam(@Param("param_id") String paramId,
                                    @Param("user_id") Long userId);


    @Query(value = "SELECT " +
            "  UP.user_param_id, " +
            "  UP.user_id, " +
            "  UP.value, " +
            "  UP.param_date, " +
            "  UP.param_id, " +
            "  P.name, " +
            "  P.unit " +
            "FROM user_params UP " +
            "  JOIN params P ON UP.param_id = P.param_id " +
            "  JOIN telegram_credentials TC ON TC.user_id = UP.user_id " +
            "WHERE UP.param_id = :paramId " +
            "      AND TC.telegram_username = :telegramNickname " +
            "ORDER BY UP.param_date; ", nativeQuery = true)
    List<UserParamDto> getUserParamsByTelegramNickname(@Param("paramId") String paramId,
                                                       @Param("telegramNickname") String telegramNickname);
}
