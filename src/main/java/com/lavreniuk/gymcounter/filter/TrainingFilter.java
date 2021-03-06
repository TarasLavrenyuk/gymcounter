package com.lavreniuk.gymcounter.filter;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author taras
 * @date 04.06.18.
 */
@Data
public class TrainingFilter {

    private Long userId;
    private Date from;
    private Date to;
    private String trainingId;
    private List<String> trainingsIds;

    public TrainingFilter() {
    }

    public TrainingFilter(String trainingId) {
        this.trainingId = trainingId;
    }

    public TrainingFilter(Long userId, Date from, Date to) {
        this.userId = userId;
        this.from = from;
        this.to = to;
    }
}
