package org.daypilot.demo.html5eventcalendarspring.service.impl;


import org.daypilot.demo.html5eventcalendarspring.model.Goal;
import org.daypilot.demo.html5eventcalendarspring.model.GoalType;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidGoalIdException;
import org.daypilot.demo.html5eventcalendarspring.repository.GoalRepository;
import org.daypilot.demo.html5eventcalendarspring.service.GoalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal findById(Long id) {
        return this.goalRepository.findById(id).orElseThrow(InvalidGoalIdException::new);
    }

    @Override
    public List<Goal> listAll() {
        return this.goalRepository.findAll();
    }

    @Override
    public Goal create(String name, String description, GoalType goalType) {
        Goal goal = new Goal(name, description, goalType);
        return this.goalRepository.save(goal);

    }

    @Override
    public Goal update(Long id, String name, String description, GoalType goalType){
        Goal goal = this.findById(id);
        goal.setName(name); goal.setDescription(description); goal.setGoalType(goalType);
        return this.goalRepository.save(goal);
    }

    @Override
    public Goal delete(Long id) {
        Goal goal = this.findById(id);
        this.goalRepository.delete(goal);
        return goal;
    }

    @Override
    public List<Goal> listEventFWithGoalType(GoalType goalType) {
        if(goalType == null){
            return this.goalRepository.findAll();
        }
        return this.goalRepository.findAllByGoalTypeEquals(goalType);
    }
}
