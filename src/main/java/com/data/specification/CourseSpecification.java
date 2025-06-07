package com.data.specification;

import com.data.entity.Course;
import com.data.form.CourseFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {
    // Phuong thuc build menh de WHERE title LIKE '%...%'
    public static Specification<Course> buildSpec(CourseFilterForm form){
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();//list dieu kien
                String search = form.getSearch();
                if (StringUtils.hasText(search)){
                    //StringUtil check cả dấu cách
                    String pattern = "%" + search.trim() + "%";
                    Predicate predicate = builder.like(root.get("coursesName"),pattern);
                    //FROM course WHERE course_name LIKE '%pattern%'
                    predicates.add(predicate);
                }
                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
