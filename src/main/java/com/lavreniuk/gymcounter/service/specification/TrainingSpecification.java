package com.lavreniuk.gymcounter.service.specification;

import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import com.lavreniuk.gymcounter.security.utils.AuthenticationUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author taras
 * @date 04.06.18.
 */
public class TrainingSpecification {

    public static Specification<Training> getFilter(final TrainingFilter filter) {
        return (Root<Training> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            List<Predicate> predicates = toPredicates(filter, root, cq, cb);

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private static List<Predicate> toPredicates(final TrainingFilter filter, Root<Training> root, CriteriaQuery cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new LinkedList<>();
        Predicate userIdPredicate = cb.equal(root.<Long>get("userId"), AuthenticationUtils.getCurrentUserId());
        predicates.add(userIdPredicate);
        if (filter.getFrom() != null && filter.getTo() == null) {
            Predicate dateFromPredicate = cb.greaterThanOrEqualTo(root.get("date"), filter.getFrom());
            predicates.add(dateFromPredicate);
        }

        if (filter.getTo() != null && filter.getFrom() == null) {
            Predicate dateToPredicate = cb.lessThanOrEqualTo(root.get("date"), filter.getTo());
            predicates.add(dateToPredicate);
        }

        if (filter.getTo() != null && filter.getFrom() != null) {
            Predicate dateToPredicate1 = cb.lessThanOrEqualTo(root.get("date"), filter.getTo());
            Predicate dateFromPredicate1 = cb.greaterThanOrEqualTo(root.get("date"), filter.getFrom());
            predicates.add(cb.and(dateFromPredicate1, dateToPredicate1));
        }
        return predicates;
    }


}
