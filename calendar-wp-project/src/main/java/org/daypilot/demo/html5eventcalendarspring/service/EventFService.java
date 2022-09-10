package org.daypilot.demo.html5eventcalendarspring.service;


import org.daypilot.demo.html5eventcalendarspring.model.EventF;
import org.daypilot.demo.html5eventcalendarspring.model.Priority;

import java.time.LocalDateTime;
import java.util.List;

public interface EventFService {


    List<EventF> listAllEventsF();

    //InvalidEventFIdException
    EventF findById(Long id);

    //InvalidCategoryIdException
    EventF create(String name, String description, String time, Priority priority, Long category);

    EventF update(Long id, String name, String description, String time, Priority priority, Long category);

    EventF delete(Long id);

    EventF favorite(Long id);

    List<EventF> listEventFWithPriority(Priority priority);

}
