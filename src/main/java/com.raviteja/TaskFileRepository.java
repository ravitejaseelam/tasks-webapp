package com.raviteja;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class TaskFileRepository implements TaskRepository {
    List<Task> tasks = new ArrayList<Task>();
    private static final String TASKS_JSON_FILE = "/home/ravitejas/Desktop/tasks.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(TASKS_JSON_FILE);


    public TaskFileRepository() {
        // tasks=readFromFile();
    }

    private void writeToFile(List<Task> tasks) {
        //     objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new FileWriter(TASKS_JSON_FILE), tasks);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Task> readFromFile() {

        //   objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Task> display() {
        return readFromFile();
    }

    @Override
    public List<Task> search(String n) {
        List<Task> nameSearchList = new ArrayList<Task>();
        tasks=readFromFile();
        for (Task obj : tasks) {
            if (obj.name.equals(n))
                nameSearchList.add(obj);
        }
        return nameSearchList;
    }

    @Override
    public List<Task> searchStatus(String n) {
        List<Task> statusSerchList = new ArrayList<Task>();
        List<Task> taskList = readFromFile();
        for (Task obj : taskList) {
            if (obj.status.equals(Status.valueOf(n)))
                statusSerchList.add(obj);
        }
        return statusSerchList;
    }

    @Override
    public void delete(String n,String id) {
        List<Task> deletedList = new ArrayList<Task>();
        tasks=readFromFile();
        for (Task obj : tasks) {
            if (obj.id.equals(Status.valueOf(id)))
                tasks.remove(obj);
        }
        writeToFile(tasks);

    }

    @Override
    public void add(String name, String dis, Date due, Status st, String id,Date dueDate) {
        tasks = readFromFile();
        tasks.add(new Task(name, dis, due,st,id,dueDate));
        writeToFile(tasks);

    }

    @Override
    public void updateStatus(Status status, String name, String id) {
        for (Task obj : tasks) {
            if (obj.name.equals(name) && obj.id.equals(id))
                obj.status = status;
        }
        writeToFile(tasks);
    }

    @Override
    public List<Task> getPendingTask() {
        List<Task> pendingList = new ArrayList<Task>();
        tasks=readFromFile();
        for (Task obj : tasks) {
            if (!(obj.status.equals(Status.valueOf("Complete"))))
                pendingList.add(obj);
        }
        return pendingList;
    }

    @Override
    public List<Task> getTodaysTask(Date date) throws ParseException {
        List<Task> todaysTask = new ArrayList<Task>();
        tasks=readFromFile();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        Date d1 = formatter1.parse(strDate);
        Date d2;
        for (Task obj : tasks) {
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


}