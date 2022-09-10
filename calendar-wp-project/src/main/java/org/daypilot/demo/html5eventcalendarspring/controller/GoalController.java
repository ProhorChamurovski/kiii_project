package org.daypilot.demo.html5eventcalendarspring.controller;


import org.daypilot.demo.html5eventcalendarspring.model.Goal;
import org.daypilot.demo.html5eventcalendarspring.model.GoalType;
import org.daypilot.demo.html5eventcalendarspring.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/goals")
    public String showGoals(@RequestParam(required = false) GoalType goalType, Model model) {
        List<Goal> goalList;
        if (goalType == null) {
            goalList = this.goalService.listAll();
        } else {
            goalList = this.goalService.listEventFWithGoalType(goalType);
        }
        List<GoalType> goalTypes = new ArrayList<>();
        goalTypes.add(GoalType.LONG_TERM); goalTypes.add(GoalType.SHORT_TERM);
        model.addAttribute("goalTypes", goalTypes);
        model.addAttribute("goalList", goalList);
        return "personalGoals";
    }


    @GetMapping("/goals/add")
    public String showAdd(Model model) {
        List<GoalType> goalTypes = new ArrayList<>();
        goalTypes.add(GoalType.LONG_TERM); goalTypes.add(GoalType.SHORT_TERM);
        model.addAttribute("goalTypes", goalTypes);
        return "formGoals";
    }


    @GetMapping("/goals/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Goal goal = goalService.findById(id);
        List<GoalType> goalTypes = new ArrayList<>();
        goalTypes.add(GoalType.LONG_TERM); goalTypes.add(GoalType.SHORT_TERM);
        model.addAttribute("goalTypes", goalTypes);
        model.addAttribute("goal", goal);
        return "formGoals";
    }


    @PostMapping("/goals")
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam GoalType goalType) {
        this.goalService.create(name, description, goalType);
        return "redirect:/goals";
    }


    @PostMapping("/goals/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam GoalType goalType) {

        this.goalService.update(id, name, description, goalType);
        return "redirect:/goals";
    }


    @PostMapping("/goals/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.goalService.delete(id);
        return "redirect:/goals";
    }
}
