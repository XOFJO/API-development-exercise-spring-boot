package com.oocl.training.controller;

public class Todo {
    private int id;
    private String title;
    private String status;

    public Todo(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
