package org.daypilot.demo.html5eventcalendarspring.service;




import org.daypilot.demo.html5eventcalendarspring.model.Note;

import java.util.List;

public interface NoteService {

    Note findById(Long id);

    List<Note> listAll();

    Note create(String title, String content);

    Note update(Long id, String title, String content);

    Note delete(Long id);

    List<Note> findAllByTitleContaining(String title);
}
