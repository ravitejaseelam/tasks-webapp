package com.raviteja;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;


public class TaskDatabaseRepository implements TaskRepository{
    static Connection con;
    static Statement stmt;
    SimpleDateFormat sdfo=new SimpleDateFormat("yyyy-MM-dd");
    TaskDatabaseRepository() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "testuser", "password");
            stmt = con.createStatement();
        }
         catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }

    }


    @Override
    public List<Task> display() throws SQLException, ParseException {

        String query="SELECT * FROM task4 ORDER BY DUEDATE ASC";
        ResultSet searchList=stmt.executeQuery(query);
        List<Task> displayList= new ArrayList<Task>() ;
        while (searchList.next())
        {
            String name=searchList.getString(1);
            String dis=searchList.getString(2);
            String dueDate=searchList.getString(3);
            String status=searchList.getString(4);
            String id=searchList.getString(5);
            Date date=new Date();
            Date dueDate1=sdfo.parse(dueDate);
            displayList.add(new Task(name,dis,date,Status.valueOf(status),id,dueDate1));
        }
        return displayList;
    }

    @Override
    public List<Task> search(String n) throws SQLException, ParseException {
        String query="SELECT * FROM task4 WHERE taskName LIKE '"+n+"'ORDER BY DUEDATE ASC";
        ResultSet searchList=stmt.executeQuery(query);
        List<Task> searchListl= new ArrayList<Task>() ;

        while (searchList.next())
        {
            String name=searchList.getString(1);
            String dis=searchList.getString(2);
            String dueDate=searchList.getString(3);
            String status=searchList.getString(4);
            String id=searchList.getString(5);
            Date date=new Date();
            Date dueDate1=sdfo.parse(dueDate);
            searchListl.add(new Task(name,dis,date,Status.valueOf(status),id,dueDate1));
        }
        return searchListl;
    }

    @Override
    public List<Task> searchStatus(String n) throws SQLException, ParseException {

        String query="SELECT * FROM task4 WHERE status LIKE '"+n+"'ORDER BY DUEDATE ASC";
        ResultSet searchList=stmt.executeQuery(query);
        List<Task> searcListl=new ArrayList<Task>();
        while (searchList.next())
        {
            String name=searchList.getString(1);
            String dis=searchList.getString(2);
            String dueDate=searchList.getString(3);
            String status=searchList.getString(4);
            String id=searchList.getString(5);
            Date date=new Date();
            Date dueDate1=sdfo.parse(dueDate);
            searcListl.add(new Task(name,dis,date,Status.valueOf(status),id,dueDate1));
        }
        return searcListl;
    }

    @Override
    public void delete(String n, String idDelete) throws SQLException {
        String query1="DELETE FROM task4 WHERE id LIKE '"+idDelete+"'";
        stmt.executeUpdate(query1);
    }

    @Override
    public void add(String name, String dis, Date dat, Status st, String id, Date dueDate) throws  SQLException {

        String sdueDate=sdfo.format(dueDate);
        String query="insert into task4 values('"+name+"','"+dis+"','"+sdueDate+"','"+st+"','"+id+"')";
        stmt.executeUpdate(query);

    }

    @Override
    public void updateStatus(Status status, String name, String id) throws SQLException {
        String query1="UPDATE task4 SET status ='"+status+"' WHERE id LIKE '"+id+"'";
        stmt.executeUpdate(query1);
    }

    @Override
    public List<Task> getPendingTask() throws SQLException, ParseException {
        String n="Complete";
        String query="SELECT * FROM task4 WHERE status NOT LIKE '"+n+"'ORDER BY DUEDATE ASC";
        ResultSet searchList=stmt.executeQuery(query);
        List<Task> pendingList=new ArrayList<Task>();
        while (searchList.next())
        {
            String name=searchList.getString(1);
            String dis=searchList.getString(2);
            String dueDate=searchList.getString(3);
            String status=searchList.getString(4);
            String id=searchList.getString(5);
            Date date=new Date();
            Date dueDate1=sdfo.parse(dueDate);
            pendingList.add(new Task(name,dis,date,Status.valueOf(status),id,dueDate1));
        }
        return pendingList;
    }

    @Override
    public List<Task> getTodaysTask(Date date) throws ParseException, SQLException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String Date = dateFormat.format(date);
        List<Task> todaysList =new ArrayList<Task>();
        String query1="SELECT * FROM task4 WHERE dueDate LIKE '"+Date+"'";
        ResultSet searchList=stmt.executeQuery(query1);
        while (searchList.next())
        {
            String name=searchList.getString(1);
            String dis=searchList.getString(2);
            String dueDate=searchList.getString(3);
            String status=searchList.getString(4);
            String id=searchList.getString(5);
            Date date1=new Date();
            Date dueDate1=sdfo.parse(dueDate);
            todaysList.add(new Task(name,dis,date1,Status.valueOf(status),id,dueDate1));
        }

        return todaysList;
    }
}