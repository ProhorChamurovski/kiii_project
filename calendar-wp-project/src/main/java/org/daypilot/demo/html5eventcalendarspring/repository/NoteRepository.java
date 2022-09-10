package org.daypilot.demo.html5eventcalendarspring.repository;


import org.daypilot.demo.html5eventcalendarspring.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByTitleContaining(String title);

}
