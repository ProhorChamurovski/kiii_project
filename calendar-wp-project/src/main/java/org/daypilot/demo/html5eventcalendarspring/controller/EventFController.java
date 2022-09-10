package org.daypilot.demo.html5eventcalendarspring.controller;


import org.daypilot.demo.html5eventcalendarspring.model.Category;
import org.daypilot.demo.html5eventcalendarspring.model.EventF;
import org.daypilot.demo.html5eventcalendarspring.model.Priority;
import org.daypilot.demo.html5eventcalendarspring.service.CategoryService;
import org.daypilot.demo.html5eventcalendarspring.service.EventFService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class EventFController {

    private final EventFService eventFService;
    private final CategoryService categoryService;

    public EventFController(EventFService eventFService, CategoryService categoryService) {
        this.eventFService = eventFService;
        this.categoryService = categoryService;
    }



    @GetMapping("/events")
    public String showEvents(@RequestParam(required = false) Priority priority, Model model) {
        List<EventF> eventFList;
        List<Category> categories = categoryService.listAll();
        if (priority == null) {
            eventFList = this.eventFService.listAllEventsF();
        } else {
            eventFList = this.eventFService.listEventFWithPriority(priority);
        }
        List<Priority> priorities = new ArrayList<>();
        priorities.add(Priority.HIGH); priorities.add(Priority.LOW); priorities.add(Priority.MEDIUM);
        model.addAttribute("priorities", priorities);
        model.addAttribute("categories", categories);
        model.addAttribute("eventFList", eventFList);
        return "personalEvents";
    }


    @GetMapping("/events/add")
    public String showAdd(Model model) {
        List<Category> categories = categoryService.listAll();
        List<Priority> priorities = new ArrayList<>();
        priorities.add(Priority.HIGH); priorities.add(Priority.MEDIUM); priorities.add(Priority.LOW);
        model.addAttribute("priorities", priorities);
        model.addAttribute("categories", categories);
        return "formEventF";
    }


    @GetMapping("/events/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        EventF eventF = eventFService.findById(id);
        List<Category> categories = categoryService.listAll();
        List<Priority> priorities = new ArrayList<>();
        priorities.add(Priority.HIGH); priorities.add(Priority.MEDIUM); priorities.add(Priority.LOW);
        model.addAttribute("priorities", priorities);
        model.addAttribute("categories", categories);
        model.addAttribute("eventF", eventF);
        return "formEventF";
    }


    @PostMapping("/events")
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam String time,
                         @RequestParam Priority priority,
                         @RequestParam Long category) {

        this.eventFService.create(name, description, time, priority, category);
        return "redirect:/events";
    }


    @PostMapping("/events/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam String time,
                         @RequestParam Priority priority,
                         @RequestParam Long category) {

        this.eventFService.update(id, name, description, time, priority, category);
        return "redirect:/events";
    }


    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.eventFService.delete(id);
        return "redirect:/events";
    }


    @PostMapping("/events/{id}/favorite")
    public String favorite(@PathVariable Long id) {
        this.eventFService.favorite(id);
        return "redirect:/events";
    }


}
