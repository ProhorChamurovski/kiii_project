package org.daypilot.demo.html5eventcalendarspring.controller;


import org.daypilot.demo.html5eventcalendarspring.model.Category;
import org.daypilot.demo.html5eventcalendarspring.model.Event;
import org.daypilot.demo.html5eventcalendarspring.model.EventF;
import org.daypilot.demo.html5eventcalendarspring.model.Priority;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.EventNotFoundException;
import org.daypilot.demo.html5eventcalendarspring.repository.EventRepository;
import org.daypilot.demo.html5eventcalendarspring.service.CategoryService;
import org.daypilot.demo.html5eventcalendarspring.service.EventFService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class CalendarEventController {

    private final EventRepository eventR;

    public CalendarEventController(EventRepository eventR) {
        this.eventR = eventR;
    }

    @GetMapping("/calendar/events")
    public String showEvents(@RequestParam(required = false) String text, Model model) {
        List<Event> eventList;
        if (text == null) {
            eventList = (List<Event>) this.eventR.findAll();
        } else {
            eventList = this.eventR.findAllByTextContaining(text);
        }
        model.addAttribute("eventList", eventList);
        return "personalEventsInCalendar";
    }


    @GetMapping("/calendar/events/add")
    public String showAdd(Model model) {
        return "formEventCalendar";
    }


    @GetMapping("/calendar/events/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Event eventCal = eventR.findById(id).orElseThrow(EventNotFoundException::new);
        model.addAttribute("eventCal", eventCal);
        return "formEventCalendar";
    }


    @PostMapping("/calendar/events")
    public String create(@RequestParam String text,
                         @RequestParam String start,
                         @RequestParam String end) {

        Event event = new Event(text, LocalDateTime.parse(start), LocalDateTime.parse(end));
        this.eventR.save(event);
        return "redirect:/calendar/events";
    }


    @PostMapping("/calendar/events/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String text,
                         @RequestParam String start,
                         @RequestParam String end) {
        Event byId = eventR.findById(id).orElseThrow(EventNotFoundException::new);
        byId.setText(text); byId.setStart(LocalDateTime.parse(start)); byId.setEnd(LocalDateTime.parse(end));
        this.eventR.save(byId);
        return "redirect:/calendar/events";
    }


    @PostMapping("calendar/events/{id}/delete")
    public String delete(@PathVariable Long id) {
        Event byId = eventR.findById(id).orElseThrow(EventNotFoundException::new);
        this.eventR.delete(byId);
        return "redirect:/calendar/events";
    }

}
