package org.daypilot.demo.html5eventcalendarspring.repository;


import org.daypilot.demo.html5eventcalendarspring.model.EventF;
import org.daypilot.demo.html5eventcalendarspring.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventFRepository extends JpaRepository<EventF, Long> {

    List<EventF> findAllByPriorityEquals(Priority priority);
}
