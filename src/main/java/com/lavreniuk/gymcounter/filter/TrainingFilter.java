package com.lavreniuk.gymcounter.filter;

import lombok.Data;

import java.util.Date;

/**
 * @author taras
 * @date 04.06.18.
 */
@Data
public class TrainingFilter {

    private Date from;
    private Date to;

    public TrainingFilter() {
    }

    public TrainingFilter(Date from, Date to) {
        this.from = from;
        this.to = to;
    }
}
