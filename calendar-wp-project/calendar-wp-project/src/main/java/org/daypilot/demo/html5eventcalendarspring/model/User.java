package org.daypilot.demo.html5eventcalendarspring.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @OneToMany
    private List<Note> noteList;

    @OneToMany
    private List<EventF> eventFList;

    @OneToMany
    private List<Event> eventCalendarList;

    public List<Event> getEventCalendarList() {
        return eventCalendarList;
    }

    public void setEventCalendarList(List<Event> eventCalendarList) {
        this.eventCalendarList = eventCalendarList;
    }

    @OneToMany
    private List<Goal> goalsList;

    public User() {
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = Role.ROLE_ADMIN;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<EventF> getEventFList() {
        return eventFList;
    }

    public void setEventFList(List<EventF> eventFList) {
        this.eventFList = eventFList;
    }

    public List<Goal> getGoalsList() {
        return goalsList;
    }

    public void setGoalsList(List<Goal> goalsList) {
        this.goalsList = goalsList;
    }
}
