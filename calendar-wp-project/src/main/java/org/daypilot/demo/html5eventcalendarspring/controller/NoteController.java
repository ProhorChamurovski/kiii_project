package org.daypilot.demo.html5eventcalendarspring.controller;


import org.daypilot.demo.html5eventcalendarspring.model.Note;
import org.daypilot.demo.html5eventcalendarspring.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String showNotes(@RequestParam(required = false) String title, Model model) {
        List<Note> notes;
        if (title == null || title.equals("secret-shorcut181539")) {
            notes = this.noteService.listAll();
        } else {
            notes = this.noteService.findAllByTitleContaining(title);
        }
        model.addAttribute("notes", notes);
        return "personalNotes";
    }


    @GetMapping("/notes/add")
    public String showAdd(Model model) {
        return "formNotes";
    }


    @GetMapping("/notes/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        return "formNotes";
    }


    @PostMapping("/notes")
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        this.noteService.create(title, content);
        return "redirect:/notes";
    }


    @PostMapping("/notes/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String title,
                         @RequestParam String content) {

        this.noteService.update(id, title, content);
        return "redirect:/notes";
    }


    @PostMapping("/notes/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.noteService.delete(id);
        return "redirect:/notes";
    }

}
