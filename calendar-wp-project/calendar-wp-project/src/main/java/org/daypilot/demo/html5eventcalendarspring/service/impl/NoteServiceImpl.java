package org.daypilot.demo.html5eventcalendarspring.service.impl;


import org.daypilot.demo.html5eventcalendarspring.model.Note;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidNoteIdException;
import org.daypilot.demo.html5eventcalendarspring.repository.NoteRepository;
import org.daypilot.demo.html5eventcalendarspring.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteServiceImpl  implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note findById(Long id) {
        return this.noteRepository.findById(id).orElseThrow(InvalidNoteIdException::new);
    }

    @Override
    public List<Note> listAll() {
        return this.noteRepository.findAll();
    }

    @Override
    public Note create(String title, String content) {
        Note note = new Note(title, content);
        return this.noteRepository.save(note);
    }

    @Override
    public Note update(Long id, String title, String content) {
        Note note = this.findById(id);
        note.setTitle(title); note.setContent(content);
        return this.noteRepository.save(note);
    }

    @Override
    public Note delete(Long id) {
        Note note = this.findById(id);
        this.noteRepository.delete(note);
        return note;
    }

    @Override
    public List<Note> findAllByTitleContaining(String title) {
        return this.noteRepository.findAllByTitleContaining(title);
    }
}
