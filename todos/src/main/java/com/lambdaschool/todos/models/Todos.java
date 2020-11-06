package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todos extends Auditable {
    //Primary key id, not null, long of the todos table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;

    //description, string, not null
    @Column(nullable = false)
    private String description;

    //completed boolean, default completed to false
    private boolean completed;

    //Connect userid as a foreign key, many todos to one user
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
    private User user;

    //Empty constructor for JPA
    public Todos() {
    }

    public Todos(User user, String description) {
        this.user = user;
        this.description = description;
        this.completed = false;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
