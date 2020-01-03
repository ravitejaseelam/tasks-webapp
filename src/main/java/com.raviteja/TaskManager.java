package com.raviteja;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class TaskManager {
   //InMemoryTaskRepository repository=new InMemoryTaskRepository();
//    TaskFileRepository repository =new TaskFileRepository();
    TaskDatabaseRepository repository =new TaskDatabaseRepository();

    public TaskManager() throws SQLException {
    }

    public List<Task> display() throws SQLException, ParseException {
        List<Task> taskList= repository.display();
        return taskList;
    }


    public void add( String name, String dis, Date due, Status st,Date dueDate) throws SQLException {
        Random n = new Random();
        int sid=n.nextInt(10000);
        String id= String.valueOf(sid);
        repository.add(name,dis,due,st,id,dueDate);
    }

    public List<Task> search( String n) throws SQLException, ParseException {
        List<Task> nameSearchList = repository.search(n);
            return nameSearchList;

    }
    public List<Task> searchStatus( String n) throws SQLException, ParseException {
        List<Task> statusSerchList = repository.searchStatus(n);
            return statusSerchList;

    }

    public void delete( String name,String id) throws SQLException {
        repository.delete(name,id);
    }

    public void updateStatus(Status status,String name ,String id ) throws SQLException {
        repository.updateStatus(status,name,id);
    }

    public List<Task> getPendingTask() throws SQLException, ParseException {
        List<Task> pendingList= repository.getPendingTask();
        return pendingList;
    }
    public List<Task> getTodaysTask(Date date) throws ParseException, SQLException {
        List<Task> todaysList= repository.getTodaysTask(date);
        return todaysList;
    }

}

