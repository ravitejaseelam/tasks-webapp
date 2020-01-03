package com.raviteja;
import java.util.Date;

public class Task {
    String name, description, id;
    Date date, duedate;
    Status status;



    public Task() {
    }
    public Task(String name, String dis, Date dat, Status st, String id, Date dueDate) {
        this.name = name;
        this.description = dis;
        this.date = dat;
        this.status = st;
        this.id = id;
        this.duedate = dueDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Status getSt1() {
        return status;
    }

    public void setSt1(Status st1) {
        this.status = st1;
    }


}