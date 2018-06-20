package com.lavreniuk.gymcounter.service;

import com.lavreniuk.gymcounter.domain.Param;
import com.lavreniuk.gymcounter.domain.UserParam;
import com.lavreniuk.gymcounter.domain.dto.UserParamDto;
import com.lavreniuk.gymcounter.repository.ParamRepo;
import com.lavreniuk.gymcounter.repository.UserParamDtoRepo;
import com.lavreniuk.gymcounter.repository.UserParamRepo;
import com.lavreniuk.gymcounter.security.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taras
 * @date 03.06.18.
 */
@Service
public class UserParamService {

    @Autowired
    private ParamRepo paramRepo;
    @Autowired
    private UserParamRepo userParamRepo;
    @Autowired
    private UserParamDtoRepo userParamDtoRepo;

    public void addParam(Param param) {
        paramRepo.save(param);
    }

    public void deleteParam(Param param) {
        paramRepo.delete(param);
    }

    public void saveUserParams(List<UserParam> userParams) {
        Long userId = AuthenticationUtils.getCurrentUserId();
        for (UserParam userParam : userParams) {
            userParam.setUserId(userId);
        }
        userParamRepo.saveAll(userParams);
    }

    public List<UserParamDto> getUserParams(String paramId, Long userId) {
        return userParamDtoRepo.getUserParam(paramId, userId);
    }

    public List<UserParamDto> getUserParamsByTelegramNickname(String paramId, String telegramNickname) {
        return userParamDtoRepo.getUserParamsByTelegramNickname(paramId, telegramNickname);
    }

    public List<Param> getUserParams(Long userId) {
        List<Param> userParams = paramRepo.getUserParams(userId);
        for (Param userParam : userParams) {
            System.out.println(userParam.getParamId() + " " + userId);
            userParam.setParams(
                    userParamRepo.getUserParams(userParam.getParamId(), userId));
        }
        return userParams;
    }

    public List<Param> getUserParamsListByTelegramNickname(String telegramNickname) {
        return paramRepo.getUserParamsListByTelegramNickname(telegramNickname);
    }
}
