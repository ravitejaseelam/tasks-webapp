package com.raviteja;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    public static void main(String[] args) throws ParseException, SQLException {
      TaskManager tm =new TaskManager();
      int flag=0;
        while (true) {
            System.out.println("Select one of the below task");
            System.out.println("1.Add elements");
            System.out.println("2.List elements");
            System.out.println("3.Search element by name");
            System.out.println("4.Delete elements by name");
            System.out.println("5.Search by status");
            System.out.println("6.Update");
            System.out.println("7.Pending List");
            System.out.println("8.Today's Due List");
            System.out.println("9.Exit");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            String switchVariable = sc.next();
            String id;
            switch (switchVariable) {
                case "1":
                    try {
                        System.out.println("Name");
                        String name = sc.next();
                        sc.nextLine();
                        System.out.println("Description about task");
                        String description = sc.nextLine();
                        Date date = new Date();
                        System.out.println("Enter Due Date in (DD/MM/YYYY) format");
                        String sdueDate = sc.nextLine();
                        SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
                        Date dueDate = sdfo.parse(sdueDate);
                        Status status = Status.valueOf("Assigned");
                        tm.add(name, description, date, status, dueDate);
                        System.out.println(".............Added...............");
                    }
                    catch (NullPointerException e)
                    {
                        System.out.println(e);
                        System.out.println("You are not allowed to leave any of the fields empty");
                    }
                    catch(ParseException p)
                    {
                        System.out.println(p);
                        System.out.println("Plz Provide Date in the mentioned format");
                    }
                    break;

                case "2":
                    List <Task> taskList=tm.display();

                    if(taskList.isEmpty())
                    {
                         System.out.println("Not Found");
                    }
                    else {
                        System.out.println(".............Contents...............");
                        Print(taskList);
                    }
                    break;

                case "3":
                    System.out.println("Enter name to be searched");
                    String name = sc.next();
                    sc.nextLine();
                    flag=0;
                    List<Task> nameSearchList=tm.search(name);
                   if(nameSearchList.isEmpty())
                   {
                       System.out.println("Not Found");
                   }
                   else {
                       for (Task obj : nameSearchList) {
                           System.out.println("Id:"+obj.id);
                           System.out.println("Name:"+obj.name);
                           System.out.println();
                       }
                       System.out.println("Enter id to get full details");
                       id=sc.next();
                       System.out.println(".............Contents...............");
                       for (Task obj : nameSearchList) {
                           if (obj.id.equals(id)) {
                               System.out.println("ID:" + obj.id);
                               System.out.println("Name:" + obj.name);
                               System.out.println("Description:" + obj.description);
                               System.out.println("Due date:" + obj.duedate);
                               System.out.println("Status:" + obj.status);
                               System.out.println();
                               flag++;
                           }
                       }
                           if(flag==0)
                               System.out.println("No such ID found");
                   }
                    break;

                case "4":
                try {

                    System.out.println("Enter name to be deleted");
                    name = sc.next();
                    sc.nextLine();
                    List<Task> deleteList = tm.search(name);
                    flag=0;

                    if (deleteList.isEmpty()) {
                         System.out.println("Not Found");
                    }
                    else {

                        for (Task obj : deleteList) {
                            System.out.println("Id:" + obj.id);
                            System.out.println("Name:" + obj.name);
                            System.out.println();
                        }
                        System.out.println("Enter id to get deleted permanently");
                        id = sc.next();
                        for (Task obj : deleteList) {
                            if (obj.id.equals(id)) {
                                tm.delete(name,id);
                                flag++;
                            }
                        }
                        if(flag==0) {
                            System.out.println("No such ID found");
                            break;
                        }
                    }
                }

                catch (Exception e){
                    System.out.println("List has become empty");
                    System.out.println();
                }
                    System.out.println(".............Deleted...............");
                    break;

                case "5":
                    System.out.println("Enter status to be searched(Assigned,InProgress,Complete)");
                    name = sc.next();
                    sc.nextLine();
                    try {
                        Status v = Status.valueOf(name);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println(e);
                        System.out.println("Plz type only from the above given options, try again");
                    }
                    System.out.println(name);
                    flag=0;
                    List<Task> satusSearchList=tm.searchStatus(name);

                    if(satusSearchList.isEmpty())
                    {
                         System.out.println("Not Found");
                    }
                    else
                    {
                        for (Task obj : satusSearchList) {
                            System.out.println("Id:" + obj.id);
                            System.out.println();
                            System.out.println("Name:" + obj.name);
                            System.out.println();
                        }
                        System.out.println("Enter id to get full details");
                        id = sc.next();

                        System.out.println(".............Contents...............");
                        for (Task obj : satusSearchList) {
                            if (obj.id.equals(id)) {
                                System.out.println("ID:" + obj.id);
                                System.out.println("Name:" + obj.name);
                                System.out.println("Description:" + obj.description);
                                System.out.println("Date:" + obj.date);
                                System.out.println("Due date:" + obj.duedate);
                                System.out.println("Status:" + obj.status);
                                System.out.println();
                                flag++;
                            }
                        }
                        if(flag==0)
                        {
                            System.out.println("No such ID found");
                            break;
                        }
                    }

                    break;

                case "6":
                    System.out.println("Enter name to be updated");
                    name = sc.next();
                    sc.nextLine();
                    System.out.println(name);
                    List<Task>updateList = tm.search(name);
                    if(updateList.isEmpty())
                    {
                         System.out.println("Not Found");
                    }

                  else {
                        for (Task obj : updateList) {
                            System.out.println("Id:" + obj.id);
                            System.out.println("Name:" + obj.name);
                            System.out.println();
                        }
                        System.out.println("Enter id to edit");
                        id = sc.next();

                        for (Task obj : updateList) {
                            if (obj.id.equals(id)) {
                                System.out.println("Change the Status to(Assigned,InProgress,Complete)");
                                String status1= sc.next();
                                Status status;
                                try{
                                    status=Status.valueOf(status1);
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e);
                                    flag++;
                                    System.out.println("Plz type only from the above given options only, try again");
                                    break;
                                }
                                tm.updateStatus(status, name, id);
                                flag++;
                            }
                        }
                        if(flag==0)
                        {
                            System.out.println("No such ID found");
                            break;
                        }
                        System.out.println(".............Updated...............");
                    }
                    break;

                case "7":
                    List<Task> pendingTask=tm.getPendingTask();

                    if(pendingTask.isEmpty())
                    {
                         System.out.println("Not Found");
                    }

                    else
                    {
                        System.out.println(".............Pending Tasks...............");
                        Print(pendingTask);
                    }
                    break;

                case "8":
                    System.out.println(".............Today's Tasks...............");
                    Date date=new Date();
                    List<Task> todaysTask=tm.getTodaysTask(date);

                    if(todaysTask.isEmpty())
                    {
                         System.out.println("Not Found");
                    }
                    else {
                        Print(todaysTask);
                    }
                    break;

                case "9":
                    System.exit(1);
                    break;

                default:
                    System.out.println("Plz Enter Correct Input from (1-9)");
                    System.out.println();
                    break;

            }
        }
    }
    static void Print(List<Task> list) {
        for (Task obj : list) {
            System.out.println("Id:" + obj.id);
            System.out.println("Name:" + obj.name);
            System.out.println("Description:" + obj.description);
            System.out.println("Due date:" + obj.duedate);
            System.out.println("Status:" + obj.status);
            System.out.println();
        }
    }
}