package org.daypilot.demo.html5eventcalendarspring.repository;


import org.daypilot.demo.html5eventcalendarspring.model.Goal;
import org.daypilot.demo.html5eventcalendarspring.model.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByGoalTypeEquals(GoalType goalType);
}
