package org.daypilot.demo.html5eventcalendarspring.service;



import org.daypilot.demo.html5eventcalendarspring.model.Goal;
import org.daypilot.demo.html5eventcalendarspring.model.GoalType;

import java.util.List;

public interface GoalService {

    //InvalidGoalIdException
    Goal findById(Long id);

    List<Goal> listAll();

    Goal create(String name, String description, GoalType goalType);

    Goal update(Long id, String name, String description, GoalType goalType);

    Goal delete(Long id);

    List<Goal> listEventFWithGoalType(GoalType goalType);
}
