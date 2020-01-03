package com.raviteja;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InMemoryTaskRepository implements TaskRepository{
    List<Task> taskList = new ArrayList<Task>();
    public void add(String name, String dis, Date due, Status st,String id,Date dueDate) {
        taskList.add(new Task(name, dis, due,st,id,dueDate));
    }

    @Override
    public void updateStatus(Status status, String name, String id) {

        for (Task obj : taskList) {
            if (obj.name.equals(name) && obj.id.equals(id))
                obj.status = status;
        }
    }

    @Override
    public List<Task> getPendingTask() {
        List<Task> pendingList = new ArrayList<Task>();
        for (Task obj : taskList) {
            if (!obj.name.equals(Status.valueOf("Complete")))
                pendingList.add(obj);
        }
        return pendingList;
    }

    @Override
    public List<Task> getTodaysTask(Date date) throws ParseException {
        List<Task> todaysTask = new ArrayList<Task>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        Date d1 = formatter1.parse(strDate);
        Date d2;
        for (Task obj : taskList) {
            String strDueDate = dateFormat.format(obj.duedate);
            d2=formatter1.parse(strDueDate);
            // System.out.println(d1+"   "+d2);
            if (d1.compareTo(d2)==0)
            {

                todaysTask.add(obj);
            }

        }

        return todaysTask;
    }



    public List<Task> display()
    {
        return taskList;
    }

    public List<Task> search( String n) {
        List<Task> nameSearchList = new ArrayList<Task>();
        for (Task obj : taskList) {
            if (obj.name.equals(n))
                nameSearchList.add(obj);
        }
        return nameSearchList;
    }
    public List<Task> searchStatus( String n) {
        List<Task> statusSerchList = new ArrayList<Task>();
        for (Task obj : taskList) {
            if (obj.status.equals(Status.valueOf(n)))
                statusSerchList.add(obj);
        }
        return statusSerchList;
    }
    public void delete( String n,String id) {
        for (Task obj : taskList) {
            if (obj.id.equals(id))
                taskList.remove(obj);
        }
    }
}
