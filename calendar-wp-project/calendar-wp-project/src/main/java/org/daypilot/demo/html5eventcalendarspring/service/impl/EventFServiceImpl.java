package org.daypilot.demo.html5eventcalendarspring.service.impl;


import org.daypilot.demo.html5eventcalendarspring.model.Category;
import org.daypilot.demo.html5eventcalendarspring.model.EventF;
import org.daypilot.demo.html5eventcalendarspring.model.Priority;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidCategoryIdException;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidEventFIdException;
import org.daypilot.demo.html5eventcalendarspring.repository.CategoryRepository;
import org.daypilot.demo.html5eventcalendarspring.repository.EventFRepository;
import org.daypilot.demo.html5eventcalendarspring.service.EventFService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventFServiceImpl implements EventFService {

    private final EventFRepository eventFRepository;
    private final CategoryRepository categoryRepository;

    public EventFServiceImpl(EventFRepository eventFRepository, CategoryRepository categoryRepository) {
        this.eventFRepository = eventFRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<EventF> listAllEventsF() {
        return this.eventFRepository.findAll();
    }

    @Override
    public EventF findById(Long id) {
        return this.eventFRepository.findById(id).orElseThrow(InvalidEventFIdException::new);
    }

    @Override
    public EventF create(String name, String description, String timeInString, Priority priority, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        LocalDateTime time = LocalDateTime.parse(timeInString);
        EventF eventF = new EventF(name, description, time, priority, category);
        return this.eventFRepository.save(eventF);
    }

    @Override
    public EventF update(Long id, String name, String description, String time, Priority priority, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(InvalidCategoryIdException::new);
        EventF eventF = this.findById(id);
        eventF.setName(name); eventF.setDescription(description); eventF.setTime(LocalDateTime.parse(time));
        eventF.setPriority(priority); eventF.setCategory(category);
        return this.eventFRepository.save(eventF);
    }

    @Override
    public EventF delete(Long id) {
        EventF eventF = this.findById(id);
        this.eventFRepository.delete(eventF);
        return eventF;
    }

    @Override
    public EventF favorite(Long id) {
        EventF eventF = this.findById(id);

        if(!eventF.isFavorited()){
            eventF.setFavorited(true);
        }
        else{
            eventF.setFavorited(false);
        }
        return this.eventFRepository.save(eventF);
    }

    @Override
    public List<EventF> listEventFWithPriority(Priority priority) {
        if(priority == null){
            return this.eventFRepository.findAll();
        }
        return this.eventFRepository.findAllByPriorityEquals(priority);
    }
}
