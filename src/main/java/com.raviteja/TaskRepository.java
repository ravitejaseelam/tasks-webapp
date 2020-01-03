package com.raviteja;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public interface TaskRepository {
    public List<Task> display() throws SQLException, ParseException;
    public List<Task> search( String n) throws SQLException, ParseException;
    public List<Task> searchStatus( String n) throws SQLException, ParseException;
    public void delete(String n,String id) throws SQLException;
    public void add(String name, String dis, Date due, Status st,String id,Date dueDate) throws ClassNotFoundException, SQLException;
    public void updateStatus(Status status,String name,String id) throws SQLException;
    public List<Task> getPendingTask() throws SQLException, ParseException;
    public List<Task> getTodaysTask(Date date) throws ParseException, SQLException;
}
