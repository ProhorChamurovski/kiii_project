package org.daypilot.demo.html5eventcalendarspring.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class EventF {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany
    private List<Note> notes;

    @ManyToOne
    private Category category;

    private boolean favorited = false;

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public EventF() {
    }

    public EventF(String name, String description, LocalDateTime time, Priority priority, Category category) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
